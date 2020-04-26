package LeetCode.hard;
import LeetCode.struct.ListNode;
import LeetCode.struct.Solution;

public class NodeSolution implements Solution {

    //23. Merge k Sorted Lists
    public ListNode mergeKLists(ListNode[] lists) {
       ListNode head=new ListNode(0);
       ListNode pee=head;
       while (true){
           int j =findMin(lists);
           if (j!=-1){
               pee.next =new ListNode(lists[j].val);
               pee =pee.next;
               ListNode tmp =lists[j].next;
               lists[j]=tmp;
           }else {
               break;
           }
       }
       return  head.next;

    }
    private int findMin(ListNode[] lists){
        int min=Integer.MAX_VALUE;
        int idx=-1;
        for (int i = 0; i <lists.length ; i++) {
            if(lists[i]!=null && lists[i].val<min){
                min=lists[i].val;
                idx=i;
            }
        }
        return  idx;
    }
}
