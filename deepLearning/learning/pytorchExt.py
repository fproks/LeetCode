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

    def iterTrain(self, title):
        train_loss = []
        train_acc = []
        test_loss = []
        test_acc = []
        for epoch in range(self.max_epoch):
            epoch_loss = []
            epoch_acc = []
            for step, (batch_x, batch_y) in enumerate(self.dataLoader):
                pred = self.model(batch_x)
                loss = self.loss_func(pred, batch_y)
                self.optimizer.zero_grad()
                loss.backward()
                self.optimizer.step()
                if (step + 1) % self.checkpoint == 0:
                    pred = self.model(self.val_data)
                    loss = self.loss_func(pred, self.val_label)
                    acc = torch.eq(pred.argmax(1), self.val_label.argmax(1)).sum() / len(self.val_label)
                    epoch_acc.append(acc.cpu().data)
                    epoch_loss.append(loss.cpu().data)
                    print(f"epoch {epoch}--step {step}: loss={loss.cpu().data:.6f}, acc={acc * 100:.6f}%")
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
        self.pltshow(f"LOSS: {title}", train_loss, test_loss)
        self.pltshow(f"ACC: {title}", train_acc, test_acc)

    def pltshow(self, title, loss_train, loss_test):
        plt.plot([i for i in range(len(loss_train))], loss_train)
        plt.plot([i for i in range(len(loss_test))], loss_test)
        plt.title(title)
        plt.legend(["Val", "Test"])
        plt.show()
