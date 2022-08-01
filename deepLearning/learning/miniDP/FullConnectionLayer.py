import math

import numpy as np
from Layer import CLayer
from EnumDef import InitialMethod, RegularMethod
from learning.miniDP.HyperParameter import HyperParameter


class FullLayer(CLayer):

    def __init__(self, input_size: int, output_size: int, param: HyperParameter):
        super().__init__()
        self.input_shape = None
        self.x = None
        self.z = None
        self.x = None
        self.W = None
        self.dB = None
        self.dW = None
        self.B = None
        self.input_size: int = input_size
        self.output_size: int = output_size
        self.param = param

    def initialize(self):
        self.W, self.B = FullLayer.InitialParameters(self.input_size, self.output_size, self.param.init_method)
        self.dW = np.zeros_like(self.W).astype('float32')
        self.dB = np.zeros_like(self.B).astype('float32')

    def forward(self, input: np.ndarray, train=True):
        self.input_shape = input.shape
        if input.ndim == 4:
            self.x = input.reshape(self.input_shape[0], -1)
        else:
            self.x = input
        self.z = np.dot(self.x, self.W) + self.B

    def backward(self, delta_in, layer_idx):
        dz = delta_in
        m = self.x.shape[0]
        if self.param.regular_name == RegularMethod.L2:  # 2范数
            self.dW = (np.dot(self.x.T, dz) + self.param.regular_value * self.W) / m
        elif self.param.regular_name == RegularMethod.L1:
            self.dW = (np.dot(self.x.T, dz) + self.param.regular_value * np.sign(self.W)) / m
        else:
            self.dW = np.dot(self.x.T, dz) / m
        if layer_idx == 0:  # 第一层不用往前输出了
            return None
        delta_out = np.dot(dz, self.W.T)
        if len(self.input_shape) > 2:
            return delta_out.reshape(self.input_shape)
        else:
            return delta_out

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
