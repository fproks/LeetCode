package Kotlin

import java.util.PriorityQueue
import java.util.TreeSet
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt


class AllOne() {
    private val strKey = HashMap<String, Int>()
    private val intKey = HashMap<Int, HashSet<String>>()
    private var maxValue = 0
    private var minValue = 0
    fun inc(key: String) {
        if (!strKey.containsKey(key)) strKey[key] = 0
        val value = strKey[key]!! + 1
        strKey[key] = value
        if (!intKey.containsKey(value)) intKey[value] = HashSet()
        intKey[value]!!.add(key)
        if (value != 1) intKey[value - 1]!!.remove(key)
        if (maxValue < value) maxValue = value

    }

    fun dec(key: String) {
        val value = strKey[key]!! - 1
        intKey[value + 1]!!.remove(key)
        if (maxValue == value + 1 && intKey[value + 1]!!.size == 0)
            maxValue = value
        if (value != 0) {
            strKey[key] = value
            intKey[value]!!.add(key)
        } else strKey.remove(key)
    }

    fun getMaxKey(): String {
        if (maxValue == 0) return ""
        return intKey[maxValue]!!.first()
    }

    fun getMinKey(): String {
        if (maxValue == 0) return ""
        for (i in 1..maxValue) {
            if (intKey.containsKey(i) && intKey[i]!!.size > 0) return intKey[i]!!.first()
        }
        return getMaxKey()
    }

}


fun winnerOfGame(colors: String): Boolean {
    var count = 0
    for (i in 0..colors.length - 3) {
        if (colors.subSequence(i, i + 3) == "AAA") count++
        if (colors.subSequence(i, i + 3) == "BBB") count--
    }
    return count > 0
}

fun distanceBetweenBusStops(distance: IntArray, start: Int, destination: Int): Int {
    var count = 0
    var midCount = 0
    var begin = start
    var end = destination
    if (start > destination)
        begin = end.apply {
            end = begin
        }
    for (i in distance.indices) {
        count += distance[i]
        if (i in begin until end) {
            midCount += distance[i]
        }
    }
    return min(midCount, count - midCount)
}

fun maxConsecutiveAnswers(answerKey: String, k: Int): Int {
    var left = 0
    var right = 0
    var t = 0
    var f = 0
    while (right < answerKey.length) {
        if (answerKey[right] == 'T') t++
        else f++
        if (t.coerceAtMost(f) > k) {
            if (answerKey[left] == 'T') t--
            else f--
            left++
        }
        right++
    }
    return right - left

}


fun busiestServers(k: Int, arrival: IntArray, load: IntArray): List<Int> {
    val N = 100010
    val cnts = IntArray(N) { 0 }
    val n = arrival.size
    var max = 0
    val busy = PriorityQueue { a: IntArray, b: IntArray -> a[1] - b[1] }
    val free = TreeSet<Int>()
    for (i in 0 until k) free.add(i)
    for (i in 0 until n) {
        val start = arrival[i]
        val end = start + load[i]
        while (!busy.isEmpty() && busy.peek()[1] <= start) free.add(busy.poll()[0])
        var u = free.ceiling(i % k)
        if (u == null) u = free.ceiling(0)
        if (u == null) continue
        free.remove(u)
        busy.add(intArrayOf(u, end))
        max = max.coerceAtLeast(++cnts[u])
    }
    val ans = ArrayList<Int>()
    for (i in 0 until k) if (cnts[i] == max) ans.add(i)
    return ans

}

//744. 寻找比目标字母大的最小字母
fun nextGreatestLetter(letters: CharArray, target: Char): Char {
    return letters.find { it > target } ?: return letters[0]
}

fun countPoints(points: Array<IntArray>, queries: Array<IntArray>): IntArray {
    val array = ArrayList<Int>()
    for (i in queries) {
        val x = i[0]
        val y = i[1]
        val s = i[2]
        val count = points.count { s>= sqrt((it[0].toDouble() - x).pow(2.0) + (it[1].toDouble() - y).pow(2.0)) }
        array.add(count)
    }
    return array.toIntArray()
}

//307. 区域和检索 - 数组可修改
class NumArray(val nums: IntArray) {
    val sumArray=IntArray(nums.size+1){0}
    init {
       for (i in nums.indices){
           sumArray[i+1]=sumArray[i]+nums[i]
       }
    }

    fun update(index: Int, `val`: Int) {
        val offset =`val`-nums[index]
        for (i in index until sumArray.size){
            sumArray[i+1]+=offset
        }
        nums[index]=`val`
    }

    fun sumRange(left: Int, right: Int): Int {
        return  sumArray[right+1]-sumArray[left]
    }

}