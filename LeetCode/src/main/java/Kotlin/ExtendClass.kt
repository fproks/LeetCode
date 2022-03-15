package Kotlin


import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
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
    val charList = StringBuilder()
    s.forEachIndexed { id, it ->
        run {
            if (it.isLetter()) {
                charList.append(it)
            } else listr.add(id)
        }
    }
    charList.reverse()
    listr.forEach {
        charList.insert(it, s[it])
    }
    return charList.toString()
}

//537. 复数乘法
fun complexNumberMultiply(num1: String, num2: String): String {
    val num1list = num1.split("+")
    val num2list = num2.split("+")
    val num11 = num1list[0].toInt()
    val num12 = num1list[1].removeSuffix("i").toInt()
    val num21 = num2list[0].toInt()
    val num22 = num2list[1].removeSuffix("i").toInt()
    val rest1 = num11 * num21 - num12 * num22
    val rest2 = num11 * num22 + num12 * num21
    return rest1.toString() + "+" + rest2.toString() + "i"

}


fun maximumRequests(n: Int, requests: Array<IntArray>): Int {
    return maximumRequestsDFS(Array(n) { 0 }, requests, 0, 0)
}

fun maximumRequestsDFS(count: Array<Int>, request: Array<IntArray>, cur: Int, chose: Int): Int {

    if (cur >= request.size) return if (count.all { it == 0 }) chose else 0
    var ret = maximumRequestsDFS(count, request, cur + 1, chose)
    count[request[cur][0]] -= 1
    count[request[cur][1]] += 1
    ret = max(ret, maximumRequestsDFS(count, request, cur + 11, chose + 1))
    count[request[cur][1]] -= 1
    count[request[cur][0]] += 1
    return ret

}


fun convert(s: String, numRows: Int): String {
    if (numRows <= 1) return s
    val sb = Array(numRows) { StringBuilder() }
    val nv = 2 * numRows - 2
    for ((i, v) in s.withIndex()) {
        val k = i % nv
        if (k <= numRows - 1) sb[k].append(v)
        else sb[nv - k].append(v)
    }
    val res = StringBuilder()
    sb.forEach { res.append(it) }
    return res.toString()
}


fun nearestPalindromic(n: String): String? {
    val n_n = n.toInt()
    if (n_n <= 10) return (n_n - 1).toString()
    if (n.reversed().toInt() == 1) return (n_n - 1).toString()
    if (n_n == 11) return "9"
    if (n.all { it == '9' }) return (n_n + 2).toString()

    val a = n.substring(0, (n.length + 1) / 2)
    val b = n.substring((n.length + 1) / 2)
    val list = mutableListOf((a.toInt() - 1).toString(), a, (a.toInt() + 1).toString())
    val list1 = list.map { it + it.substring(0, b.length).reversed() }
    return list1.minBy { abs(it.toInt() - n_n) }
}


fun findRestaurant(list1: Array<String>, list2: Array<String>): Array<String> {
    val res = HashMap<Int, ArrayList<String>>()
    val map2 = HashMap<String, Int>()
    list2.withIndex().forEach { (idx, str) ->
        map2[str] = idx
    }
    for ((idx, str) in list1.withIndex()) {
        if (map2.containsKey(str)) {
            val key = idx + map2[str]!!
            if (!res.containsKey(key)) res[key] = ArrayList()
            res[key]?.add(str)
        }
    }
    val arr = res.minBy { it.key }?.value
    return arr!!.toTypedArray()
}

fun countMaxOrSubsets(nums: IntArray): Int {
    var maxValue = 0
    nums.forEach { maxValue = maxValue.or(it) }
    return countMaxOrSubsetsdfs(0, nums, 0, maxValue)
}

fun countMaxOrSubsetsdfs(curIndex: Int, nums: IntArray, curValue: Int, maxValue: Int): Int {
    if (curIndex == nums.size) return if (curValue == maxValue) 1 else 0
    return countMaxOrSubsetsdfs(curIndex + 1, nums, curValue.or(nums[curIndex]), maxValue) +
            countMaxOrSubsetsdfs(curIndex + 1, nums, curValue, maxValue)
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