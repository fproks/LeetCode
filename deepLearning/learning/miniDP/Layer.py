class CLayer(object):
    def __init__(self):
        pass

    def initialize(self):
        pass

    def forward(self, input, train=True):
        pass

    def backward(self,  delta_in, layer_idx):
        pass

    def train(self, input, train=True):
        pass

    def update(self):
        pass

    def save_parameters(self, folder, name):
        pass

    def load_parameters(self, folder, name):
        pass
