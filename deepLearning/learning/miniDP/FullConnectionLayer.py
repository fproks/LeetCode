import numpy as np

from Layer import CLayer
from learning.miniDP.HyperParameter import HyperParameter
from learning.miniDP.WeightBias import WeightsBiases


class FullLayer(CLayer):

    def __init__(self, input_size: int, output_size: int, param: HyperParameter):
        super().__init__()
        self.input_shape = None
        self.x = None
        self.z = None
        self.input_size: int = input_size
        self.output_size: int = output_size
        self.param = param
        self.weight = WeightsBiases(self.input_size, self.output_size, param.init_method,
                                    param.optimizer_name, param.eta, param.regular_value, param.regular_name)

    def initialize(self):
        pass

    def forward(self, input: np.ndarray, train=True):
        self.input_shape = input.shape
        if input.ndim == 4:
            self.x = input.reshape(self.input_shape[0], -1)
        else:
            self.x = input
        self.z = self.weight.forward(self.x)

    def backward(self, delta_in, layer_idx):
        delta_out = self.weight.backward(self.x, delta_in)
        if layer_idx == 0:  # 第一层不用往前输出了
            return None
        if len(self.input_shape) > 2:
            return delta_out.reshape(self.input_shape)
        else:
            return delta_out

    def update(self):
        self.weight.update()
