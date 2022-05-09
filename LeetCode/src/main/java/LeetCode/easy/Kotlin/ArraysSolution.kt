package LeetCode.easy.Kotlin

import java.util.*
import kotlin.collections.HashSet
import kotlin.math.abs

class ArraysSolution {


    //766. Toeplitz Matrix
    fun isToeplitzMatrix(matrix: Array<IntArray>): Boolean {
        val HX = matrix.size  //行
        val HY = matrix[0].size  //列

        for (i in 0 until HX - 1) {
            for (j in 0 until HY - 1) {
                if (matrix[i][j] != matrix[i + 1][j + 1])
                    return false
            }
        }
        return true
    }


    //733. Flood Fill
    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, newColor: Int): Array<IntArray> {

        val srMax = image.size - 1
        val scMax = image[0].size - 1
        if (sr < 0 || sr > srMax) return image
        if (sc < 0 || sc > scMax) return image
        if (newColor < 0 || newColor > 65536) return image
        if (image[sr][sc] == newColor) return image

        val oldCol = image[sr][sc]
        image[sr][sc] = newColor
        var tmp = image
        if (sr > 0 && image[sr - 1][sc] == oldCol) tmp = floodFill(tmp, sr - 1, sc, newColor)
        if (sr < srMax && image[sr + 1][sc] == oldCol) tmp = floodFill(tmp, sr + 1, sc, newColor)
        if (sc > 0 && image[sr][sc - 1] == oldCol) tmp = floodFill(tmp, sr, sc - 1, newColor)
        if (sc < scMax && image[sr][sc + 1] == oldCol) tmp = floodFill(tmp, sr, sc + 1, newColor)
        return tmp
    }


    //762. Prime Number of Set Bits in Binary Representation
    fun countPrimeSetBits(L: Int, R: Int): Int {
        if (L > R) return 0
        val zs = listOf(2, 3, 5, 7, 11, 13, 17, 19)
        return (L..R).count {
            zs.contains(countNumberOfOne(it))
        }

    }


    fun countNumberOfOne(number: Int): Int {
        var tmp = number
        var res = 0
        while (tmp != 0) {
            if (tmp.and(1) != 0)
                res++
            tmp = tmp.ushr(1)
        }
        return res
    }


    //695. Max Area of Island
    fun maxAreaOfIsland(grid: Array<IntArray>): Int {
        var count = 0
        val queue = Stack<Pair<Int, Int>>()
        for (i in grid.indices) {
            for (j in 0 until grid[i].size) {
                if (grid[i][j] == 1) {
                    queue.push(Pair(i, j))
                    var tmp = 0
                    while (!queue.empty()) {
                        val tmpPair = queue.pop()
                        if (grid[tmpPair.first][tmpPair.second] == 1) {
                            grid[tmpPair.first][tmpPair.second] = 0
                            tmp++
                            if (tmpPair.first > 0) queue.push(Pair(tmpPair.first - 1, tmpPair.second))
                            if (tmpPair.first + 1 < grid.size) queue.push(Pair(tmpPair.first + 1, tmpPair.second))
                            if (tmpPair.second > 0) queue.push(Pair(tmpPair.first, tmpPair.second - 1))
                            if (tmpPair.second + 1 < grid[i].size) queue.push(Pair(tmpPair.first, tmpPair.second + 1))
                        }
                    }
                    if (tmp > count) count = tmp
                }
            }
        }
        return count
    }

    //804. Unique Morse Code Words
    fun uniqueMorseRepresentations(words: Array<String>): Int {
        val lettle = arrayListOf<String>(
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
            "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-",
            ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."
        )
        val setTmp = HashSet<String>()
        val tmp = StringBuilder()
        for (word in words) {
            word.forEach { tmp.append(lettle[it - 'a']) }
            setTmp.add(tmp.toString())
            tmp.delete(0, tmp.length)
        }

        return setTmp.size
    }

    fun largestTriangleArea(p: Array<Array<Int>>): Double {
        var res = 0.0
        for (i in p)
            for (j in p)
                for (k in p)
                    res = maxOf(
                        res,
                        0.5 * abs(i[0] * j[1] + j[0] * k[1] + k[0] * i[1] - j[0] * i[1] - k[0] * j[1] - i[0] * k[1])
                    )
        return res
    }

    fun repeatedNTimes(A: IntArray): Int {
        val n = A.size
        val random = Random()
        while (true) {
            val a = random.nextInt(n)
            var b = random.nextInt(n)
            while (a == b) b = random.nextInt(n)
            if (A[a] == A[b]) return A[a]
        }
    }

    fun canThreePartsEqualSum(A: IntArray): Boolean {
        val sum = A.sum() / 3
        val first = sumArr(A, 0, sum)
        if (first == -1) return false
        val j = sumArr(A, first + 1, sum)
        if (j == -1) return false
        if (sumArr(A, j + 1, sum) != -1) return true
        return false
    }

    fun sumArr(A: IntArray, first: Int, result: Int): Int {
        var sum = 0
        if (first >= A.size) return -1
        for (idx in first until A.size) {
            sum += A[idx]
            if (sum == result) return idx
        }
        return -1
    }

    fun powerfulIntegers(x: Int, y: Int, bound: Int): List<Int> {
        val result = HashSet<Int>()
        val xset = HashSet<Double>()
        val yset = HashSet<Double>()
        var dx = x.toDouble()
        var dy = y.toDouble()
        xset.add(1.0)
        xset.add(dx)
        yset.add(1.0)
        yset.add(dy)
        while (dx != 1.0) {
            dx *= x
            if (dx <= bound) xset.add(dx)
            else break
        }
        while (dy != 1.0) {
            dy = dy * y
            if (dy <= bound) yset.add(dy)
            else break
        }
        for (xs in xset) {
            for (ys in yset) {
                if (xs + ys <= bound) result.add((xs + ys).toInt())
            }
        }
        return result.toList().sorted()
    }

    fun validMountainArray(A: IntArray): Boolean {
        if (A.size < 3) return false
        if (A[1] < A[0]) return false
        var max = Int.MIN_VALUE
        var hasMax = false
        for (a in A) {
            if (!hasMax) {
                if (max < a) {
                    max = a
                    continue
                }
                if (max == a) return false
                if (max > a) {
                    hasMax = true
                    max = a
                }
            } else {
                if (max == a) return false
                if (max > a) {
                    max = a
                } else {
                    return false
                }
            }
        }
        return hasMax
    }


    fun numberOfLines(widths: IntArray, s: String): IntArray {
        var lin = 1
        var res = 0
        for (c in s) {
            val tmp = widths[c - 'a']
            res += tmp
            if (res > 100) {
                lin++
                res = tmp
            }
        }
        return intArrayOf(lin, res)
    }

    fun maximumWealth(accounts: Array<IntArray>): Int {
        return accounts.maxOf { it.sum() }
    }

    fun diStringMatch(s: String): IntArray {
        val n =s.length
        val first =s.count { it=='D' }
        val array =kotlin.collections.ArrayList<Int>()
        array.add(first)
        var low=first
        var high =first
        for (i in 1..n){
            if(s[i-1]=='I')array.add(low--)
            else array.add(high++)
        }
        return  array.toIntArray()
    }


}