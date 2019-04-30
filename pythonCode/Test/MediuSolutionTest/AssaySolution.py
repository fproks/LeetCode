from unittest import TestCase

from Solution.MediumSoultion.ArraySolution import *


class ArraySolutionTest(TestCase):

    def test_findPoisonedDuration(self):
        solu = ArraySolution()
        self.assertEqual(4, solu.findPoisonedDuration([1, 4], 2))
        self.assertEqual(3, solu.findPoisonedDuration([1, 2], 2))

    def test_transpose(self):
        solu = ArraySolution()
        A = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
        TA = [[1, 4, 7], [2, 5, 8], [3, 6, 9]]
        self.assertEqual(solu.transpose(A), TA)

    def test_subdomainVisits(self):
        solu = ArraySolution()
        A = ['9001 discuss.leetcode.com']
        res = solu.subdomainVisits(A)
        self.assertEqual(len(res), 3)
        for str in res:
            print(str)

    def test_sortArrayByParity(self):
        A = [4, 2, 5, 7]
        solu = ArraySolution()
        p = solu.sortArrayByParityII(A)
        for i in p:
            print(i)

    def test_surfaceArea(self):
        sole = ArraySolution()
        self.assertEqual(10, sole.surfaceArea([[2]]))
        self.assertEqual(34, sole.surfaceArea([[1, 2], [3, 4]]))
        self.assertEqual(16, sole.surfaceArea([[1, 0], [0, 2]]))
        self.assertEqual(32, sole.surfaceArea([[1, 1, 1], [1, 0, 1], [1, 1, 1]]))
        self.assertEqual(46, sole.surfaceArea([[2, 2, 2], [2, 1, 2], [2, 2, 2]]))


if __name__ == '__main__':
    ArraySolutionTest.main()
