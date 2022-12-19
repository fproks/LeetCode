package Kotlin.offer

import java.util.*

class CQueue() {
    private val one = LinkedList<Int>()
    private val two = LinkedList<Int>()
    fun appendTail(value: Int) {
        one.push(value)
    }

    fun deleteHead(): Int {
        if (one.isEmpty()) return -1
        while (one.isNotEmpty()) two.push(one.pop())
        val value = two.pop()
        while (two.isNotEmpty()) one.push(two.pop())
        return value
    }

}

class MinStack() {

    /** initialize your data structure here. */
    data class MinStackData(val value: Int, val min: Int)

    private val minstack = LinkedList<MinStackData>()


    fun push(x: Int) {
        if (minstack.isNotEmpty()) {
            minstack.push(MinStackData(x, Math.min(minstack.first.min, x)))
        }else minstack.push(MinStackData(x,x))
    }

    fun pop() {
        minstack.pop()
    }

    fun top(): Int {
        return minstack.first.value
    }

    fun min(): Int {
        return minstack.first.min
    }

}