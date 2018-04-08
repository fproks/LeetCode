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

    @Test
     fun uniqueMorseRepresentations(){
        val arrays = arrayOf<String>("gin","zen","gig","msg")
        Assert.assertEquals(2, solution.uniqueMorseRepresentations(arrays))
    }
}