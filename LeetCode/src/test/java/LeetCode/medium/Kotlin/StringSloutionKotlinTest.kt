package LeetCode.medium.Kotlin

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

class StringSloutionKotlinTest {
    companion object {
        val soultuion = StringSloutionKotlin()
    }

    @Test
    fun shortestToCharTest() {
        assertArrayEquals(intArrayOf(3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0), soultuion.shortestToChar("loveleetcode", 'e'))
        assertArrayEquals(intArrayOf(3, 2, 1, 0), soultuion.shortestToChar("aaab", 'b'))
    }

    @Test
    fun lengthLongestPathTest() {
        assertEquals(20, soultuion.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"))
    }

    @Test
    fun filterDouble() {
        assertTrue(soultuion.filterDouble("2.3"))
        assertEquals(soultuion.ambiguousCoordinates("(123)").size, 4)
    }

    @Test
    fun removeSubfolders() {
        assertArrayEquals(
            arrayOf("/a", "/c/d", "/c/f"),
            soultuion.removeSubfolders(arrayOf("/a", "/a/b", "/c/d", "/c/d/e", "/c/f")).toTypedArray()
        )
    }

}