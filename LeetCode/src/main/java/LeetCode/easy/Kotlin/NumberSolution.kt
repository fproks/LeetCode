package LeetCode.easy.Kotlin

class NumberSolution {

    fun selfDividingNumbers(left: Int, right: Int): List<Int> {
        return left.rangeTo(right).filter { isSelfDividingNumber(it) }.toList()

    }


    private fun isSelfDividingNumber(number: Int): Boolean {
        if (number <= 9) return true
        if (number % 10 == 0) return false
        var numberTmp = number
        while (numberTmp > 0) {
            val div = numberTmp % 10
            if (div == 0 || number % div != 0) return false
            numberTmp /= 10

        }
        return true
    }

    //693. Binary Number with Alternating Bits
    /*
    * 利用了0和1的交替的特性，进行错位相加，从而组成全1的二进制数，
    * 然后再用一个检测全1的二进制数的trick，
    * 就是‘与’上加1后的数，因为全1的二进制数加1，就会进一位，并且除了最高位，其余位都是0，
    * 跟原数相‘与’就会得0，所以我们可以这样判断。
    * 比如n是10101，那么n>>1就是1010，二者相加就是11111，再加1就是100000，二者相‘与’就是0，
    * */
    fun hasAlternatingBits(n: Int): Boolean {
        return (n + (n.shr(1)) + 1) and (n + (n.shr(1))) == 0
    }

    fun DivisorGame(N: Int): Boolean {
        val tmp = Array(N + 1, { false })
        tmp[1] = false
        for (i in 2..N) {
            for (j in 1 until i) {
                if (i % j == 0 && !tmp[i - j]) {
                    tmp[i] = true
                    break
                }
            }
        }
        return tmp[N]
    }

    fun DivisorGame2(N: Int): Boolean {
        return N % 2 == 0
    }


    //1033. Moving Stones Until Consecutive
    //https://blog.csdn.net/CSerwangjun/article/details/89643294
    fun numMovesStones(a: Int, b: Int, c: Int): IntArray {
        val max = Math.max(Math.max(a, b), c)
        val arr = intArrayOf(a, b, c).sorted()
        val a = arr[0]
        val b = arr[1]
        val c = arr[2]
        val array = IntArray(2)
        if (a + 1 == b && b + 1 == c) array[0] = 0
        else {
            if (a + 2 == b || b + 2 == c || a + 1 == b || b + 1 == c) array[0] = 1
            else array[0] = 2
        }
        array[1] = c - a - 2
        return array
    }


}

class UnionSolution {
    fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        val union = UnionFind(n)
        for (edge in edges) {
            union.union(edge[0], edge[1])
        }
        return union.find(source) == union.find(destination)
    }

    class UnionFind(n: Int) {
        private lateinit var parent: IntArray

        init {
            parent = IntArray(n) { 0 }.mapIndexed { index, _ -> index }.toIntArray()
        }

        fun find(x: Int): Int {
            if (x != parent[x]) parent[x] = find(parent[x])
            return parent[x]
        }

        fun union(x: Int, y: Int) {
            parent[find(x)] = parent[find(y)]
        }
    }
}
