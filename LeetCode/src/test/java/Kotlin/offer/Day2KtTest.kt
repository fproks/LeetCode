package Kotlin.offer

import org.junit.Test

import org.junit.Assert.*

class Day2KtTest {

    @Test
    fun movingCount() {
        assertEquals(3, movingCount(2,3,1))
        assertEquals(1, movingCount(3,1,0))
    }

    @Test
    fun minNumber() {
        assertEquals("102", Kotlin.offer.minNumber(intArrayOf(10,2)))
    }
}