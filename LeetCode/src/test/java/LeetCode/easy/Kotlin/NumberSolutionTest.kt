package LeetCode.easy.Kotlin

import org.junit.Assert
import org.junit.Test

class NumberSolutionTest{
    companion object {
        val solution =NumberSolution()
    }

    @Test
    fun  selfDividingNumbers(){
        //val arr = intArrayOf(1,2,3)
        Assert.assertArrayEquals(intArrayOf(1,2,3,4,5,6,7,8,9,11,12,15,22), solution.selfDividingNumbers(1,22).toIntArray())
    }

    @Test
    fun hasAlternatingBits(){
        Assert.assertTrue(solution.hasAlternatingBits(5))
        Assert.assertFalse(solution.hasAlternatingBits(7))
        Assert.assertFalse(solution.hasAlternatingBits(11))
        Assert.assertTrue(solution.hasAlternatingBits(10))
    }
}