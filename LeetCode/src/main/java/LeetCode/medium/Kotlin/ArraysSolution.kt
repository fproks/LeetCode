package LeetCode.medium.Kotlin

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class ArraysSolution {

    //739. Daily Temperatures
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        var result = Array(temperatures.size, { 0 })
        for (currI in temperatures.indices) {
            val curr = temperatures[currI]
            for (freI in currI + 1 until temperatures.size) {
                if (temperatures[freI] > curr) {
                    result[currI] = freI - currI
                    break
                }
            }
        }
        return result.toIntArray()
    }

    //    //739. Daily Temperatures
    fun dailyTemperatures2(temperatures: IntArray): IntArray {
        val stack = Array<Int>(temperatures.size, { 0 })
        var top = -1
        val ret = Array<Int>(temperatures.size, { 0 })
        for (i in temperatures.indices) {
            while (top > -1 && temperatures[i] > temperatures[stack[top]]) {
                var idx = stack[top--]
                ret[idx] = i - idx
            }
            stack[++top] = i
        }
        return ret.toIntArray()
    }


    //667. Beautiful Arrangement II
    fun constructArray(n: Int, k: Int): IntArray {
        var arr = IntArray(n)
        var l = 1
        var r = n
        var i = 0
        var tk = k
        while (l <= r) {
            arr[i] = if (tk > 1) if (tk-- % 2 != 0) l++ else r-- else l++
            i++
        }
        return arr
    }

    fun findPoisonedDuration(timeSeries: IntArray, duration: Int): Int {
        var curr = 0
        var sum = 0
        for (time in timeSeries) {
            if (curr <= time) {
                sum += duration
            } else {
                sum += (duration + (time - curr))
            }
            curr = time + duration
        }
        return sum
    }
}

class  LexicalOrderSolution {
    fun lexicalOrder(n: Int): List<Int> {
        val list =kotlin.collections.ArrayList<Int>()
        for ( i in 1 until 10){
            dfs(n,i,list)
        }
        return  list
    }

    fun  dfs(n:Int,target:Int,list : ArrayList<Int>){
        if (target>n)return
        list.add(target)
        val tmp=target*10
        for (i in 0 until 10){
            dfs(n,tmp+i,list)
        }
    }
}