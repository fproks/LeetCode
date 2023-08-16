package LeetCode.easy.Kotlin

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class ArraysSolutionTest : TestCase() {

    fun testNumberOfLines() {
        Assert.assertArrayEquals(intArrayOf(3,60),ArraysSolution().numberOfLines(intArrayOf(
            10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10
        ),"abcdefghijklmnopqrstuvwxyz"))
        Assert.assertArrayEquals(intArrayOf(2,4),ArraysSolution().numberOfLines(intArrayOf(
            4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10
        ),"bbbcccdddaaa"))
    }

    fun testMaximumWealth() {
        assertEquals(6, ArraysSolution().maximumWealth(arrayOf(intArrayOf(1, 2, 3), intArrayOf(3, 2, 1))))
        assertEquals(10, ArraysSolution().maximumWealth(arrayOf(intArrayOf(1, 5), intArrayOf(3, 7), intArrayOf(3, 5))))
        assertEquals(
            17,
            ArraysSolution().maximumWealth(arrayOf(intArrayOf(2, 8, 7), intArrayOf(7, 3, 1), intArrayOf(1, 9, 5)))
        )
    }


    fun testnumberOfPairs() {
        Assert.assertArrayEquals(ArraysSolution().numberOfPairs(intArrayOf(1, 3, 2, 1, 3, 2, 2)), intArrayOf(3, 1))
    }


    fun testminNumberOfHours() {
        assertEquals(0, ArraysSolution().minNumberOfHours(2, 4, intArrayOf(1), intArrayOf(3)))
    }

    fun testDiagonalSum() {
        assertEquals(
            25,
            ArraysSolution().diagonalSum(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
        )
        assertEquals(
            8, ArraysSolution().diagonalSum(
                arrayOf(
                    intArrayOf(1, 1, 1, 1), intArrayOf(1, 1, 1, 1), intArrayOf(1, 1, 1, 1),
                    intArrayOf(1, 1, 1, 1)
                )
            )
        )
    }


    fun testFindTheLongestBalancedSubstring() {
        assertEquals(2,ArraysSolution().findTheLongestBalancedSubstring("010"))
    }
}