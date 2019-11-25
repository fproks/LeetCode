package LeetCode.medium;

import LeetCode.struct.ListNode;
import LeetCode.struct.TreeNode;

import java.util.ArrayList;

/**
 * @user: linhos
 * @Time: Create in 10:32 2017/9/26
 */
public class ListNodeSolution {

    //24. Swap Nodes in Pairs
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode h = new ListNode(0);
        h.next = head;
        ListNode p = h;
        while (p != null && p.next != null && p.next.next != null) {
            ListNode n = p.next;
            p.next = n.next;
            n.next = p.next.next;
            p.next.next = n;
            p = p.next.next;
        }
        return h.next;
    }
    public TreeNode sortedListToBST(ListNode head){
        if(head==null)return null;
        var list =new ArrayList<Integer>();
        while (head!=null){
            list.add(head.val);
            head=head.next;
        }
        return  sortedArrayToBST(list,0,list.size()-1);
    }

    private  TreeNode sortedArrayToBST(ArrayList<Integer> list,int first,int last){
         TreeNode result=null;
        if(first >last)return result;
        if(first ==last)return  new TreeNode(list.get(first).intValue());
        int mid =(first+last)/2;
        result =new TreeNode(list.get(first).intValue());
        result.left =sortedArrayToBST(list,first,mid-1);
        result.right=sortedArrayToBST(list,mid+1,last);
        return  result;
    }
}
