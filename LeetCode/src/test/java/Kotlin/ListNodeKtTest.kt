package Kotlin

import junit.framework.TestCase

class ListNodeKtTest : TestCase() {

    fun testRotateRight() {
        val head = createListNode(intArrayOf(1,2,3,4,5))
        listNodePrint(head)
        assertEquals(4, rotateRight(head,2)!!.`val`)
        val  tmp= rotateRight(createListNode(intArrayOf(1)),99)
        listNodePrint(tmp)
    }
}