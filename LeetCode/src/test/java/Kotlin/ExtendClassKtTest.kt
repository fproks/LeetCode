package Kotlin

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import kotlin.concurrent.thread

class ExtendClassKtTest {

    @Test
    fun swap() {
        val str = "abcd"
        println("str.swap(0,str.lastIndex)=${str.swap(0, str.lastIndex)}")
        var lit = listOf<Int>(1,2,3,4,5).filter { it %2 ==1 }
        fun isodd(x:Int)=x %2 ==1
        val  lts = listOf(1,2,3,4,5).filter (::isodd)
        Assert.assertArrayEquals(lit.toIntArray(),lts.toIntArray())

        Thread {
            for (i in 1..10) {
                println("i = $i")
                Thread.sleep(1000)
            }
        }.start()
        Thread {
            for (j in 20..10) {
                println("j =$j")
                Thread.sleep(1000)
            }
        }.start()

        repeat(3){
            println("hello")
            Thread.sleep(1000)
        }

        thread(start = true,isDaemon = true){
            for (i in 1..10) {
                Thread.sleep(1000)
                println("z =$i")
            }
        }




    }

    @Test
    fun testPushDominoes() {
        assertEquals(pushDominoes(".L.R...LR..L.."),"LL.RR.LLRRLL..")
        assertEquals(pushDominoes("RR.L"),"RR.L")
    }

    @Test
    fun testNumPrimeArrangements() {
        assertEquals(12,numPrimeArrangements(5))
        assertEquals(682289015, numPrimeArrangements(100))
    }

    @Test
    fun testReverseOnlyLetters() {
        assertEquals("dc-ba", reverseOnlyLetters("ab-cd"))
    }

    @Test
    fun testConvert() {
        assertEquals(convert("PAYPALISHIRING",3),"PAHNAPLSIIGYIR")
        assertEquals(convert("PAYPALISHIRING",4),"PINALSIGYAHRPI")
    }

    @Test
    fun testNearestPalindromic() {
        assertEquals(nearestPalindromic("9"),"8")
        assertEquals(nearestPalindromic("123"),"121")
        assertEquals(nearestPalindromic("1"),"0")
        assertEquals(nearestPalindromic("1444"),"1441")
    }

    @Test
    fun testFindRestaurant() {
        assertArrayEquals(arrayOf("Shogun"), findRestaurant(arrayOf("Shogun", "Tapioca Express", "Burger King", "KFC"),
            arrayOf("KFC", "Shogun", "Burger King")
        ))
        assertArrayEquals(arrayOf("Shogun"), findRestaurant(arrayOf("Shogun", "Tapioca Express", "Burger King", "KFC"),
            arrayOf("Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun")
        ))
    }

    @Test
    fun testCountMaxOrSubsets() {
        assertEquals(15,countMaxOrSubsets(intArrayOf(2,2,2,2)))
        assertEquals(8,countMaxOrSubsets(intArrayOf(2,2,2,3)))
    }

    @Test
    fun testFindMinHeightTrees() {
       assertEquals(1, findMinHeightTrees(4, arrayOf(intArrayOf(1,0), intArrayOf(1,2), intArrayOf(1,3))).size)
    }
}