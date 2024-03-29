from learning.miniDP.EnumDef import NetType, InitialMethod, OptimizerName, RegularMethod


class HyperParameter(object):
    def __init__(self, eta=0.1, max_epoch=10000, batch_size=5, net_type=NetType.Fitting,
                 init_method=InitialMethod.Xavier,
                 optimizer_name=OptimizerName.SGD,
                 stopper=None,
                 regular_name=RegularMethod.Nothing, regular_value=1.0):
        self.regular_value = regular_value
        self.regular_name = regular_name
        self.stopper = stopper
        self.optimizer_name = optimizer_name
        self.init_method = init_method
        self.net_type = net_type
        self.batch_size = batch_size
        self.max_epoch = max_epoch
        self.eta = eta

    def toString(self):
        title = str.format("bz:{0},eta:{1},init:{2},op:{3}", self.batch_size, self.eta, self.init_method.name,
                           self.optimizer_name.name)
        if self.regular_name != RegularMethod.Nothing:
            title += str.format(",rgl:{0}:{1}", self.regular_name.name, self.regular_value)
        return title
