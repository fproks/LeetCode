package Kotlin

import junit.framework.TestCase

class KotlinClassKtTest : TestCase() {

    fun testWinnerOfGame() {
        assertTrue(winnerOfGame("AAABABB"))
    }
}