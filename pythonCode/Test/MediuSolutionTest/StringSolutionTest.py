from unittest import TestCase

from Solution.MediumSoultion.StringSolution import StringSolution


class StringSolutionTest(TestCase):

    def test_minAddToMakeValid(self):
        solution = StringSolution()
        self.assertEqual(1, solution.minAddToMakeValid("())"))
        self.assertEqual(3, solution.minAddToMakeValid("((("))
        self.assertEqual(0, solution.minAddToMakeValid("()"))
        self.assertEqual(4, solution.minAddToMakeValid("()))(("))

    def test_queryString(self):
        solution =StringSolution()
        self.assertFalse(solution.queryString("0110",4))

    def test_longestCommonSubsequence(self):
        solution =StringSolution()
        self.assertEqual(2,solution.longestCommonSubsequence("abc","adc"))
        self.assertEqual(3, solution.longestCommonSubsequence("abgfc", "adbbc"))
        self.assertEqual(4, solution.longestCommonSubsequence("bdcaba", "abcbdab"))

    def test_longestCommonSubsequenceDP(self):
        solution =StringSolution()
        self.assertEqual(2,solution.longestCommonSubsequenceDP("abc","adc"))
        self.assertEqual(3, solution.longestCommonSubsequenceDP("abgfc", "adbbc"))
        self.assertEqual(4,solution.longestCommonSubsequenceDP("bdcaba","abcbdab"))


    def test_minimumDeleteSum(self):
        solution=StringSolution()
        self.assertEqual(231,solution.minimumDeleteSum("sea","eat"))
        self.assertEqual(403, solution.minimumDeleteSum("delete", "leet"))

    def test_camelMatch(self):
        solution=StringSolution()
        self.assertEqual([True,False,True,False,False],solution.camelMatch(["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"],"FoBa"))
        self.assertEqual([True, False, True, True, False],
                         solution.camelMatch(["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"],
                                             "FB"))
        self.assertEqual([False, True, False, False, False],
                         solution.camelMatch(["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"],
                                             "FoBaT"))
