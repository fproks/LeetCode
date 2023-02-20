package Kotlin.offer

import LeetCode.struct.TreeNode
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
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

    /*
    * 动态规划 类似跳台阶的问题但是有额外的限制条件
    * 当前一个数为1时可以组合，当前一个数为2 但是后一个数小于6也可以组合。
    * */
    fun translateNum(num: Int): Int {
        if (num < 10) return 1
        val str = num.toString()
        val dp = IntArray(str.length + 1) { 0 }
        dp[0] = 1
        dp[1] = 1
        for (i in 2..str.length) {
            val tmp = str[i - 2] - '0'
            dp[i] = if (tmp == 1 || (tmp == 2 && str[i - 1] - '0' < 6)) dp[i - 1] + dp[i - 2] else dp[i - 1]
        }
        return dp[str.length]
    }

    //剑指 Offer 48. 最长不含重复字符的子字符串
    //最长子串的长度不会大于相同字符的最大间隔
    fun lengthOfLongestSubstring(s: String): Int {
        if (s.length <= 1) return s.length
        val dic = HashMap<Char, Int>()
        var res = 0
        var tmp = 0
        for (j in 0 until s.length) {
            val i = dic.getOrDefault(s[j], -1)
            dic.put(s[j], j)
            tmp = if (tmp < j - i) tmp + 1 else j - i
            res = max(res, tmp)
        }
        return res
    }

    fun reverseWords(s: String): String {
        val tmp = s.trim().split(" ").filter { it.isNotEmpty() }.reversed()
        if (tmp.isEmpty()) return ""
        return tmp.reduce { acc, s1 -> "$acc $s1" }.toString()


    }

}

fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null) return l2
    if (l2 == null) return l1
    if (l1.`val` > l2.`val`) return mergeTwoLists(l2, l1)
    var tmp1 = l1
    var tmp2 = l2
    while (tmp2 != null) {
        if (tmp1!!.next == null) {
            tmp1.next = tmp2
            break
        } else {
            if (tmp2.`val` >= tmp1.next!!.`val`) {
                tmp1 = tmp1.next
                continue
            } else {
                val tmp = tmp1.next
                tmp1.next = tmp2
                tmp2 = tmp2.next
                tmp1 = tmp1.next
                tmp1!!.next = tmp
            }
        }
    }
    return l1
}

fun exchange(nums: IntArray): IntArray {
    var i = 0
    var j = nums.size - 1
    while (i < j) {
        while (i < nums.size && nums[i] % 2 == 1) i++
        while (j > 0 && nums[j] % 2 == 0) j--
        if (i >= j) return nums
        val tmp = nums[i]
        nums[i] = nums[j]
        nums[j] = tmp
    }
    return nums

}

fun twoSum(nums: IntArray, target: Int): IntArray {
    var i = 0
    var j = nums.size
    while (i < j) {
        if (nums[i] + nums[j] == target) return intArrayOf(nums[i], nums[j])
    }

    return intArrayOf(0, 0)
}

fun exist(board: Array<CharArray>, word: String): Boolean {
    fun dfs(board: Array<CharArray>, word: String, i: Int, j: Int, k: Int): Boolean {
        if (k == word.length) return true
        if (i < 0 || i >= board.size || j < 0 || j >= board[0].size) return false
        if (board[i][j] != word[k]) return false
        board[i][j] = '/'
        val res = dfs(board, word, i + 1, j, k + 1) ||
                dfs(board, word, i - 1, j, k) ||
                dfs(board, word, i, j + 1, k) ||
                dfs(board, word, i, j - 1, k)
        board[i][j] = word[k]
        return res
    }
    for (i in board.indices)
        for (j in board[0].indices) {
            if (board[i][j] == word[0]) {
                val tmp = dfs(board, word, i, j, 0)
                if (tmp) return true
            }
        }
    return false
}


fun movingCount(m: Int, n: Int, k: Int): Int {
    fun def(arr: Array<IntArray>, visited: Array<BooleanArray>, m: Int, n: Int): Int {
        if (m < 0 || m >= arr.size || n < 0 || n >= arr[0].size) return 0
        if (arr[m][n] == 0 || visited[m][n]) return 0
        visited[m][n] = true


        return 1 + def(arr, visited, m + 1, n) + def(arr, visited, m, n + 1)
    }

    val arr = Array(m) { IntArray(n) { 0 } }
    val visited = Array(m) { BooleanArray(n) { false } }
    for (i in 0 until m)
        for (j in 0 until n) {
            val tmp = (i / 100) + ((i % 100) / 10) + (i % 10) + (j / 100) + ((j % 100) / 10) + (j % 10)
            if (tmp > k) arr[i][j] = 0
            else arr[i][j] = 1
        }
    return def(arr, visited, 0, 0)


}

fun pathSum(root: TreeNode?, target: Int): List<List<Int>> {

    fun rec(root: TreeNode?, target: Int, ans: ArrayList<List<Int>>, path: ArrayList<Int>, curSum: Int) {
        if (root == null) return
        path.add(root.`val`)
        val tmp = curSum + root.`val`
        if (root.left == null && root.right == null && target == tmp) ans.add(ArrayList(path))
        else {
            rec(root.left, target, ans, path, tmp)
            rec(root.right, target, ans, path, tmp)
        }
        path.removeAt(path.size - 1)
    }

    val ans = ArrayList<List<Int>>()
    rec(root, target, ans, ArrayList(), 0)
    return ans

}


fun kthLargest(root: TreeNode?, k: Int): Int {
    var count = 0
    var res = 0
    fun helper(root: TreeNode?, target: Int) {
        if (root == null) return
        if (count < k) helper(root.right, k)
        if (++count == k) {
            res = root.`val`
            return
        }
        if (count < k) helper(root.left, k)
    }
    helper(root, k)
    return res

}



