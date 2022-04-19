package LeetCode.medium.Kotlin

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

class StringSloutionKotlinTest {
    companion object {
        val soultuion =StringSloutionKotlin()
    }

    @Test
    fun  shortestToCharTest(){
        assertArrayEquals(intArrayOf(3,2,1,0,1,0,0,1,2,2,1,0), soultuion.shortestToChar("loveleetcode",'e'))
        assertArrayEquals(intArrayOf(3,2,1,0), soultuion.shortestToChar("aaab",'b'))
    }

}