package Kotlin

import kotlin.math.min


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
    var count=0
    for (i in 0.. colors.length-3){
        if (colors.subSequence(i,i+3) == "AAA")count++
        if(colors.subSequence(i,i+3) == "BBB")count--
    }
    return  count>0
}

fun distanceBetweenBusStops(distance: IntArray, start: Int, destination: Int): Int {
    var count=0
    var midCount=0
    var begin=start
    var end=destination
    if(start>destination)
        begin=end.apply {
            end=begin
        }
    for (i in distance.indices){
        count+=distance[i]
        if (i in begin until end){midCount+=distance[i]}
    }
    return  min(midCount,count-midCount)
}