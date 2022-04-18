package LeetCode.medium.Kotlin

import org.junit.Assert
import org.junit.Test


import org.junit.Assert.*

class ArraysSolutionTest {
    private val solutionKotlin = ArraysSolution()

    @Test
    fun dailyTemperatures() {
        val temperatures = intArrayOf(73, 74, 75, 71, 69, 72, 76, 73)

        val result = intArrayOf(1, 1, 4, 2, 1, 1, 0, 0)

        Assert.assertArrayEquals(result, solutionKotlin.dailyTemperatures(temperatures))
    }


}

class LexicalOrderSolutionTest {

    @Test
    fun lexicalOrderTest() {
        assertArrayEquals(intArrayOf(1,10,11,12,13,2,3,4,5,6,7,8,9),LexicalOrderSolution().lexicalOrder(13).toIntArray())
    }
}
