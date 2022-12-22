package Kotlin.offer

import java.util.*

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

}