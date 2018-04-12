package LeetCode.easy.Kotlin

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

class StringSoultionTest {
    companion object {
        val solution = StringSoultion()
    }

    @Test
    fun rotateString() {

        Assert.assertTrue(
                solution.rotateString("abcde", "cdeab"))

        Assert.assertFalse(
                solution.rotateString("abcde", "adced")
        )
        Assert.assertTrue(
                solution.rotateString("gcmbf", "fgcmb")
        )
    }

  /*  @Test
    fun  letterCasePermutation(){
        var S ="a1b2"
        val tmp = solution.letterCasePermutation(S)
        Assert.assertEquals(4,tmp.size)
        Assert.assertTrue(tmp.contains("a1b2"))
        tmp.forEach(::println)
    }*/
}