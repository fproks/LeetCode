package Kotlin.offer

import org.junit.Test

import org.junit.Assert.*

class Day2KtTest {

    @Test
    fun movingCount() {
        assertEquals(3, movingCount(2,3,1))
        assertEquals(1, movingCount(3,1,0))
    }
}