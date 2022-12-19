package LeetCode.easy.Kotlin

import org.junit.Test

import org.junit.Assert.*

class UnionSolutionTest {

    @Test
    fun validPath() {
        assertFalse(
            UnionSolution().validPath(
                6, arrayOf(
                    intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(3, 5),
                    intArrayOf(5, 4), intArrayOf(4, 3)
                ), 0, 5
            )
        )
    }
}