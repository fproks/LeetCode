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

    def test_matrixScore(self):
        sole = ArraySolution()
        self.assertEqual(39, sole.matrixScore([[0, 0, 1, 1], [1, 0, 1, 0], [1, 1, 0, 0]]))

    def test_spiralMatrixIII(self):
        sole = ArraySolution()

        self.assertEqual([[0, 0], [0, 1], [0, 2], [0, 3]], sole.spiralMatrixIII(1, 4, 0, 0))
        self.assertEqual(
            [[1, 4], [1, 5], [2, 5], [2, 4], [2, 3], [1, 3], [0, 3], [0, 4], [0, 5], [3, 5], [3, 4], [3, 3], [3, 2],
             [2, 2], [1, 2], [0, 2], [4, 5], [4, 4], [4, 3], [4, 2], [4, 1], [3, 1], [2, 1], [1, 1], [0, 1], [4, 0],
             [3, 0], [2, 0], [1, 0], [0, 0]], sole.spiralMatrixIII(5, 6, 1, 4))

    def test_pathInZigZagTree(self):
        sole = ArraySolution()
        self.assertEqual([1, 3, 4, 14], sole.pathInZigZagTree(14))
        self.assertEqual([1, 2, 6, 10, 26], sole.pathInZigZagTree(26))

    def test_sortArray(self):
        sole = ArraySolution()
        self.assertEqual([1, 2, 3, 5], sole.sortArray([5, 2, 3, 1]))
        self.assertEqual([0, 0, 1, 1, 2, 5], sole.sortArray([5, 1, 1, 2, 0, 0],3))

    def test_intervalIntersection(self):
        sole = ArraySolution()
        self.assertEqual([[1, 2], [5, 5], [8, 10], [15, 23], [24, 24], [25, 25]],
                         sole.intervalIntersection(A=[[0, 2], [5, 10], [13, 23], [24, 25]],
                                                   B=[[1, 5], [8, 12], [15, 24], [25, 26]]))

    def test_maxSumAfterPartitioning(self):
        sole = ArraySolution()
        self.assertEqual(84, sole.maxSumAfterPartitioning([1, 15, 7, 9, 2, 5, 10],3))

    def test_pancakeSort(self):
        sole =ArraySolution()
        print(sole.pancakeSort([3,2,4,1]))


    def test_kClosest(self):
        sole= ArraySolution()
        print(sole.kClosest([[3,3],[5,-1],[-2,4]],2))

    def test_stoneGame(self):
        sole =ArraySolution()
        self.assertTrue(sole.stoneGame([5,3,4,5]))

    def test_canVisitAllRooms(self):
        sole=ArraySolution()
        self.assertTrue(sole.canVisitAllRooms([[1],[2],[3],[]]))

    def test_mctFromLeafValues(self):
        sole=ArraySolution()
        #self.assertEqual(32,sole.mctFromLeafValues([6,2,4]))
        self.assertEqual(644,sole.mctFromLeafValues([9,14,6,4,13,12,1,6]))

    def test_validateStackSequences(self):
        sole=ArraySolution()
        #self.assertTrue(sole.validateStackSequences([1,2,3,4,5],[4,5,3,2,1]))
        self.assertFalse(sole.validateStackSequences([1, 2, 3, 4, 5], [4,3,5,1,2]))


if __name__ == '__main__':
    ArraySolutionTest.main()
