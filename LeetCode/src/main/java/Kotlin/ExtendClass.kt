package Kotlin

import java.io.File


fun  String.swap(index1:Int, index2: Int): String{
    val charArray =this.toCharArray()
    val tmp =charArray[index1]
    charArray[index1] =charArray[index2]
    charArray[index2] =tmp
    return  charArratToString(charArray)
}
private fun charArratToString(charArray: CharArray):String{
     var result =""
    charArray.forEach { it-> result += it }
    return result
}


private  fun  traverseFileTree(fileName: String){
    val  f =File(fileName)
    val fileTreeWalk =f.walk()
   println(fileTreeWalk.filter { it.isFile}.count())
    fileTreeWalk.filter { it.isDirectory }.forEach { println(it.absolutePath) }

}



fun main(args: Array<String>) {
    //代码重复执行3次
    repeat(3){println("hello")}
    var i =0
    with(i){
        if(i <10)
            print(toString())
    }.let { println(i) }

    traverseFileTree("C:\\Users\\ezlinho\\Desktop\\te")

}