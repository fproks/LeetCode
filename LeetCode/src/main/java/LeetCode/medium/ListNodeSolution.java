package LeetCode.medium;

import LeetCode.ListNode;

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
}
