package LeetCode.medium.Kotlin

import kotlin.math.abs
import kotlin.math.min


class StringSloutionKotlin {
    fun partitionLabels(s: String): List<Int> {
        val letters = s.toCharArray().toSet()
        val ranges = letters.map { Pair(s.indexOf(it), s.lastIndexOf(it) + 1) }.sortedBy { it.first }
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
        val letterMap = mutableMapOf<Char, StringBuilder>()
        for (c in T) {
            if (letterMap.containsKey(c))
                letterMap[c]?.append(c)
            else
                letterMap[c] = StringBuilder().append(c)
        }

        val result = StringBuilder()
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
        return (plateArray.indices).none { wordArray[it] < plateArray[it] }
    }

    private fun compileNumWithChar(S: String): IntArray {
        val wordArray = Array(26) { 0 }
        S.lowercase().filter { it in 'a'..'z' }.forEach {
            wordArray[(it - 'a')]++
        }
        return wordArray.toIntArray()
    }

    fun shortestToChar(s: String, c: Char): IntArray {
        val indexArray = IntArray(s.length) { -1 }
        var pos = -s.length
        for (i in s.indices) {
            if (s[i] == c) pos = i
            indexArray[i] = i - pos
        }
        for (i in s.length - 1 downTo 0) {
            if (s[i] == c) pos = i
            indexArray[i] = min(indexArray[i], abs(i - pos))
        }
        return indexArray
    }

    fun lengthLongestPath(input: String): Int {
        if (input.isEmpty()) return 0
        val words = input.split("\n")
        val pathLens = IntArray(words.size + 1)
        pathLens[0] = -1
        var ans = 0
        for (word in words) {
            val leve = word.lastIndexOf('\t') + 1 + 1
            val nameLen = word.length - (leve - 1)
            pathLens[leve] = pathLens[leve - 1] + 1 + nameLen
            if (word.contains(".")) ans = ans.coerceAtLeast(pathLens[leve])
        }
        return ans
    }


    fun ambiguousCoordinates(s: String): List<String> {
        val res = ArrayList<String>()
        val n = s.substring(1, s.length - 1)
        for (i in 1 until n.length) {
            val first = StringBuilder(n.substring(0, i)).toString()
            val sec = StringBuilder(n.substring(i, n.length)).toString()
            res.add("(${first},${sec})")
            for (f in 0..first.length) {
                val fp = StringBuilder(first).insert(f, ".").toString()
                if (filterDouble(fp)) {
                    for (s in 1 until sec.length) {
                        val sp = StringBuilder(sec).insert(s, ".").toString()
                        if (filterDouble(sp)) {
                            res.add("(${fp},${sp})")
                        }
                    }
                } else continue

            }
        }

        return res
    }

    fun filterDouble(s: String): Boolean {
        if (s.isEmpty()) return false
        if (!s.contains(".")) {
            if (s.length != 1) return !s.startsWith("0")
        } else {
            if (s.length < 3) return false
            try {
                if (s.toDouble() == 0.0) return false
            } catch (e: NumberFormatException) {
                return false
            }
            val idx = s.indexOf(".")
            if (idx > 1 && s.startsWith("0")) return false
            try {
                if (s.substring(idx + 1).toInt() == 0) return false
            } catch (e: NumberFormatException) {
                return false
            }
            if (s.endsWith("0")) return false
        }
        return true
    }

    fun removeSubfolders(folder: Array<String>): List<String> {
        folder.sort()
        val tmp = ArrayList<String>()
        for (i in folder) {
            if (tmp.any { i.startsWith("$it/") }) continue
            else tmp.add(i)
        }
        return tmp
    }

    fun canChange(start: String, target: String): Boolean {
        val s = start.replace("_","")
        val e = target.replace("_","")
        if (e != s) return false
        var i =0
        var j =0
        while (i<start.length){
            if (start[i]=='_'){
                i++
                continue
            }
            while (target[j]=='_'){
                j++
            }
            if (i!=j && (start[i]=='L')==(i<j))
                return false
            i++
            j++
        }
        return true

    }

}
