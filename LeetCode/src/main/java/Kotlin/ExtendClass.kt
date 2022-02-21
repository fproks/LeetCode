package Kotlin

import java.io.File
import java.lang.StringBuilder
import java.util.Arrays

fun String.swap(index1: Int, index2: Int): String {
    val charArray = this.toCharArray()
    val tmp = charArray[index1]
    charArray[index1] = charArray[index2]
    charArray[index2] = tmp
    return charArratToString(charArray)
}

private fun charArratToString(charArray: CharArray): String {
    var result = ""
    charArray.forEach { it -> result += it }
    return result
}


private fun traverseFileTree(fileName: String) {
    val f = File(fileName)
    val fileTreeWalk = f.walk()
    println(fileTreeWalk.filter { it.isFile }.count())
    fileTreeWalk.filter { it.isDirectory }.forEach { println(it.absolutePath) }

}

//838. 推多米诺
fun pushDominoes(dominoes: String): String {
    if(dominoes.length<=1)return dominoes
    val res = StringBuilder(dominoes)
    for (i in res.indices) {
        if (res[i] == '.') {
            if (i - 1 >= 0 && i + 1 < res.length) {
                if (dominoes[i - 1] == 'R' && dominoes[i + 1] != 'L') res[i] = 'R'
                if (dominoes[i + 1] == 'L' && dominoes[i - 1] != 'R') res[i] = 'L'
            } else {
                if (i == 0 && dominoes[i + 1] == 'L') res[i] = 'L'
                if (i == res.length - 1 && dominoes[i - 1] == 'R') res[i] = 'R'
            }
        }
    }
    return if (res.toString() == dominoes) dominoes
    else pushDominoes(res.toString())
}


fun main(args: Array<String>) {
    //代码重复执行3次
    repeat(3) { println("hello") }
    var i = 0
    with(i) {
        if (i < 10)
            print(toString())
    }.let { println(i) }

    traverseFileTree("C:\\Users\\ezlinho\\Desktop\\te")

}