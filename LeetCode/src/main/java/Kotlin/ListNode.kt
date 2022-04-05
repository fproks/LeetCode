package Kotlin

class ListNode(var `val`: Int) {
        var next: ListNode? = null
}

fun rotateRight(head: ListNode?, k: Int): ListNode? {
    if(k==0)return head
    if (head!=null){
        var p =head
        var count=0
        while (p!=null){
            p=p.next
            count++
        }
        if (k%count==0)return  head
        else {
            var  rem =count-k%count-1
            p=head
            while (rem!=0){
                rem --
                if (p != null) {
                    p=p.next
                }
            }
            val reh=p?.next
            p?.next =null
            p=reh
            while (p?.next!=null){
                p=p.next
            }
            p?.next =head
            return  reh

        }
    }
    return null
}

fun  listNodePrint(head:ListNode?){
    var p =head
   while (p !=null){
       print(p.`val`)
       print(" ")
       p =p.next
   }
    println()
}

fun  createListNode(arr: IntArray):ListNode?{
    if(arr.isEmpty())return null
    var p =ListNode(arr[0])
    var q =p
    for ( i in 1 until arr.size){
        q.next=ListNode(arr[i])
        q= q.next!!
    }
    return p
}



fun mergeNodes(head: ListNode?): ListNode? {
    var  res =ListNode(0)
    var p =res
    var h =head?.next
    while (h!=null){
        if (h.`val`==0) {
            if (h.next!=null) {
                p.next = ListNode(0)
                p = p.next!!
            }
        }
        else {
            p.`val` += h.`val`
        }
        h=h.next

    }
    if (res.`val`==0)return  null
    return res
}