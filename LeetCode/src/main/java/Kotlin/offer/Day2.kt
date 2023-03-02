package Kotlin.offer

import LeetCode.struct.TreeNode
import java.lang.Integer.compare
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.abs
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

fun minNumber(nums: IntArray): String {
    nums.sortedWith { o1, o2 ->
        if ("$o1$o2".toInt() > "$o2$o1".toInt()) -1
        if ("$o1$o2".toInt() == "$o2$o1".toInt()) 0
        else 1
    }
    return nums.joinToString("")
}

fun isStraight(nums: IntArray): Boolean {
    var zero = 0
    val arr = IntArray(14) { 0 }
    var min = 13
    var max = 1
    for (i in nums) {
        if (i == 0) {
            zero++
            continue
        }
        arr[i]++
        if (arr[i] > 1) return false
        if (i > max) max = i
        if (i < min) min = i
    }
    return max - min > 5
}

fun getLeastNumbers(arr: IntArray, k: Int): IntArray {
    val result = IntArray(k) { 0 }
    for (i in arr.indices) {
        if (i < k) result[i] = arr[i]
        else {
            var maxresult = result[0]
            var maxj = 0
            for (j in result.indices) {
                if (maxresult < result[j]) {
                    maxj = j
                    maxresult = result[j]
                }

            }
            if (arr[i] < maxresult) {
                result[maxj] = arr[i]
                break
            }
        }
    }
    return result
}


class MedianFinder() {

    /** initialize your data structure here. */
    val left = PriorityQueue<Int>(Collections.reverseOrder())
    val right = PriorityQueue<Int>()

    fun addNum(num: Int) {
        left.offer(num)
        right.offer(left.poll())
        while (right.size > left.size) {
            left.offer(right.poll())
        }
    }

    fun findMedian(): Double {
        if (left.size == right.size) return (left.peek() + right.peek()) / 2.0
        else return left.peek().toDouble()
    }

}

fun maxDepth(root: TreeNode?): Int {
    if (root == null) return 0
    else return 1 + max(maxDepth(root.left), maxDepth(root.right))
}

fun isBalanced(root: TreeNode?): Boolean {
    if (root == null) return true
    if (!isBalanced(root.left)) return false
    if (!isBalanced(root.right)) return false
    if (abs(maxDepth(root.left) - maxDepth(root.right)) > 1) return false
    return true
}

fun sumNums(n: Int): Int {
    return n and (n + sumNums(n - 1))
}

fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
    if (preorder.isEmpty()) return null
    if (preorder.size == 1) return TreeNode(preorder[0])
    val root = TreeNode(preorder[0])
    val root_index = inorder.indexOf(preorder[0])
    val left_inorder = inorder.copyOfRange(0, root_index)
    val right_inorder = inorder.copyOfRange(root_index + 1, inorder.size)
    val preorder_left = preorder.filter { left_inorder.contains(it) }.toIntArray()
    val preorder_right = preorder.filter { right_inorder.contains(it) }.toIntArray()
    root.left = buildTree(preorder_left, left_inorder)
    root.right = buildTree(preorder_right, right_inorder)
    return root
}


fun verifyPostorder(postorder: IntArray): Boolean {
    val stack = LinkedList<Int>()
    var parent = Int.MAX_VALUE

    for (i in postorder.size - 1 downTo 0) {
        if (parent < postorder[i]) return false
        while (!stack.isEmpty() && stack.peek() > postorder[i]) parent = stack.pop()
        stack.push(postorder[i])
    }
    return true
}


fun constructArr(a: IntArray): IntArray {
    val b = IntArray(a.size) { 1 }
    for (i in 1 until a.size) {
        b[i] = b[i - 1] * a[i - 1]
    }
    var porduct = 1
    for (i in a.size - 2 downTo 0) {
        porduct *= a[i + 1]
        b[i] = b[i + 1] * porduct
    }
    return b
}

fun cuttingRope(n: Int): Int {
    if (n <= 3) return 1
    val mod = n % 3
    val nc = n / 3
    if (mod == 1) return Math.pow(3.0, (nc - 1).toDouble()).toInt() * 4
    if (mod == 2) return (Math.pow(3.0, nc.toDouble()) * mod).toInt()
    else return Math.pow(3.0, nc.toDouble()).toInt()
}

fun findContinuousSequence(target: Int): Array<IntArray> {
    fun consum(start: Int, end: Int, target: Int): Boolean {
        val sum = (start + end) / 2 * (end - start + 1)
        return sum == target
    }

    fun createSXPArray(start: Int, end: Int): IntArray {
        val arr = IntArray(end - start + 1) { 0 }
        for (i in arr.indices) arr[i] = start + i
        return arr
    }

    val result = ArrayList<IntArray>()
    for (start in 0..target / 2)
        for (end in start + 1..target) {
            val sum = (start + end) / 2 * (end - start + 1)
            if (sum == target) {
                result.add(createSXPArray(start, end))
                break
            }
            if (sum > target) break
        }
    return result.toTypedArray()
}


fun spiralOrder(matrix: Array<IntArray>): IntArray {
    val res = ArrayList<Int>()
    if (matrix.isEmpty()) return res.toIntArray()
    var rl = 0
    var rh = matrix.size - 1
    var cl = 0
    var ch = matrix[0].size - 1
    while (true) {
        for (i in cl..ch) res.add(matrix[rl][i])
        if (++rl > rh) break
        for (i in rl..rh) res.add(matrix[i][ch])
        if (--ch < cl) break
        for (i in ch downTo cl) res.add(matrix[rh][i])
        if (--rh < rl) break
        for (i in rh downTo  rl)res.add(matrix[i][cl])
        if (++cl >ch)break
    }
    return res.toIntArray()

}