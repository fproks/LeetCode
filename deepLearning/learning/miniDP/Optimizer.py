import numpy as np

from learning.miniDP.EnumDef import OptimizerName


class OptimizerFactory(object):
    @staticmethod
    def createOptimizer(lr, name=OptimizerName.SGD):
        if name == OptimizerName.SGD:
            optimizer = SGD(lr)
        elif name == OptimizerName.Adam:
            optimizer = Adam(lr)
        elif name == OptimizerName.AdaGrad:
            optimizer = AdaGrad(lr)
        elif name == OptimizerName.Momentum:
            optimizer = Momentum(lr)
        elif name == OptimizerName.Nag:
            optimizer = Nag(lr)
        elif name == OptimizerName.RMSProp:
            optimizer = RMSProp(lr)
        elif name == OptimizerName.AdaDelta:
            optimizer = AdaDelta(lr)
        return optimizer


class Optimizer(object):
    def __init__(self, lr):
        self.lr = lr
        pass

    def update(self, eta, grad):
        pass


class SGD(Optimizer):
    def __init__(self, lr):
        super().__init__(lr)

    def update(self, eta, grad):
        eta = eta - self.lr * grad
        return eta


class Adam(Optimizer):
    def __init__(self, lr):
        super().__init__(lr)
        self.p1 = 0.9
        self.p2 = 0.999
        self.eps = 1e-8
        self.t = 0
        self.m = np.empty((1, 1))
        self.v = np.empty((1, 1))

    def update(self, eta, grad):
        self.t = self.t + 1
        self.m = self.p1 * self.m + (1 - self.p1) * grad
        self.v = self.p2 * self.v + (1 - self.p2) * np.multiply(grad, grad)
        m_hat = self.m / (1 - self.p1 ** self.t)
        v_hat = self.v / (1 - self.p2 ** self.t)
        d_theta = self.lr * m_hat / (self.eps + np.sqrt(v_hat))
        eta = eta - d_theta
        return eta


class AdaDelta(Optimizer):
    def __init__(self, lr):
        super(AdaDelta, self).__init__(lr)
        self.eps = 1e-5
        self.r = 0
        self.s = 0
        self.alpha = 0.9

    def update(self, theta, grad):
        grad2 = np.multiply(grad, grad)
        self.s = self.alpha * self.s + (1 - self.alpha) * grad2
        d_theta = np.sqrt((self.eps + self.r) / (self.eps + self.s)) * grad
        theta = theta - d_theta
        d_theta2 = np.multiply(d_theta, d_theta)
        self.r = self.alpha * self.r + (1 - self.alpha) * d_theta2
        return theta


class RMSProp(Optimizer):
    def __init__(self, lr):
        super(RMSProp, self).__init__(lr)
        self.p = 0.9
        self.eps = 1e-6
        self.r = 0

    def update(self, theta, grad):
        grad2 = np.multiply(grad, grad)
        self.r = self.p * self.r + (1 - self.p) * grad2
        alpha = self.lr / np.sqrt(self.eps + self.r)
        theta = theta - alpha * grad
        return theta


class AdaGrad(Optimizer):
    def __init__(self, lr):
        super(AdaGrad, self).__init__(lr)
        self.eps = 1e-6
        self.r = 0

    def update(self, theta, grad):
        self.r = self.r + np.multiply(grad, grad)
        alpha = self.lr / (self.eps + np.sqrt(self.r))
        theta = theta - alpha * grad
        return theta


class Momentum(Optimizer):
    def __init__(self, lr):
        super(Momentum, self).__init__(lr)
        self.vt = 0
        self.alpha = 0.9

    def update(self, eta, grad):
        vt_new = self.alpha * self.vt - self.lr * grad
        eta = eta + vt_new
        self.vt = vt_new
        return eta


class Nag(Optimizer):
    def __init__(self, lr):
        super(Nag, self).__init__(lr)
        self.vt = 0
        self.alpha = 0.9

    # 先用预测的梯度来更新W,b
    def pre_update(self, theta):
        theta_hat = theta - self.alpha * self.vt
        return theta_hat

    # 再用动量法更新W,b do final update
    def update(self, theta, grad):
        self.vt = self.alpha * self.vt + self.lr * grad
        theta = theta - self.vt
        return theta
