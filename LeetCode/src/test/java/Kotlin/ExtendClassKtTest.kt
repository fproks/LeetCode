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
}