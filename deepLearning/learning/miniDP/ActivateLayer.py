import numpy as np

from learning.miniDP.Layer import CLayer


class CActivator(object):

    def forward(self, z):
        pass

    def backward(self, z, a, delta):
        pass

    def get_name(self):
        return self.__class__.__name__


class ActivationLayer(CLayer):
    def __init__(self, activator: CActivator):
        super().__init__()
        self.a = None
        self.z = None
        self.activator = activator

    def forward(self, input, train=True):
        self.z = input
        self.a = self.activator.forward(self.z)
        return self.a

    def backward(self, delta_in, layer_idx=None):
        dz = self.activator.backward(self.z, self.a, delta_in)
        return dz


# 直传函数，相当于无激活
class Identity(CActivator):
    def forward(self, z):
        return z

    def backward(self, z, a, delta):
        return delta


class Sigmoid(CActivator):
    def forward(self, z):
        a = 1.0 / (1.0 + np.exp(-z))
        return a

    def backward(self, z, a, delta):
        da = np.multiply(a, 1 - a)
        dz = np.multiply(delta, da)
        return dz


class Tanh(CActivator):
    def forward(self, z):
        a = 2.0 / (1.0 + np.exp(-2 * z)) - 1.0
        return a

    def backward(self, z, a, delta):
        da = 1 - np.multiply(a, a)
        dz = np.multiply(delta, da)
        return dz


class Relu(CActivator):
    def forward(self, z):
        a = np.maximum(z, 0)
        return a

    # 注意relu函数判断是否大于1的根据是正向的wx+b=z的值，而不是a值
    def backward(self, z, a, delta):
        da = np.zeros(z.shape).astype('float32')
        da[z > 0] = 1
        dz = da * delta
        return dz
