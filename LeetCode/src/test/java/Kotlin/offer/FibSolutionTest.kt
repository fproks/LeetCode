package Kotlin.offer

import org.junit.Test

import org.junit.Assert.*

class FibSolutionTest {
    val solution = FibSolution()

    @Test
    fun lengthOfLongestSubstring() {
        assertEquals(solution.lengthOfLongestSubstring("abcabcbb"), 3)
    }
}