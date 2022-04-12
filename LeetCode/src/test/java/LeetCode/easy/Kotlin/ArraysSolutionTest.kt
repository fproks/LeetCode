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
}