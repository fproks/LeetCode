from Solution.STRUCT import LitNode

class ListSolution:
    def middleNode(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        first =head
        second =head
        while second and second.next:
            first=first.next
            second =second.next.next
        return  first
