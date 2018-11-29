package LeetCode.easy

import org.junit.Test

import org.junit.Assert.*

class StringSolutionKotlinTest {
    companion object {
        val solution = StringSolutionKotlin()
    }

    @Test
    fun minDeletionSize() {
        val array = arrayOf("cba", "daf", "ghi")
        val array1 = arrayOf("zyx", "wvu", "tsr")
        assertEquals(solution.minDeletionSize(array), 1)
        assertEquals(solution.minDeletionSize(array1), 3)
    }
}