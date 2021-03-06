package LeetCode.easy.Kotlin

import java.lang.StringBuilder

//796. Rotate String
class StringSoultion {
    fun rotateString(A: String, B: String): Boolean {
        if (A == "" && B == "") return true
        for ((index, c) in A.withIndex()) {
            if (c == B[0] && index != 0) {
                val sub1 = A.substring(0, index)
                val sub2 = A.substring(index)
                if (B == sub2 + sub1) return true
            }
        }
        return false
    }

    fun licenseKeyFormatting(S: String, K: Int): String {
        val builder = StringBuilder()
        var sum = 0
        for (i in S.length - 1 downTo 0) {
            if (S[i] != '-') {
                if (sum == K) {
                    sum = 0
                    builder.insert(0, '-')
                }
                if (S[i].isLowerCase()) builder.insert(0, S[i].toUpperCase())
                else builder.insert(0, S[i])
                sum++
            }
        }
        if (builder.length > 0 && builder[0] == '-') builder.deleteCharAt(0)
        return builder.toString()
    }


}