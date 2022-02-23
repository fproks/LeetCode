package Kotlin



import java.io.File
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.text.StringBuilder

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
    if (dominoes.length <= 1) return dominoes
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


fun funJC(n: Int): Int {
    val modNumber = (10.0.pow(9) + 7).toInt()
    var res = 1
    for (i in n downTo 1) {
        res = (res * i).rem(modNumber)
    }
    return res
}

fun isPrimeNumber(n: Int): Boolean {
    if (n == 2) return true
    if (n % 2 == 0 || n == 1) return false
    val p = sqrt(n.toDouble()).toInt()
    for (j in 2..p) if (n % j == 0) return false
    return true
}

fun numPrimeArrangements(n: Int): Int {
    var primeNumber = 0
    var noPrimeNumber = 0
    for (i in 1..n) {
        if (isPrimeNumber(i)) primeNumber++
        else noPrimeNumber++
    }
    val t1 = funJC(noPrimeNumber)
    val t2 = funJC(primeNumber)
    return (t1 * t2).rem((10.0.pow(9) + 7).toInt())
}

fun reverseOnlyLetters(s: String): String {
    val listr = ArrayList<Int>()
    val charList=StringBuilder()
    s.forEachIndexed{id,it->run{
        if (it.isLetter()) {
            charList.append(it)
        }
        else listr.add(id)
    }}
    charList.reverse()
    listr.forEach{
        charList.insert(it,s[it])
    }
    return  charList.toString()
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