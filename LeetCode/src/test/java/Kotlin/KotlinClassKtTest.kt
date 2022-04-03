package Kotlin

import junit.framework.TestCase
import org.junit.Assert

class KotlinClassKtTest : TestCase() {

    fun testWinnerOfGame() {
        assertTrue(winnerOfGame("AAABABB"))
    }

    fun testDistanceBetweenBusStops() {
        assertEquals(3, distanceBetweenBusStops(intArrayOf(1,2,3,4),0,2))
        assertEquals(4, distanceBetweenBusStops(intArrayOf(1,2,3,4),0,3))
    }

    fun testMaxConsecutiveAnswers() {
        assertEquals(3, maxConsecutiveAnswers("TFFT",1))
    }

    fun testBusiestServers() {
      Assert.assertArrayEquals(intArrayOf(1), busiestServers(3, intArrayOf(1,2,3,4), intArrayOf(5,2,3,3,3)).toIntArray())
    }

    fun testNextGreatestLetter() {
        assertEquals('c', nextGreatestLetter(charArrayOf('c','f','j'),'a'))
    }

    fun testCountPoints() {
        Assert.assertArrayEquals(intArrayOf(2,3,2,4),
            countPoints(
                arrayOf(intArrayOf(1,1), intArrayOf(2,2), intArrayOf(3,3), intArrayOf(4,4), intArrayOf(5,5)),
                arrayOf(intArrayOf(1,2,2), intArrayOf(2,2,2), intArrayOf(4,3,2), intArrayOf(4,3,3))
        ))
    }
}