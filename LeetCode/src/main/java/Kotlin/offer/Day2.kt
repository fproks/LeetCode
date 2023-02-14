package Kotlin.offer

import LeetCode.struct.TreeNode
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.math.max

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun reversePrint(head: ListNode?): IntArray {
        val stack = Stack<Int>()
        var ptr = head
        while (ptr != null) {
            stack.push(ptr.`val`)
            ptr = ptr.next
        }
        return stack.reversed().toIntArray()
    }


    fun reverseList(head: ListNode?): ListNode? {
        var ptr = head
        var headptr = head
        var nl: ListNode? = null
        while (headptr != null) {
            ptr = headptr.next
            headptr.next = nl
            nl = headptr
            headptr = ptr
        }
        return nl
    }

    fun replaceSpace(s: String): String {
        val sb = StringBuilder()
        for (c in s) {
            if (c == ' ') {
                sb.append("%20")
            } else
                sb.append(c)
        }
        return sb.toString()
    }

    fun reverseLeftWords(s: String, n: Int): String {
        return "${s.substring(n)}${s.substring(0, n)}"
    }

    fun findRepeatNumber(nums: IntArray): Int {
        val set = HashSet<Int>()
        for (i in nums) {
            if (set.contains(i)) return i
            set.add(i)
        }
        return 0
    }

    fun search(nums: IntArray, target: Int): Int {
        return nums.count { it == target }
    }

    fun missingNumber(nums: IntArray): Int {
        if (nums.isEmpty())
            return -1
        var left = 0
        var right = nums.size - 1
        while (left <= right) {
            val mid = (left + right) / 2
            if (nums[mid] > mid) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return right + 1
    }


    fun findNumberIn2DArray(matrix: Array<IntArray>, target: Int): Boolean {
        val y = matrix.size
        if (y == 0) return false
        val x = matrix[0].size
        if (x == 0) return false
        var row = 0
        var col = x - 1
        while (row < y && col >= 0) {
            when {
                matrix[row][col] == target -> return true
                matrix[row][col] < target -> row++
                else -> col--
            }
        }
        return false
    }

    fun minArray(numbers: IntArray): Int {
        return numbers.sorted()[0]
    }

    fun firstUniqChar(s: String): Char {
        val base = IntArray(26)
        for (i in s) base[i - 'a']++
        for (i in s)
            if (base[i - 'a'] == 0) return i
        return ' '

    }

    fun levelOrder(root: TreeNode?): IntArray {
        val queue = LinkedList<TreeNode?>()
        queue.add(root)
        val res = ArrayList<Int>()
        while (queue.isNotEmpty()) {
            val tmp = queue.poll()
            tmp?.let {
                res.add(it.`val`)
                queue.add(it.left)
                queue.add(it.right)
            }
        }
        return res.toIntArray()
    }

    fun levelOrder2(root: TreeNode?): List<List<Int>> {
        data class kv(val root: TreeNode, val ce: Int)

        val queue = LinkedList<kv>()
        val res = LinkedList<LinkedList<Int>>()
        root?.let { queue.add(kv(root, 1)) }
        while (queue.isNotEmpty()) {
            val tmp = queue.poll()
            while (tmp.ce > res.size) res.add(LinkedList())
            res[tmp.ce - 1].add(tmp.root.`val`)
            tmp.root.left?.let { queue.add(kv(it, tmp.ce + 1)) }
            tmp.root.right?.let { queue.add(kv(it, tmp.ce + 1)) }
        }
        return res
    }

    fun levelOrder3(root: TreeNode?): List<List<Int>> {
        data class kv(val root: TreeNode, val ce: Int)

        val queue = LinkedList<kv>()
        val res = LinkedList<LinkedList<Int>>()
        root?.let { queue.add(kv(root, 1)) }
        while (queue.isNotEmpty()) {
            val tmp = queue.poll()
            while (tmp.ce > res.size) res.add(LinkedList())
            res[tmp.ce - 1].add(tmp.root.`val`)
            tmp.root.left?.let { queue.add(kv(it, tmp.ce + 1)) }
            tmp.root.right?.let { queue.add(kv(it, tmp.ce + 1)) }
        }
        for (i in 0..res.size - 1) {
            if (i % 2 == 1) res[i].reverse()
        }
        return res
    }

    fun isSubStructure(A: TreeNode?, B: TreeNode?): Boolean {
        fun compare(a: TreeNode?, b: TreeNode?): Boolean {
            if (b == null) return true
            if (a == null || a.`val` != b.`val`) return false
            return compare(a.left, b.left) && compare(a.right, b.right)
        }
        if (A == null || B == null) return false
        return compare(A, B) or isSubStructure(A.left, B) or isSubStructure(A.right, B)
    }

    fun mirrorTree(root: TreeNode?): TreeNode? {
        if (root == null) return null
        val tmp = TreeNode(root.`val`)
        if (root.right != null) tmp.left = mirrorTree(root.right)
        if (root.left != null) tmp.right = mirrorTree(root.left)
        return tmp
    }

    fun isSymmetric(root: TreeNode?): Boolean {
        fun compare(a: TreeNode?, b: TreeNode?): Boolean {
            if (a == null && b == null) return true
            if (a == null || b == null || a.`val` != b.`val`) return false
            return compare(a.left, b.right) && compare(a.right, b.left)
        }
        if (root == null) return true
        return compare(root, root)
    }


}

class FibSolution {
    private val res = IntArray(101) { 0 }

    init {
        res[1] = 1
        for (i in 2..100) res[i] = (res[i - 1] + res[i - 2]) % 1000000007
    }

    fun fib(n: Int): Int {
        return res[n]
    }

    fun numWays(n: Int): Int {
        if (n == 1 || n == 0) return 1
        if (n == 2) return 2
        return (numWays(n - 1) + numWays(n - 2)) % 1000000007
    }

    fun maxProfit(prices: IntArray): Int {
        val len = prices.size
        if (len <= 1) return 0
        val dp = Array(len) { IntArray(2) }
        dp[0][0] = prices[0]
        dp[0][1] = 0
        for (i in 1 until len) {
            dp[i][0] = Math.min(prices[i], dp[i - 1][0])
            dp[i][1] = Math.max(prices[i] - dp[i - 1][0], dp[i - 1][1])
        }
        return dp[len - 1][1]
    }

    fun maxSubArray(nums: IntArray): Int {
        var res = nums[0]
        for (i in 1 until nums.size) {
            nums[i] += max(nums[i - 1], 0)
            res = max(res, nums[i])
        }
        return res
    }

    fun maxValue(grid: Array<IntArray>): Int {
        val dp = Array(grid.size + 1) { IntArray(grid[0].size + 1) { 0 } }
        dp[1][1] = grid[0][0]
        for (i in 1..grid.size)
            for (j in 1..grid[0].size)
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1]
        return dp[grid.size][grid[0].size]

    }
}