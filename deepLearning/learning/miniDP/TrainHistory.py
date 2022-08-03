from matplotlib import pyplot as plt

from learning.miniDP.EnumDef import StopCondition, XCoordinate


class TrainHistory(object):
    def __init__(self, need_earlyStop=False, patience=5):
        self.loss_train = []
        self.accuracy_train = []
        self.iteration_seq = []
        self.epoch_seq = []
        self.loss_val = []
        self.accuracy_val = []
        self.counter = 0
        self.max_vld_acc = 0
        # for early stop
        self.early_stop = need_earlyStop
        self.patience = patience
        self.patience_counter = 0
        self.last_vld_loss = float("inf")

    def add(self, epoch, total_iterations, loss_train, accuracy_train, loss_val, accuracy_val, stopper):
        self.iteration_seq.append(total_iterations)
        self.epoch_seq.append(epoch)
        self.loss_train.append(loss_train)
        self.accuracy_train.append(accuracy_train)
        if loss_val is not None:
            self.loss_val.append(loss_val)
        if accuracy_val is not None:
            self.accuracy_val.append(accuracy_val)

        if stopper is not None:
            if stopper.stop_condition == StopCondition.StopDiff:
                if len(self.loss_val) > 1:
                    if abs(self.loss_val[-1] - self.loss_val[-2]) < stopper.stop_value:
                        self.counter = self.counter + 1
                        if self.counter > 3:
                            return True
                        else:
                            self.counter = 0

        if self.early_stop:
            if loss_val < self.last_vld_loss:
                self.patience_counter = 0
                self.last_vld_loss = loss_val
            else:
                self.patience_counter += 1
                if self.patience_counter >= self.patience:
                    return True
        return False

    def showLossHistory(self, title, xcoord, xmin=None, xmax=None, ymin=None, ymax=None):
        fig = plt.figure(figsize=(12, 5))

        axes = plt.subplot(1, 2, 1)
        if xcoord == XCoordinate.Iteration:
            p2, = axes.plot(self.iteration_seq, self.loss_train)
            p1, = axes.plot(self.iteration_seq, self.loss_val)
            axes.set_xlabel("iteration")
        elif xcoord == XCoordinate.Epoch:
            p2, = axes.plot(self.epoch_seq, self.loss_train)
            p1, = axes.plot(self.epoch_seq, self.loss_val)
            axes.set_xlabel("epoch")
        # end if
        axes.legend([p1, p2], ["validation", "train"])
        axes.set_title("Loss")
        axes.set_ylabel("loss")
        if xmin is not None or xmax is not None or ymin is not None or ymax is not None:
            axes.axis([xmin, xmax, ymin, ymax])

        axes = plt.subplot(1, 2, 2)
        if xcoord == XCoordinate.Iteration:
            p2, = axes.plot(self.iteration_seq, self.accuracy_train)
            p1, = axes.plot(self.iteration_seq, self.accuracy_val)
            axes.set_xlabel("iteration")
        elif xcoord == XCoordinate.Epoch:
            p2, = axes.plot(self.epoch_seq, self.accuracy_train)
            p1, = axes.plot(self.epoch_seq, self.accuracy_val)
            axes.set_xlabel("epoch")
        # end if
        axes.legend([p1, p2], ["validation", "train"])
        axes.set_title("Accuracy")
        axes.set_ylabel("accuracy")

        plt.suptitle(title)
        plt.show()

