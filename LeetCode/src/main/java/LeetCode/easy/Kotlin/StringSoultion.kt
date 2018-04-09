package LeetCode.easy.Kotlin
//796. Rotate String
class StringSoultion {
    fun rotateString(A: String, B: String): Boolean {
        if(A=="" && B=="")return true
        for ((index,c) in A.withIndex()){
            if(c ==B[0] && index!=0 ){
                val sub1 =A.substring(0,index)
                val sub2 = A.substring(index)
                if(B==sub2+sub1) return true
            }
        }
        return false
    }
}