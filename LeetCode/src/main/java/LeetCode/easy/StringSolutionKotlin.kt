package LeetCode.easy

class StringSolutionKotlin {

    //771. Jewels and Stones
    fun numJewelsInStones(J: String, S: String): Int {
        val hashJ = J.toHashSet()
        return S.filter { hashJ.contains(it) }.length
    }
}