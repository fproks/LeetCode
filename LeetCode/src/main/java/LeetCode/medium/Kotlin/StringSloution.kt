package LeetCode.medium.Kotlin


class StringSloutionKotlin {
    fun partitionLabels(S: String): List<Int> {
        val letters = S.toCharArray().toSet()
        val ranges = letters.map { Pair(S.indexOf(it), S.lastIndexOf(it) + 1) }.sortedBy { it.first }
        val reduceRanges = mutableListOf(ranges.first())
        ranges.forEach {
            if (it.first >= reduceRanges.last().second)
                reduceRanges += it
            else {
                val pair = reduceRanges.removeAt(reduceRanges.size - 1)
                reduceRanges += Pair(pair.first, maxOf(it.second, pair.second))
            }

        }
        return reduceRanges.map { it.second - it.first }
    }

    fun customSortString(S: String, T: String): String {
        var letterMap = mutableMapOf<Char, StringBuilder>()
        for (c in T) {
            if (letterMap.containsKey(c))
                letterMap[c]?.append(c)
            else
                letterMap[c] = StringBuilder().append(c)
        }

        var result = StringBuilder()
        for (c in S) {
            result.append(letterMap[c])
            letterMap.remove(c)
        }
        for (c in letterMap) {
            result.append(c.value)
        }
        return result.toString()
    }

    fun countSubstrings(s: String): Int {
        var count = 0
        if (s.isEmpty()) return count
        for (i in 0 until s.length) {
            count += extendPalindrome(s, i, i)
            count += extendPalindrome(s, i, i + 1)
        }
        return count

    }

    private fun extendPalindrome(s: String, left: Int, right: Int): Int {
        var tmp = 0
        var leftT = left
        var rightT = right
        while (leftT >= 0 && rightT < s.length && s[leftT] == s[rightT]) {
            tmp++
            leftT--
            rightT++
        }
        return tmp
    }

    //748. Shortest Completing Word
    fun shortestCompletingWord(licensePlate: String, words: Array<String>): String {
        val plateArray = compileNumWithChar(licensePlate)
        return words.filter {
            isCompleting(plateArray, compileNumWithChar(it))
        }.sortedBy { it.length }[0]
    }

    private fun isCompleting(plateArray: IntArray, wordArray: IntArray): Boolean {
        return (0 until plateArray.size).none { wordArray[it] < plateArray[it] }
    }

    private fun compileNumWithChar(S: String): IntArray {
        var wordArray = Array(26, { 0 })
        S.toLowerCase().filter { it in 'a'..'z' }.forEach {
            wordArray[(it-'a')]++
        }
        return wordArray.toIntArray()
    }


}