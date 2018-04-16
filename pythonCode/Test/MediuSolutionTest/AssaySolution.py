from unittest import TestCase

from Solution.MediumSoultion.ArraySolution import *


class ArraySolutionTest(TestCase):

    def test_findPoisonedDuration(self):
        solu =ArraySolution()
        self.assertEqual(4, solu.findPoisonedDuration([1, 4], 2))
        self.assertEqual(3,solu.findPoisonedDuration([1,2],2))




if __name__=='__main__':
    ArraySolutionTest.main()
