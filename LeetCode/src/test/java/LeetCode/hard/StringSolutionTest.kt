package LeetCode.hard

import org.junit.Test

import org.junit.Assert.*

class StringSolutionTest {

    @Test
    fun findSubstring() {
   /*     assertArrayEquals(
            intArrayOf(0, 9),
            StringSolution().findSubstring("barfoothefoobarman", arrayOf("foo", "bar")).toIntArray()
        )
        assertArrayEquals(
            intArrayOf(6, 9, 12),
            StringSolution().findSubstring("barfoofoobarthefoobarman", arrayOf("bar", "foo", "the")).toIntArray()
        )
        assertArrayEquals(
            intArrayOf(),
            StringSolution().findSubstring("wordgoodgoodgoodbestword", arrayOf("word","good","best","word")).toIntArray()
        )*/
        assertArrayEquals(
            intArrayOf(8),
            StringSolution().findSubstring("wordgoodgoodgoodbestword", arrayOf("word","good","best","good")).toIntArray()
        )
    }
}