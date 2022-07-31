import os
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, LabelBinarizer
from learning.miniDP.EnumDef import NetType


class DataReader(object):
    def __init__(self, train_file, test_file):
        self.YDev = None
        self.XDev = None
        self.YTest = None
        self.XTest = None
        self.num_test = None
        self.YTestRaw = None
        self.XTestRaw = None
        self.YTrainRaw = None
        self.XTrainRaw = None
        self.YTrain = None
        self.XTrain = None
        self.train_file = train_file
        self.test_file = test_file
        self.train_data = None
        self.test_data = None
        self.valid_data = None
        self.num_category = None
        self.num_train = None

    def ReadData(self):
        if os.path.exists(self.train_file):
            data = np.load(self.train_file)
            self.XTrainRaw: np.ndarray = data['data']
            self.YTrainRaw: np.ndarray = data['labels']
            assert (self.XTrainRaw.shape[0] == self.YTrainRaw.shape[0])
            self.num_train = self.XTrainRaw.shape[0]
            self.num_category = len(np.unique(self.YTrainRaw))
            self.XTrain = self.XTrainRaw
            self.YTrain = self.YTrainRaw
        else:
            raise Exception("read train file fail !!")

        if os.path.exists(self.test_file):
            data = np.load(self.test_file)
            self.XTestRaw: np.ndarray = data['data']
            self.YTestRaw: np.ndarray = data['labels']
            assert (self.XTestRaw.shape[0] == self.YTestRaw.shape[0])
            self.num_test = self.XTestRaw.shape[0]
            self.XTest = self.XTestRaw
            self.YTest = self.YTestRaw
            self.XDev = self.XTest
            self.YDev = self.YTest
        else:
            raise Exception("read test file fail!!")

    def NormalizeX(self):
        x_merge = np.vstack((self.XTrainRaw, self.XTestRaw))
        normalize = MinMaxScaler()
        normalize.fit(x_merge)
        self.XTrain = normalize.transform(self.XTrainRaw)
        self.XTest = normalize.transform(self.XTestRaw)

    def NormalizeY(self, netType: NetType):
        if netType == NetType.Fitting:
            y_merge = np.vstack((self.YTrainRaw, self.YTestRaw))
            normalize = MinMaxScaler()
            normalize.fit(y_merge)
            self.YTest = normalize.transform(self.YTestRaw)
            self.YTrain = normalize.transform(self.YTrainRaw)
        elif netType == NetType.BinaryClassifier:
            self.YTrain = self.oneZero(self.YTrainRaw)
            self.YTest = self.oneZero(self.YTestRaw)
        elif netType == NetType.MultiClassification:
            self.YTrain = LabelBinarizer().fit_transform(self.YTrainRaw)
            self.YTest = LabelBinarizer().fit_transform(self.YTestRaw)

    # tanh 需要将negative_value 设置为-1
    @staticmethod
    def oneZero(Y, positive_label=1, negative_label=0, positive_value=1, negative_value=0):
        temp = np.zeros_like(Y)
        temp[Y == positive_label] = positive_value
        temp[Y == negative_label] = negative_value
        return temp

    def generateValidationSet(self, k=0.1):
        if k > 1:
            k = 1 / k
        self.XTrain, self.XDev, self.YTrain, self.YDev = train_test_split(self.XTrain, self.YTrain, test_size=k,
                                                                          shuffle=True)

    def getValidationSet(self):
        return self.XDev, self.YDev

    def getTestSet(self):
        return self.XTest, self.YTest

    def getBatchTrainSamples(self, batch_size, iteration):
        start = iteration * batch_size
        end = start + batch_size
        batch_x = self.XTrain[start:end, :]
        batch_y = self.YTrain[start:end, :]
        return batch_x, batch_y

    def Shuffle(self):
        seed = np.random.randint(0, 100)
        np.random.seed(seed)
        xp = np.random.permutation(self.XTrain)
        np.random.seed(seed)
        yp = np.random.permutation(self.YTrain)
        self.XTrain = xp
        self.YTrain = yp
