package LeetCode.easy

class StringSolutionKotlin {

    //771. Jewels and Stones
    fun numJewelsInStones(J: String, S: String): Int {
        val hashJ = J.toHashSet()
        return S.filter { hashJ.contains(it) }.length
    }

    //944 Delete Columns to Make Sorted
    fun minDeletionSize(A: Array<String>): Int {
        val N = A[0].length
        var count = 0
        for (idx in 0 until N) {
            if (!isNoDecreasing(A, idx)) count++
        }
        return count
    }

    private fun isNoDecreasing(A: Array<String>, idx: Int): Boolean {
        var i = 0
        var c = A[0][idx]
        for (str in A) {
            if (str[idx] >= c) c = str[idx]
            else return false
        }
        return true
    }


}