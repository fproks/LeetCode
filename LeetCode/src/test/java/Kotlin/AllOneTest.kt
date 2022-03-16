package Kotlin


import org.junit.Assert.assertEquals
import org.junit.Test

class AllOneTest {

    @Test
    fun  all(){
        val allOne =AllOne()
        allOne.inc("a")
        allOne.inc("b")
        allOne.inc("b")
        allOne.inc("b")
        allOne.inc("b")
        allOne.dec("b")
        allOne.dec("b")
        assertEquals("b",allOne.getMaxKey())
        assertEquals("a",allOne.getMinKey())
    }
}