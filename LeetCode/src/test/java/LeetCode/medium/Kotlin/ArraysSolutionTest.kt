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

    @Test
    fun maxRotateFunction() {
        assertEquals(26, solutionKotlin.maxRotateFunction(intArrayOf(4, 3, 2, 6)))
        assertEquals(0, solutionKotlin.maxRotateFunction(intArrayOf(100)))
    }

    @Test
    fun findRightInterval() {
        // assertArrayEquals(intArrayOf(-1),solutionKotlin.findRightInterval(arrayOf(intArrayOf(1,2))))
        assertArrayEquals(
            intArrayOf(-1, 2, -1), solutionKotlin.findRightInterval(
                arrayOf(
                    intArrayOf(1, 4),
                    intArrayOf(2, 3),
                    intArrayOf(3, 4)
                )
            )
        )
        assertArrayEquals(
            intArrayOf(-1, 0, 1), solutionKotlin.findRightInterval(
                arrayOf(
                    intArrayOf(3, 4),
                    intArrayOf(2, 3),
                    intArrayOf(1, 2)
                )
            )
        )
    }

    @Test
    fun wiggleSort() {
        print(solutionKotlin.wiggleSort(intArrayOf(1, 3, 2, 2, 3, 1)))
    }

    @Test
    fun minOperations() {
        assertEquals(
            4,
            solutionKotlin.minOperations(intArrayOf(5, 6, 4, 3, 1, 2), intArrayOf(6, 3, 3, 1, 4, 5, 3, 4, 1, 3, 4))
        )
    }

    @Test
    fun minElements() {
        assertEquals(solutionKotlin.minElements(intArrayOf(1, -1, 1), 3, -4), 2)
    }

    @Test
    fun largest1BorderedSquare() {
        assertEquals(
            solutionKotlin.largest1BorderedSquare(
                arrayOf(
                    intArrayOf(1, 1, 1),
                    intArrayOf(1, 0, 1),
                    intArrayOf(1, 1, 1)
                )
            ), 9
        )
        assertEquals(
            solutionKotlin.largest1BorderedSquare(
                arrayOf(intArrayOf(1, 1, 0, 0))
            ), 1
        )
        assertEquals(
            solutionKotlin.largest1BorderedSquare(
                arrayOf(
                    intArrayOf(0, 1, 1, 1, 1, 0),
                    intArrayOf(1, 1, 0, 1, 1, 0),
                    intArrayOf(1, 1, 0, 1, 0, 1),
                    intArrayOf(1, 1, 0, 1, 1, 1),
                    intArrayOf(1, 1, 0, 1, 1, 1),
                    intArrayOf(1, 1, 1, 1, 1, 1),
                    intArrayOf(1, 0, 1, 1, 1, 1),
                    intArrayOf(0, 0, 1, 1, 1, 1),
                    intArrayOf(1, 1, 1, 1, 1, 1)
                )
            ), 16
        )
    }

}


class LexicalOrderSolutionTest {

    @Test
    fun lexicalOrderTest() {
        assertArrayEquals(
            intArrayOf(1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9),
            LexicalOrderSolution().lexicalOrder(13).toIntArray()
        )
    }
}
