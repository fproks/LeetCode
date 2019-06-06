package LeetCode.easy.Kotlin

import org.junit.Assert
import org.junit.Test
import java.util.*

class  ArraysSoultionTest{
    companion object {
        val  solution=ArraysSolution()
    }

    @Test
    fun countNumberOfOne(){
        Assert.assertEquals(2, solution.countNumberOfOne(10))
    }

    @Test
     fun uniqueMorseRepresentations(){
        val arrays = arrayOf<String>("gin","zen","gig","msg")
        Assert.assertEquals(2, solution.uniqueMorseRepresentations(arrays))
    }

    @Test
    fun repeatedNTimes() {
        val array = intArrayOf(1, 2, 3, 3)
        Assert.assertEquals(3, solution.repeatedNTimes(array))
    }

    @Test
    fun canThreePartsEqualSum() {
        val array = intArrayOf(0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1)
        val array2 = intArrayOf(0, 2, 1, -6, 6, 7, 9, -1, 2, 0, 1)
        val array3 = intArrayOf(3, 3, 6, 5, -2, 2, 5, 1, -9, 4)
        Assert.assertTrue(solution.canThreePartsEqualSum(array))
        Assert.assertFalse(solution.canThreePartsEqualSum(array2))
        Assert.assertTrue(solution.canThreePartsEqualSum(array3))

    }

    @Test
    fun powerfulIntegersTest() {
        Assert.assertArrayEquals(solution.powerfulIntegers(2, 3, 10).toIntArray(), intArrayOf(2, 3, 4, 5, 7, 9, 10))
        Assert.assertArrayEquals(solution.powerfulIntegers(3, 5, 15).toIntArray(), intArrayOf(2, 4, 6, 8, 10, 14))
        Assert.assertArrayEquals(solution.powerfulIntegers(2, 1, 10).toIntArray(), intArrayOf(2, 3, 5, 9))
    }

    @Test
    fun validMountainArrayTest() {
        Assert.assertFalse(solution.validMountainArray(intArrayOf(2, 1)))
        Assert.assertFalse(solution.validMountainArray(intArrayOf(3, 5, 5)))
        Assert.assertTrue(solution.validMountainArray(intArrayOf(0, 3, 2, 1)))
        Assert.assertFalse(solution.validMountainArray(intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)))
    }
}