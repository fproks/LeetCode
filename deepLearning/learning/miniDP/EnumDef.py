from enum import Enum


class NetType(Enum):
    Fitting = 1,
    BinaryClassifier = 2,
    MultipleClassifier = 3


class InitialMethod(Enum):
    Zero = 0,
    Normal = 1,
    Xavier = 2,
    MSRA = 3


class RegularMethod(Enum):
    Nothing = 0,
    L1 = 1,
    L2 = 2,
    EarlyStop = 3


class OptimizerName(Enum):
    SGD = 0,
    Momentum = 1,
    Nag = 2,
    AdaGrad = 3,
    AdaDelta = 4,
    RMSProp = 5,
    Adam = 6


class StopCondition(Enum):
    Nothing = 0,  # reach the max_epoch then stop
    StopLoss = 1,  # reach specified loss value then stop
    StopDiff = 2,  # reach specified abs(curr_loss - prev_loss)


class Stopper(object):
    def __init__(self, sc, sv):
        self.stop_condition = sc
        self.stop_value = sv


class XCoordinate(Enum):
    Nothing = 0,
    Iteration = 1,
    Epoch = 2
