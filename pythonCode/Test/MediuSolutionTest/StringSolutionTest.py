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
