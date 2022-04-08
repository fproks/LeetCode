package LeetCode.medium.Kotlin

import java.util.Queue

class Node(var `val`: Int) {
    var children: List<Node?> = listOf()
}

class LevelOrderSolution {
    val res =ArrayList<List<Int>>()
    fun levelOrder(root: Node?): List<List<Int>> {

        val list=ArrayList<Node>()
        if (root!=null) {
            list.add(root)
            listOrder(list)
        }
        return  res
    }

    fun listOrder(list:List<Node>){
        val tmp=ArrayList<Int>()
        val listTmp=ArrayList<Node>()
        for (l in list){
            tmp.add(l.`val`)
            for (ll in l.children){
                if (ll!=null) listTmp.add(ll)
            }
        }
        res.add(tmp)
        if (listTmp.size>0)
        listOrder(listTmp)
    }
}