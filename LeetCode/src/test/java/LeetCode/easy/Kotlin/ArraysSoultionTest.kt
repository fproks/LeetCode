package LeetCode.easy.Kotlin

import org.junit.Assert
import org.junit.Test

class  ArraysSoultionTest{
    companion object {
        val  solution=ArraysSolution()
    }

    @Test
    fun countNumberOfOne(){
        Assert.assertEquals(2, solution.countNumberOfOne(10))
    }
}