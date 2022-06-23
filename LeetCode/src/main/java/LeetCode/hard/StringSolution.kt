package LeetCode.hard

class StringSolution {
    fun findSubstring(s: String, words: Array<String>): List<Int> {
        val map = HashMap<String, Int>()
        for (w in words)
            map[w] = map.getOrDefault(w, 0) + 1
        val res = ArrayList<Int>()
        val wordLength = words[0].length
        val wordsSize = words.size
        if (s.length < wordLength * wordsSize) return res

        for (i in 0 .. s.length - wordLength * wordsSize) {
            val tmp = HashMap(map)
            for (j in i until i + wordLength * wordsSize step wordLength) {
                val sub = s.substring(j, j + wordLength)
                if (tmp.contains(sub) && tmp[sub]!! > 0) {
                    tmp[sub] = tmp[sub]!! - 1
                    if (j == i + wordLength * (wordsSize-1))
                        res.add(i)
                } else
                    break
            }

        }
        return res

    }
}