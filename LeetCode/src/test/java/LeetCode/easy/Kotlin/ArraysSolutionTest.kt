package LeetCode.easy.Kotlin

import junit.framework.TestCase
import org.junit.Assert

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
        assertEquals(6,ArraysSolution().maximumWealth(arrayOf(intArrayOf(1,2,3), intArrayOf(3,2,1))))
        assertEquals(10,ArraysSolution().maximumWealth(arrayOf(intArrayOf(1,5), intArrayOf(3,7), intArrayOf(3,5))))
        assertEquals(17,ArraysSolution().maximumWealth(arrayOf(intArrayOf(2,8,7), intArrayOf(7,3,1), intArrayOf(1,9,5))))
    }
}