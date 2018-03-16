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


    //733. Flood Fill
    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, newColor: Int): Array<IntArray> {

        val srMax =image.size-1
        val scMax =image[0].size-1
        if(sr< 0 || sr>srMax) return image
        if(sc <0 || sc>scMax) return  image
        if(newColor<0 || newColor>65536) return image
        if(image[sr][sc]==newColor) return  image

        val oldCol=image[sr][sc]
        image[sr][sc]=newColor
        var tmp =image
        if(sr>0  && image[sr-1][sc]==oldCol)tmp=floodFill(tmp, sr-1, sc, newColor)
        if(sr<srMax && image[sr+1][sc] ==oldCol)tmp=floodFill(tmp,sr+1,sc,newColor)
        if(sc>0  && image[sr][sc-1]==oldCol)tmp =floodFill(tmp, sr, sc-1, newColor)
        if(sc<scMax && image[sr][sc+1] ==oldCol)tmp=floodFill(tmp,sr,sc+1,newColor)
        return tmp
    }



    //762. Prime Number of Set Bits in Binary Representation
    fun countPrimeSetBits(L: Int, R: Int): Int {
        if(L >R) return 0
        val zs = listOf(2,3,5,7,11,13,17,19)
        return (L..R).count{
            zs.contains(countNumberOfOne(it))
        }

    }


    fun countNumberOfOne(number: Int):Int{
        var  tmp =number
        var res =0
        while (tmp!=0) {
            if(tmp.and(1)!=0)
                res++
            tmp=tmp.ushr(1)
        }
        return  res
    }

}