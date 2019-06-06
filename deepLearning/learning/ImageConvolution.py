import numpy as np
import os
from PIL import Image
import matplotlib.pyplot as plt


def conv(image: np.ndarray, weight: np.ndarray):
    height, width = image.shape
    h, w = weight.shape
    new_h = height - h + 1
    new_w = width - w + 1
    new_image = np.zeros((new_h, new_w), dtype=np.float)
    for i in range(new_h):
        for j in range(new_w):
            new_image[i, j] = np.sum(image[i:i + h, j:j + w] * weight)
    new_image = new_image.clip(0, 255)
    new_image = np.rint(new_image).astype('uint8')
    return new_image


if __name__ == "__main__":
    A = Image.open("C:\\Users\\ezlinho\\Desktop\\4a52542d1c4999d719a2cded3d23e118.jpg", "r")
    out_path = "./picture/"
    if not os.path.exists(out_path):
        os.makedirs(out_path)
    a = np.array(A)
    sobel_x = np.array(([-1, 0, 1], [-2, 0, 2], [-1, 0, 1]))
    sobel_y = np.array(([-1, -2, -1], [0, 0, 0], [1, 2, 1]))
    sobel = np.array(([-1, -1, 0], [-1, 0, 1], [0, 1, 1]))
    # prewitt各个方向上的算子
    prewitt_x = np.array(([-1, 0, 1], [-1, 0, 1], [-1, 0, 1]))
    prewitt_y = np.array(([-1, -1, -1], [0, 0, 0], [1, 1, 1]))
    prewitt = np.array(([-2, -1, 0], [-1, 0, 1], [0, 1, 2]))
    # 拉普拉斯算子
    laplacian = np.array(([0, -1, 0], [-1, 4, -1], [0, -1, 0]))
    laplacian_2 = np.array(([-1, -1, -1], [-1, 8, -1], [-1, -1, -1]))
    weight_list = ("sobel_x", "sobel_y", "sobel", "prewitt_x", "prewitt_y", "prewitt", "laplacian", "laplacian_2")

    for w in weight_list:
        print("starting %s ..." % w)
        print("weight:\n")
        R = conv(a[:, :, 0], eval(w))
        G = conv(a[:, :, 1], eval(w))
        B = conv(a[:, :, 2], eval(w))
        I = np.stack((R, G, B), axis=2)
        Image.fromarray(I).save("%s//bigger-%s.jpg" % (out_path, w))
