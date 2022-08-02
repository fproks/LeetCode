import math

import numpy as np

from learning.miniDP.EnumDef import InitialMethod, RegularMethod
from learning.miniDP.Optimizer import OptimizerFactory


class WeightsBiases(object):
    def __init__(self, n_input, n_output, init_method, optimizer_name, eta, regular_value, regular_name):
        self.regular_name = regular_name
        self.regular_value = regular_value
        self.B = None
        self.W = None
        self.init_method = init_method
        self.learn_rate = eta
        self.optimizer_name = optimizer_name
        self.n_output = n_output
        self.n_input = n_input

    def initialize(self):
        self.W, self.B = WeightsBiases.InitialParameters(self.n_input, self.n_output, self.init_method)
        self.dW = np.zeros_like(self.W).astype('float32')
        self.dB = np.zeros_like(self.B).astype('float32')
        self.oW = OptimizerFactory.createOptimizer(self.learn_rate, self.optimizer_name)
        self.oB = OptimizerFactory.createOptimizer(self.learn_rate, self.optimizer_name)

    def forward(self, x):
        return np.dot(x, self.W) + self.B

    def backward(self, x, delta_in):
        dz = delta_in
        m = dz.shape[0]
        if self.regular_name == RegularMethod.L2:
            self.dW = (np.dot(x.T, dz) + self.regular_value * self.W) / m
        elif self.regular_name == RegularMethod.L1:
            self.dW = (np.dot(x.T, dz) + self.regular_value * np.sign(self.W)) / m
        else:
            self.dW = np.dot(x.T, dz) / m
        self.dB = np.sum(dz, axis=0, keepdims=True) / m
        delta_out = np.dot(dz, self.W.T)
        return delta_out

    def update(self):
        self.W = self.oW.update(self.W, self.dW)
        self.B = self.oB.update(self.B, self.dB)

    @staticmethod
    def InitialParameters(num_input, num_output, method):
        W = None
        if method == InitialMethod.Zero:
            W = np.zeros((num_input, num_output)).astype('float32')
        elif method == InitialMethod.Normal:
            W = np.random.normal(size=(num_input, num_output)).astype('float32')
        elif method == InitialMethod.MSRA:
            W = np.random.normal(0, np.sqrt(2 / num_input), size=(num_input, num_output)).astype('float32')
        elif method == InitialMethod.Xavier:
            t = math.sqrt(6 / (num_input + num_output))
            W = np.random.uniform(-t, t, (num_input, num_output)).astype('float32')
        B = np.zeros((1, num_output)).astype('float32')
        return W, B
