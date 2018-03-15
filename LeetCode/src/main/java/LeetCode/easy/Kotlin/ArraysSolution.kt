package LeetCode.easy.Kotlin

class ArraysSolution {


    //766. Toeplitz Matrix
    fun isToeplitzMatrix(matrix: Array<IntArray>): Boolean {
        val HX =matrix.size  //行
        val HY =matrix[0].size  //列

        for (i in 0 until HX-1){
            for (j in 0 until  HY-1){
                if(matrix[i][j]!=matrix[i+1][j+1])
                    return false
            }
        }
        return true
    }

}