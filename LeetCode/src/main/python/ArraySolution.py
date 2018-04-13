# coding=utf-8
import itertools


class ArraySoultion:

    # 812. Largest Triangle Area
    def largestTriangleArea(self, points):
        """
        :type points: List[List[int]]
        :rtype: float
        """
        return max(0.5 * abs(i[0] * j[1] + j[0] * k[1] + k[0] * i[1] - j[0] * i[1] - k[0] * j[1] - i[0] * k[1])
                   for i, j, k in itertools.combinations(points, 3))


if __name__ == '__main__':
    solu = ArraySoultion()
    p = [[0, 0], [0, 1], [1, 0], [0, 2], [2, 0]]
    print(solu.largestTriangleArea(p))
