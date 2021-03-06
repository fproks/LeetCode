package LeetCode.medium;

import LeetCode.struct.ListNode;
import LeetCode.struct.TreeNode;
import org.junit.Test;

/**
 * @user: linhos
 * @Time: Create in 10:42 2017/9/26
 */
public class ListNodeSolutionTest {
    ListNodeSolution solution = new ListNodeSolution();

    @Test
    public void swapPairs() throws Exception {
        ListNode p = new ListNode(1);
        ListNode head = p;
        p.next = new ListNode(2);
        p = p.next;
        p.next = new ListNode(3);
        p = p.next;
        p.next = new ListNode(4);
        head = solution.swapPairs(head);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

    }

    @Test
    public  void  sortedListToBST(){
        ListNode p =new ListNode(1);
        p.next=new ListNode(2);
        TreeNode tree = solution.sortedListToBST(p);
        System.out.println(tree.val);
    }

}