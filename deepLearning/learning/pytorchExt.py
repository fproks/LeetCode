import matplotlib.pyplot as plt
import numpy as np
import torch
from torch import FloatTensor, nn
from torch.utils.data import DataLoader, TensorDataset

from learning.miniDP.Data import DataReader


class PytorchExt(object):
    def __init__(self, batch_size: int, max_epoch: int, model: nn.Module, loss_func, optimizer, checkpoint):
        self.test_label = None
        self.test_data = None
        self.val_label = None
        self.val_data = None
        self.dataLoader = None
        self.batch_size = batch_size
        self.max_epoch = max_epoch
        self.model = model
        self.loss_func = loss_func
        self.optimizer = optimizer
        self.checkpoint = checkpoint

    def generateDataFromDataReader(self, dataReader: DataReader):
        self.dataLoader = DataLoader(
            dataset=TensorDataset(FloatTensor(dataReader.XTrain), FloatTensor(dataReader.YTrain)),
            batch_size=self.batch_size, shuffle=True)
        self.val_data = FloatTensor(dataReader.XDev)
        self.val_label = FloatTensor(dataReader.YDev)
        self.test_data = FloatTensor(dataReader.XTest)
        self.test_label = FloatTensor(dataReader.YTest)

    def generateDataFromNumpy(self, train_x, train_y, test_x, test_y, val_x=None, val_y=None):
        self.dataLoader = DataLoader(
            dataset=TensorDataset(FloatTensor(train_x), FloatTensor(train_y)),
            batch_size=self.batch_size, shuffle=True)
        self.test_data = FloatTensor(test_x)
        self.test_label = FloatTensor(test_y)
        if (val_x is not None) and (val_y is not None):
            self.val_data = FloatTensor(val_x)
            self.val_label = FloatTensor(val_y)

    def iterTrain(self, need_check=False, CUDA=False):
        if CUDA is True:
            self.test_data = self.test_data.cuda()
            self.test_label = self.test_label.cuda()
            if (self.val_data is not None) and (self.val_label is not None):
                self.val_data = self.val_data.cuda()
                self.val_label = self.val_label.cuda()
        train_loss = []
        train_acc = []
        test_loss = []
        test_acc = []
        for epoch in range(self.max_epoch):
            epoch_loss = []
            epoch_acc = []
            for step, (batch_x, batch_y) in enumerate(self.dataLoader):
                batch_len = len(batch_y)
                if CUDA:
                    batch_x = batch_x.cuda()
                    batch_y = batch_y.cuda()
                pred = self.model(batch_x)
                loss = self.loss_func(pred, batch_y)
                acc = torch.eq(pred.argmax(1), batch_y.argmax(1)).sum() / batch_len
                self.optimizer.zero_grad()
                loss.backward()
                self.optimizer.step()
                print(f"\r epoch {epoch}--step {step}: loss {loss.cpu().data:.6f},acc= {acc * 100:.6f}%", end="",
                      flush=True)
                if need_check and (step + 1) % self.checkpoint == 0:
                    val_data = self.val_data if self.val_data is not None else self.test_data
                    val_label = self.val_label if self.val_label is not None else self.test_label
                    pred = self.model(val_data)
                    loss = self.loss_func(pred, val_label)
                    acc = torch.eq(pred.argmax(1), val_label.argmax(1)).sum() / len(self.val_label)
                    print()
                    print(f"epoch {epoch}--step {step}: loss={loss.cpu().data:.6f}, acc={acc * 100:.6f}%")
                epoch_acc.append(acc.cpu().data)
                epoch_loss.append(loss.cpu().data)
            print()
            train_loss.append(np.mean(epoch_loss))
            train_acc.append(np.mean(epoch_acc))
            pred = self.model(self.test_data)
            loss = self.loss_func(pred, self.test_label)
            acc = torch.eq(pred.argmax(1), self.test_label.argmax(1)).sum() / len(self.test_label)
            test_loss.append(loss.cpu().data)
            test_acc.append(acc.cpu().data)
            print(
                f"\033[0;35mepoch {epoch}: train_loss: {np.mean(epoch_loss):.6f}, test_loss: {loss.cpu().data:.6f}, "
                f"train_acc={np.mean(epoch_acc) * 100:.6f}%, test_acc={acc * 100:.6f}%\033[0m")
        return {"train_accuracy": train_acc, "test_accuracy": test_acc, "train_loss": train_loss,
                "test_loss": test_loss}

    @staticmethod
    def pltshow(title, loss_train, loss_test, legend1, legend2):
        plt.plot([i for i in range(len(loss_train))], loss_train)
        plt.plot([i for i in range(len(loss_test))], loss_test)
        plt.title(title)
        plt.legend([legend1, legend2])
        plt.show()
