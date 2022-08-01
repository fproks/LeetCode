from enum import Enum


class NetType(Enum):
    Fitting = 1,
    BinaryClassifier = 2,
    MultiClassification = 3


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
