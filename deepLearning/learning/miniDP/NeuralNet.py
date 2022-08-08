import math
import time

import numpy as np
from matplotlib import pyplot as plt

from learning.miniDP.Data import DataReader
from learning.miniDP.EnumDef import RegularMethod, StopCondition
from learning.miniDP.FullConnectionLayer import FullLayer
from learning.miniDP.HyperParameter import HyperParameter
from learning.miniDP.Layer import CLayer
from learning.miniDP.LossFunction import LossFunction
from learning.miniDP.TrainHistory import TrainHistory


class NeuralNet(object):
    def __init__(self, param: HyperParameter):
        self.hp = param
        self.layer_list: [CLayer] = []
        self.output = None
        self.layer_count = 0
        self.accuracy = 0

    def addLayer(self, layer: CLayer):
        self.layer_list.append(layer)
        self.layer_count += 1

    def forward(self, x, train=True):
        input = x
        for i in range(self.layer_count):
            layer = self.layer_list[i]
            output = layer.forward(input, train)
            input = output
        self.output = input
        return self.output

    def inference(self, x):
        return self.forward(x, False)

    def backward(self, output, Y):
        delta_in = output - Y
        for i in range(self.layer_count - 1, -1, -1):
            layer = self.layer_list[i]
            delta_out = layer.backward(delta_in, i)
            delta_in = delta_out

    def update(self):
        for i in range(self.layer_count - 1, -1, -1):
            self.layer_list[i].update()

    def __get_regular_cost_from_fc_layer(self, regularName):
        if regularName != RegularMethod.L1 and regularName != RegularMethod.L2:
            return 0
        regular_cost = 0
        for i in range(self.layer_count - 1, -1, -1):
            layer = self.layer_list[i]
            if isinstance(layer, FullLayer):
                if regularName == RegularMethod.L1:
                    regular_cost += np.sum(np.abs(layer.weight.W))
                elif regularName == RegularMethod.L2:
                    regular_cost += np.sum(np.square(layer.weight.W))
        return regular_cost * self.hp.regular_value

    def train(self, dataReader: DataReader, checkpoint=0.1, need_test=True):
        t0 = time.time()
        self.loss_func = LossFunction(self.hp.net_type)
        if self.hp.regular_name == RegularMethod.EarlyStop:
            self.loss_trace = TrainHistory(True, self.hp.regular_value)
        else:
            self.loss_trace = TrainHistory()
        if self.hp.batch_size == -1 or self.hp.batch_size > dataReader.num_train:
            self.hp.batch_size = dataReader.num_train
        max_iteration = math.ceil(dataReader.num_train / self.hp.batch_size)
        checkpoint_iteration = int(math.ceil(max_iteration * checkpoint))
        need_stop = False
        for epoch in range(self.hp.max_epoch):
            dataReader.Shuffle()
            for iteration in range(max_iteration):
                batch_x, batch_y = dataReader.getBatchTrainSamples(self.hp.batch_size, iteration)
                output = self.forward(batch_x, train=True)
                self.backward(output, batch_y)
                self.update()

                total_iterations = epoch * max_iteration + iteration
                if (total_iterations + 1) % checkpoint_iteration == 0:

                    need_stop = self.checkErrorAdnLoss(dataReader, dataReader.XDev, dataReader.YDev, epoch,
                                                       total_iterations)
                    if need_stop:
                        break
            dataReader.Shuffle()
            if need_stop:
                break
        t1 = time.time()
        print(f"use time :{t1 - t0}")
        if need_test:
            print("testing...")

    def checkErrorAdnLoss(self, dataReader: DataReader, train_x, train_y, epoch, total_iterations):
        print(f"epoch:{epoch}, total_iterations:{total_iterations}")

        regular_cost = self.__get_regular_cost_from_fc_layer(self.hp.regular_name)

        output = self.forward(train_x, False)
        loss_train, acc_train = self.loss_func.CheckLoss(output, train_y)
        loss_train = loss_train + regular_cost / train_x.shape[0]

        print(f"loss_train: {loss_train:.6f}, acc_train: {acc_train}")
        val_x, val_y = dataReader.getValidationSet()
        output = self.forward(val_x, False)
        loss_val, acc_val = self.loss_func.CheckLoss(output, val_y)
        print(f"loss_valid={loss_val:.6f}, accuracy_val={acc_val}")

        need_stop = self.loss_trace.add(epoch, total_iterations, loss_train, acc_train, loss_val, acc_val,
                                        self.hp.stopper)
        if self.hp.stopper is not None:
            if self.hp.stopper.stop_condition == StopCondition.StopLoss and loss_val <= self.hp.stopper.stop_value:
                need_stop = True
        return need_stop

    def test(self, dataReader):
        x, y = dataReader.getTestSet()
        output = self.forward(x, False)
        _, acc = self.loss_func.CheckLoss(output, y)
        print(acc)

    def showLossHistory(self, title, xcoord, xmin=None, xmax=None, ymin=None, ymax=None):
        self.loss_trace.showLossHistory(title, xcoord, xmin, xmax, ymin, ymax)
