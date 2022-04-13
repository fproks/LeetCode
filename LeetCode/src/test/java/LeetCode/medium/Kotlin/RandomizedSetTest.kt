package LeetCode.medium.Kotlin

import junit.framework.TestCase

class RandomizedSetTest : TestCase(){

    fun testAll(){
        val r =RandomizedSet()
        assertTrue(r.insert(0))
        assertTrue(r.insert(1))
        assertTrue(r.remove(0))
        assertTrue(r.insert(2))
        assertTrue(r.remove(1))
        assertEquals(2,r.getRandom())
    }
}