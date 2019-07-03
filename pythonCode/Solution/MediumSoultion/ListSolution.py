from typing import List
from copy import copy
from Solution.STRUCT import LitNode
from Solution.STRUCT.TreeNode import TreeNode


class ListSolution:
    def middleNode(self, head: LitNode):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        first = head
        second = head
        while second and second.next:
            first = first.next
            second = second.next.next
        return first

    @staticmethod
    def insertIntoBST(root: TreeNode, val: int) -> TreeNode:
        tmp = root
        while True:
            if tmp.val < val:
                if tmp.right is not None:
                    tmp = tmp.right
                    continue
                else:
                    tmp.right = TreeNode(val)
                    break
            else:
                if tmp.left is not None:
                    tmp = tmp.left
                    continue
                else:
                    tmp.left = TreeNode(val)
                    break
        return root

    def pruneTree(self, root: TreeNode) -> TreeNode:

        if root is None: return None
        root.left = self.pruneTree(root.left)
        root.right = self.pruneTree(root.right)
        if root.val == 0 and root.left is None and root.right is None: return None
        return root

    res = {}

    # 可以记录中间步骤，节省时间
    def allPossibleFBT(self, N: int) -> List[TreeNode]:
        res = {}
        if N % 2 == 0: return []
        self.res[1] = [TreeNode(0)]
        if N not in self.res:
            ans = []
            for x in range(1, N, 2):
                y = N - 1 - x
                for left in self.allPossibleFBT(x):
                    for right in self.allPossibleFBT(y):
                        tmp = TreeNode(0)
                        tmp.left = left
                        tmp.right = right
                        ans.append(tmp)
            self.res[N] = ans
        return self.res[N]

    def distributeCoins(self, root: TreeNode) -> int:
        def need(tree: TreeNode) -> int:
            if tree is None: return 0
            left = need(tree.left)
            right = need(tree.right)
            return left + right + 1 - tree.val

        if root is None: return 0
        left = need(root.left)
        right = need(root.right)
        res = abs(left) + abs(right)
        res += self.distributeCoins(root.left)
        res += self.distributeCoins(root.right)
        return res




