from unittest import TestCase

from Solution.MediumSoultion.ArraySolution import *


class ArraySolutionTest(TestCase):

    def test_findPoisonedDuration(self):
        solu =ArraySolution()
        self.assertEqual(4, solu.findPoisonedDuration([1, 4], 2))
        self.assertEqual(3,solu.findPoisonedDuration([1,2],2))

    def test_transpose(self):
        solu =ArraySolution()
        A =[[1,2,3],[4,5,6],[7,8,9]]
        TA =[[1,4,7],[2,5,8],[3,6,9]]
        self.assertEqual(solu.transpose(A),TA)

    def test_subdomainVisits(self):
        solu =ArraySolution()
        A=['9001 discuss.leetcode.com']
        res =solu.subdomainVisits(A)
        self.assertEqual(len(res),3)
        for str in res:
            print(str)




if __name__=='__main__':
    ArraySolutionTest.main()
