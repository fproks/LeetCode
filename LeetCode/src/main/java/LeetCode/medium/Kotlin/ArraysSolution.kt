package LeetCode.medium.Kotlin

import java.util.*

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
    fun dailyTemperatures2(temperatures: IntArray): IntArray{
        val stack =Array<Int>(temperatures.size,{0})
        var top =-1
        val ret =Array<Int>(temperatures.size,{0})
        for(i in temperatures.indices){
            while (top >-1 && temperatures[i] >temperatures[stack[top]]){
                var idx = stack[top--]
                ret[idx] =i-idx
            }
            stack[++top] =i
        }
        return  ret.toIntArray()
    }


}