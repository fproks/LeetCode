import numpy as np

from learning.miniDP.EnumDef import NetType


class LossFunction(object):
    def __init__(self, net_type: NetType):
        self.net_type = net_type

    def CheckLoss(self, A, Y):
        m = Y.shape[0]
        if self.net_type == NetType.Fitting:
            loss, acc = self.MSE(A, Y, m)
        elif self.net_type == NetType.BinaryClassifier:
            loss, acc = self.CE2(A, Y, m)
        elif self.net_type == NetType.MultipleClassifier:
            loss, acc = self.CE3(A, Y, m)
        # end if
        if loss.ndim == 1:
            return loss[0], acc[0]
        return loss, acc

    @staticmethod
    def MSE(A, Y, count):
        p1 = A - Y
        LOSS = np.multiply(p1, p1)
        loss = np.sum(LOSS) / count / 2
        var = np.var(Y)
        mse = np.sum((A - Y) * (A - Y)) / count
        r2 = 1 - mse / var

        return loss, r2

    # for binary classifier
    @staticmethod
    def CE2(A, Y, count):
        p1 = 1 - Y
        p2 = np.log(1 - A + 1e-5)
        p3 = np.log(A)
        p4 = np.multiply(p1, p2)
        p5 = np.multiply(Y, p3)
        LOSS = np.sum(-(p4 + p5))  # binary classification
        loss = LOSS / count
        # accuracy
        b = np.round(A)
        r = (b == Y)
        correct = np.sum(r) / count

        return loss, correct

    # for multiple classifier
    @staticmethod
    def CE3(A, Y, count):
        p1 = np.log(A + 1e-7)
        p2 = np.multiply(Y, p1)
        LOSS = np.sum(-p2)
        loss = LOSS / count
        ra = np.argmax(A, axis=1)
        ry = np.argmax(Y, axis=1)
        r = (ra == ry)
        correct = np.sum(r) / count

        return loss, correct
