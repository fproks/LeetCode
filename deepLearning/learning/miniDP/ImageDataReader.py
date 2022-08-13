import numpy as np

from learning.miniDP.Data import DataReader


class ImageDataReader(DataReader):
    def __init__(self, train_file, test_file):
        super().__init__(train_file, test_file)

    def NormalizeX(self):
        if self.XTrainRaw.ndim > 2:
            x_min = np.min(self.XTrainRaw)
            x_max = np.max(self.XTrainRaw)
            self.XTrain = (self.XTrainRaw - x_min) / (x_max - x_min)
            self.XTest = (self.XTestRaw - x_min) / (x_max - x_min)
        else:
            super().NormalizeX()

    def CoverToGray(self):
        self.XTrain = ImageDataReader._cover_to_gray(self.XTrainRaw)
        self.XTest = ImageDataReader._cover_to_gray(self.XTestRaw)

    @staticmethod
    def _cover_to_gray(data):
        (N, C, H, W) = data.shape
        new_data = np.empty((N, H * W))
        for i in range(N):
            if C == 3:
                new_data[i] = np.dot([0.299, 0.587, 0.114], data[i].reshape(3, -1)).reshape(1, -1)
            elif C == 1:
                new_data[i] = data[i, 0].reshape(1, -1)
        return new_data
