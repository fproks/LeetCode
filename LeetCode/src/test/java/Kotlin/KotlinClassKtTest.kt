package Kotlin

import junit.framework.TestCase

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
}