package LeetCode.hard

class ksolution {

    fun isGoodArray(nums: IntArray): Boolean {
        var num = nums[0]
        for (i in 1 until nums.size) {
            num = gcd(num, nums[i])
            if (num==1)return true
        }
        return num==1
    }

    fun gcd(a: Int, b: Int): Int {
        if (b == 0) return a
        if (b > a) return gcd(b, a)
        return gcd(b, a % b)
    }
}