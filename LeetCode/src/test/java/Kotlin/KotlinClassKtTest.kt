package Kotlin

import junit.framework.TestCase
import org.junit.Assert

class KotlinClassKtTest : TestCase() {

    fun testWinnerOfGame() {
        assertTrue(winnerOfGame("AAABABB"))
    }

    fun testDistanceBetweenBusStops() {
        assertEquals(3, distanceBetweenBusStops(intArrayOf(1, 2, 3, 4), 0, 2))
        assertEquals(4, distanceBetweenBusStops(intArrayOf(1, 2, 3, 4), 0, 3))
    }

    fun testMaxConsecutiveAnswers() {
        assertEquals(3, maxConsecutiveAnswers("TFFT", 1))
    }

    fun testBusiestServers() {
        Assert.assertArrayEquals(
            intArrayOf(1),
            busiestServers(3, intArrayOf(1, 2, 3, 4), intArrayOf(5, 2, 3, 3, 3)).toIntArray()
        )
    }

    fun testNextGreatestLetter() {
        assertEquals('c', nextGreatestLetter(charArrayOf('c', 'f', 'j'), 'a'))
    }

    fun testCountPoints() {
        Assert.assertArrayEquals(
            intArrayOf(2, 3, 2, 4),
            countPoints(
                arrayOf(intArrayOf(1, 1), intArrayOf(2, 2), intArrayOf(3, 3), intArrayOf(4, 4), intArrayOf(5, 5)),
                arrayOf(intArrayOf(1, 2, 2), intArrayOf(2, 2, 2), intArrayOf(4, 3, 2), intArrayOf(4, 3, 3))
            )
        )
        Assert.assertArrayEquals(
            intArrayOf(3, 2, 2), countPoints(
                arrayOf(intArrayOf(1, 3), intArrayOf(3, 3), intArrayOf(5, 3), intArrayOf(2, 2)),
                arrayOf(intArrayOf(2, 3, 1), intArrayOf(4, 3, 1), intArrayOf(1, 1, 2))
            )
        )
    }

    fun testMinPartitions() {
        assertEquals(3, minPartitions("32"))
    }
}