package LeetCode.medium.Kotlin


class StringSloutionKotlin{

    fun partitionLabels(S: String): List<Int> {
        val letters =S.toCharArray().toSet()
        val ranges =letters.map { Pair(S.indexOf(it),S.lastIndexOf(it)+1) }.sortedBy { it.first }
        val reduceRanges = mutableListOf(ranges.first())
        ranges.forEach {
            if(it.first >= reduceRanges.last().second)
                reduceRanges+=it
            else {
                val pair = reduceRanges.removeAt(reduceRanges.size - 1)
                reduceRanges +=Pair(pair.first, maxOf(it.second,pair.second))
            }

        }
        return  reduceRanges.map { it.second-it.first }
    }

    fun customSortString(S: String, T: String): String {
        var letterMap = mutableMapOf<Char,StringBuilder>()
        for (c in T){
            if(letterMap.containsKey(c))
                letterMap[c]?.append(c)

            else
                letterMap[c]=StringBuilder().append(c)
        }

        var result =StringBuilder()
        for (c in S){
            result.append(letterMap[c])
            letterMap.remove(c)
        }
        for (c in letterMap){
            result.append(c.value)
        }
        return  result.toString()
    }



}