package LeetCode.medium.Kotlin

import  java.util.Queue
import kotlin.random.Random

class Node(var `val`: Int) {
    var children: List<Node?> = listOf()
}

class LevelOrderSolution {
    val res = ArrayList<List<Int>>()
    fun levelOrder(root: Node?): List<List<Int>> {

        val list = ArrayList<Node>()
        if (root != null) {
            list.add(root)
            listOrder(list)
        }
        return res
    }

    fun listOrder(list: List<Node>) {
        val tmp = ArrayList<Int>()
        val listTmp = ArrayList<Node>()
        for (l in list) {
            tmp.add(l.`val`)
            for (ll in l.children) {
                if (ll != null) listTmp.add(ll)
            }
        }
        res.add(tmp)
        if (listTmp.size > 0)
            listOrder(listTmp)
    }
}

class RandomizedSet() {
    val list = ArrayList<Int>()
    val map = HashMap<Int, Int>()
    val random = java.util.Random()

    fun insert(`val`: Int): Boolean {
        return if (map.containsKey(`val`)) false
        else {
            list.add(`val`)
            map[`val`] = list.size - 1
            true
        }
    }

    fun remove(`val`: Int): Boolean {
        return if (!map.containsKey(`val`)) false
        else {
            val idx = map[`val`]
            val last = list[list.size - 1]
            list[idx!!] = last
            list.removeAt(list.size - 1)
            map[last] = idx
            map.remove(`val`)
            true
        }
    }

    fun getRandom(): Int {
        return list[random.nextInt(list.size)]
    }

}

class AuthenticationManager(var timeToLive: Int) {

    val tokenMap = HashMap<String, Int>()

    fun generate(tokenId: String, currentTime: Int) {
        tokenMap[tokenId] = currentTime
    }

    fun renew(tokenId: String, currentTime: Int) {
        tokenMap[tokenId]?.let {
            if (it + timeToLive <= currentTime)
                tokenMap.remove(tokenId)
            else
                tokenMap[tokenId] = currentTime
        }
    }

    fun countUnexpiredTokens(currentTime: Int): Int {
        return tokenMap.count { it.value + timeToLive > currentTime }
    }

}
