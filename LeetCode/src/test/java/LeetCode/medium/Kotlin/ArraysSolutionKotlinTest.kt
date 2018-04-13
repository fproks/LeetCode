package LeetCode.medium.Kotlin

import org.junit.Assert
import org.junit.Test

class ArraysSolutionKotlinTest{
    companion object {
        val solution =ArraysSolution()
    }

    @Test
    fun findPoisonedDuration(){
        Assert.assertEquals(3, solution.findPoisonedDuration(intArrayOf(1,2),2))
    }
}