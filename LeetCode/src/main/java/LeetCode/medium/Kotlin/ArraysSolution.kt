package LeetCode.medium.Kotlin

import java.util.*
import kotlin.collections.ArrayList

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

    fun maxRotateFunction(nums: IntArray): Int {
        val sum=nums.sum()
        var f0=0
        for ( i in nums.indices){
            f0+=nums[i]*i
        }
        var res =f0

        for (i in 1 until nums.size){
            f0=f0+sum-nums.size*nums[nums.size-i]
            if (f0>res)res=f0
        }
        return  res
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

class PickSolution(val nums: IntArray) {
    fun pick(target: Int): Int {
        val count=nums.count { it==target }
        val r =Random().nextInt(count)
        var idx=0
        for ( i in nums.indices){
            if (nums[i]==target){
                if(idx==r)return  i
                else idx++
            }
        }
        return  -1
    }


    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        val list =kotlin.collections.ArrayList<List<Int>>()
        val m =heights.size
        if(m<1)return list
        val n =heights[0].size
        val pacific = Array(m){BooleanArray(n){false} }
        val atlantic =Array(m){BooleanArray(n){false} }
        for ( i in heights.indices){
            pacificAtlanticDfs(heights,i,0,pacific,heights[i][0])
            pacificAtlanticDfs(heights,i,n-1,atlantic,heights[i][n-1])
        }
        for (i in 0 until n){
            pacificAtlanticDfs(heights,0,i,pacific,heights[0][i])
            pacificAtlanticDfs(heights,m-1,i,atlantic,heights[m-1][i])
        }

        for (i in 0 until m){
            for (j in 0 until n){
                if(pacific[i][j] && atlantic[i][j])list.add(listOf(i,j))
            }
        }
        return  list

    }
    private fun pacificAtlanticDfs(heights: Array<IntArray>, x :Int, y:Int, visited:Array<BooleanArray>, pre: Int){
        if (x<0 || y<0 || x>=heights.size || y>=heights[0].size || visited[x][y] || heights[x][y] <pre)
            return
        visited[x][y]=true
        pacificAtlanticDfs(heights, x+1, y, visited, pre)
        pacificAtlanticDfs(heights, x-1, y, visited, pre)
        pacificAtlanticDfs(heights, x, y+1, visited, pre)
        pacificAtlanticDfs(heights, x, y-1, visited, pre)
    }

}
