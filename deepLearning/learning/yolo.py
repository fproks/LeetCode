import numpy as np
import tensorflow as tf
import cv2


def leak_relu(x, alpha=0.1):
    return tf.maximum(alpha * x, x)


class Yolo(object):
    def __init__(self, weights_file):
        self.verbose = True
        self.S = 7  # 单元格数量
        self.B = 2  # bbox(边框和宽高) 数量(预测一个单元格存在多个目标物体？？？)
        self.classes = ["aeroplane", "bicycle", "bird", "boat", "bottle",
                        "bus", "car", "cat", "chair", "cow", "diningtable",
                        "dog", "horse", "motorbike", "person", "pottedplant",
                        "sheep", "sofa", "train", "tvmonitor"]  # 分类类别
        self.C = len(self.classes)  # 类别个数
        self.x_offset = np.transpose(  # 7*7*2
            np.reshape(np.array([np.arange(self.S)] * self.S * self.B), [self.B, self.S, self.S]), [1, 2, 0])
        self.y_offset = np.transpose(self.x_offset, [1, 0, 2])  # 7*7*2
        self.threshold = 0.2
        self.iou_threshold = 0.5
        self.session = tf.Session()
        self._build_net()

    def _build_net(self):
        if self.verbose:
            print("start to build the network ...")
        self.images = tf.placeholder(tf.float32, [None, 448, 448, 3])  # 待输入的图片大小
        net = self._conv_layer(self.images, 1, 64, 7, 2)
        net = self._maxpool_layer(net, 1, 2, 2)
        net = self._conv_layer(net, 2, 192, 3, 1)
        net = self._maxpool_layer(net, 2, 2, 2)
        net = self._conv_layer(net, 3, 128, 1, 1)
        net = self._conv_layer(net, 4, 256, 3, 1)
        net = self._conv_layer(net, 5, 256, 1, 1)
        net = self._conv_layer(net, 6, 512, 3, 1)
        net = self._maxpool_layer(net, 6, 2, 2)
        net = self._conv_layer(net, 7, 256, 1, 1)
        net = self._conv_layer(net, 8, 512, 3, 1)
        net = self._conv_layer(net, 9, 256, 1, 1)
        net = self._conv_layer(net, 10, 512, 3, 1)
        net = self._conv_layer(net, 11, 256, 1, 1)
        net = self._conv_layer(net, 12, 512, 3, 1)
        net = self._conv_layer(net, 13, 256, 1, 1)
        net = self._conv_layer(net, 14, 512, 3, 1)
        net = self._conv_layer(net, 15, 512, 1, 1)
        net = self._conv_layer(net, 16, 1024, 3, 1)
        net = self._maxpool_layer(net, 16, 2, 2)
        net = self._conv_layer(net, 17, 512, 1, 1)
        net = self._conv_layer(net, 18, 1024, 3, 1)
        net = self._conv_layer(net, 19, 512, 1, 1)
        net = self._conv_layer(net, 20, 1024, 3, 1)
        net = self._conv_layer(net, 21, 1024, 3, 1)
        net = self._conv_layer(net, 22, 1024, 3, 2)
        net = self._conv_layer(net, 23, 1024, 3, 1)
        net = self._conv_layer(net, 24, 1024, 3, 1)
        net = self._flatten(net)
        net = self._fully_connection_layer(net, 25, 512, activation=leak_relu)
        net = self._fully_connection_layer(net, 26, 4096, activation=leak_relu)
        net = self._fully_connection_layer(net, 27, self.S * self.S * (self.C + 5 * self.B))
        self.predicts = net

    def _conv_layer(self, x, id, num_filters, filter_size, stride):  # 卷积层
        in_channels = x.get_shape().as_list()[-1]
        weight = tf.Variable(
            tf.truncated_normal([filter_size, filter_size, in_channels, num_filters], stddev=0.1))  # 生成了一个卷积
        bias = tf.Variable(tf.zeros([num_filters, ]))  # 生成矩阵对应的偏差
        pad_size = filter_size // 2
        pad_mat = np.array([[0, 0], [pad_size, pad_size], [pad_size, pad_size], [0, 0]])
        x_pad = tf.add(x, pad_mat)
        conv = tf.nn.conv2d(x_pad, weight, strides=[1, stride, stride, 1], padding="VALID")  # strid 步长 wx
        output = leak_relu(tf.nn.bias_add(conv, bias))  # wx +b 这里就是+ b

        if self.verbose:
            print(" Layer %d: type=Conv, num_filter=%d, filter_size=%d, stride=%d, output_shape=%s" % (
                id, num_filters, filter_size, stride, str(output.get_shape())))
        return output

    def _fully_connection_layer(self, x, id, num_out, activation=None):
        num_in = x.get_shape().as_list()[-1]
        weight = tf.Variable(tf.truncated_normal([num_in, num_out], stddev=0.1))
        bias = tf.Variable(tf.zeros([num_out, ]))
        output = tf.nn.xw_plus_b(x, weight, bias)
        if activation:
            output = activation(output)
        if self.verbose:
            print("    Layer %d: type=Fc, num_out=%d, output_shape=%s"
                  % (id, num_out, str(output.get_shape())))
        return output

    def _maxpool_layer(self, x, id, pool_size, stride):  # 最大池化层
        output = tf.nn.max_pool(x, [1, pool_size, pool_size, 1], strides=[1, stride, stride, 1], padding="SAME")
        if self.verbose:
            print("    Layer %d: type=MaxPool, pool_size=%d, stride=%d, output_shape=%s"
                  % (id, pool_size, stride, str(output.get_shape())))
        return output

    def _flatten(self, x):
        tran_x = tf.transpose(x, [0, 3, 1, 2])
        nums = np.product(x.get_shape().as_list()[1:])
        print("nums is %d", nums)
        return tf.reshape(tran_x, [-1, nums])

    def _load_weights(self, weight_file):
        if self.verbose:  # 加载权重文件
            print("start to load weights from file: %s" % (weight_file))

        saver = tf.train.Saver()
        saver.restore(self.session, weight_file)

    def detect_from_file(self, image_file, imshow=True, deteted_box_file="boxes.txt",
                         detected_imagee_file="detected_img.jpg"):
        image = cv2.imread(image_file)
        img_h, img_w, _ = image.shape
        predicts = self._detect_from_image(image)
        predict_bosx = self._interpret_predicts(predicts, img_h, img_w)
        self.show_result(image, predict_bosx, True)

    def _detect_from_image(self, image):  # 进行运算
        img_resized = cv2.resize(image, (448, 448))
        img_RGB = cv2.cvtColor(img_resized, cv2.COLOR_BGR2RGB)
        img_resiezd_np = np.asarray(img_RGB)
        _images = np.zeros((1, 448, 448, 3), dtype=np.float32)
        _images[0] = (img_resiezd_np / 255.0) * 2.0 - 1.0
        predicts = self.session.run(self.predicts, feed_dict={self.images: _images})[0]
        return predicts

    def _interpret_predicts(self, predicts, img_h, img_w):  # 获取输出结果
        idx1 = self.S * self.S * self.C
        idx2 = idx1 + self.S * self.S * self.B
        class_probs = np.reshape(predicts[:idx1], [self.S, self.S, self.C])  # 获取每个单元格的类别信息C, 分类可能性
        confs = np.reshape(predicts[idx1:idx2], [self.S, self.S, self.B])  # 每个结果的置信度，每个单元格有B个结果
        boxes = np.reshape(predicts[idx2:], [self.S, self.S, self.B, 4])  # 每个结果的边框信息(x,y,w,h)， 每个单元格有B个结果
        # 计算 x, y 相对于
        boxes[:, :, :, 0] += self.x_offset
        boxes[:, :, :, 1] += self.y_offset
        boxes[:, :, :, :2] /= self.S

        boxes[:, :, :, 2:] = np.square(boxes[:, :, :, 2:])

        boxes[:, :, :, 0] *= img_w  # x 相对于左上角的位置 (中心位置)
        boxes[:, :, :, 1] *= img_h  # y 相对于左上角的位置  (中心位置)
        boxes[:, :, :, 2] *= img_w  # 宽度 像素个数
        boxes[:, :, :, 3] *= img_h  # 高度 像素个数

        score = np.expand_dims(confs, -1) * np.expand_dims(class_probs, 2)  # 获取每个类别的置信度
        score = np.reshape(score, [-1, self.C])  # [S*S*B,C]
        boxes = np.reshape(boxes, [-1, 4])  # [S*S*B,4]

        score[score < self.threshold] = 0.0  # 置信度低的置为0
        self._non_max_suppression(score, boxes)
        predict_box = []
        max_idxs = np.argmax(score, axis=1)  # 每个框的最大分类
        for i in range(len(score)):
            max_idx = max_idxs[i]
            if (score[i, max_idx]) > 0.0:
                predict_box.append(
                    (self.classes[max_idx], boxes[i, 0], boxes[i, 1], boxes[i, 2], boxes[i, 3], score[i, max_idx]))
        return predict_box

    def _non_max_suppression(self, scores, boxes):  # 消除重叠
        for c in range(self.C):
            sorted_idx = np.argsort(scores[:, c])  # argsort函数返回的是数组值从小到大的索引值
            last = len(sorted_idx) - 1
            while last > 0:
                if scores[sorted_idx[last], c] < 1e-6:
                    break
                for i in range(last):
                    if scores[sorted_idx[i], c] < 1e-6:
                        continue
                    if self._iou(boxes[sorted_idx[i]], boxes[sorted_idx[last]]) > self.iou_threshold:
                        scores[sorted_idx[i], c] = 0.0
                last -= 1

    def _iou(self, box1, box2):  # 计算交叉集

        # 交叉宽度为最大宽度的最小值- 最小宽度的最大值
        inter_w = np.minimum(box1[0] + 0.5 * box1[2], box2[0] + 0.5 * box2[2]) - \
                  np.maximum(box1[0] - 0.5 * box1[2], box2[0] - 0.5 * box2[2])

        inter_h = np.minimum(box1[1] + 0.5 * box1[3], box2[1] + 0.5 * box2[3]) - \
                  np.maximum(box1[1] - 0.5 * box2[3], box2[1] - 0.5 * box2[3])

        if inter_h <= 0 or inter_w <= 0:
            inter = 0
        else:
            inter = inter_w * inter_h
        union = box1[2] * box1[3] + box2[2] * box2[3] - inter
        return inter / union

    def show_result(self, image, results, imshow=True, deteted_boxes_file=None, deteted_image_file=None):
        img_cp = image.copy()
        if deteted_boxes_file:
            f = open(deteted_boxes_file, "w")
        for i in range(len(results)):
            x = int(results[i][1])
            y = int(results[i][2])
            w = int(results[i][3]) // 2
            h = int(results[i][4]) // 2

            if self.verbose:
                print("   class: %s, [x, y, w, h]=[%d, %d, %d, %d], confidence=%f" %
                      (results[i][0], x, y, w, h, results[i][-1]))

                cv2.rectangle(img_cp, (x - w, y - h), (x + w, y + h), (0, 255, 0), 2)
                cv2.rectangle(img_cp, (x - w, y - h - 20), (x + w, y - h), (125.125, 125), -1)
                cv2.putText(img_cp, results[i][0] + '%.2f' % results[i][5], (x - w + 5, y - h - 7),
                            cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 1)

            if deteted_boxes_file:
                f.write(results[i][0] + ',' + str(x) + ',' + str(y) + ',' + str(w) + ',' + str(h) + ',' + str(
                    results[i][5]) + '\n')

        if imshow:
            cv2.imshow('YOLO detection', img_cp)
            cv2.waitKey(1)
        if deteted_image_file:
            cv2.imwrite(deteted_image_file, img_cp)
        if deteted_boxes_file:
            f.close()


if __name__ == "__main__":
    yolo_net = Yolo("./yolo.ckpt")
    yolo_net.detect_from_file('car.jpg')
