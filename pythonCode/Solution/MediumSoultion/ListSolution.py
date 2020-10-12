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

    # 951. Flip Equivalent Binary Trees
    # https://leetcode.com/problems/flip-equivalent-binary-trees/
    def flipEquiv(self, root1: TreeNode, root2: TreeNode) -> bool:
        def fanz(root1: TreeNode, root2: TreeNode):
            if not root1 and not root2: return True
            if root1 and root2 and root1.val == root2.val:
                return (fanz(root1.left, root2.right) and fanz(root1.right, root2.left)) or (
                        fanz(root1.left, root2.left) and fanz(root1.right, root2.right))
            else:
                return False

        return fanz(root1, root2)

    # 998. Maximum Binary Tree II
    # 这个题只能插右边，后插的插右边，原来的右边变成左边
    def insertIntoMaxTree(self, root: TreeNode, val: int) -> TreeNode:
        p = TreeNode(val)
        if root is None: return p
        if val > root.val:
            p.left = root
            return p
        rol1 = root
        ro2 = root.right
        while ro2 is not None:
            if val > ro2.val:
                p.left = ro2
                rol1.right = p
                return root
            else:
                rol1 = ro2
                ro2 = rol1.right
        rol1.right = p
        return root

    # 1110. Delete Nodes And Return Forest
    def delNodes(self, root: TreeNode, to_delete: List[int]) -> List[TreeNode]:
        d = set(to_delete)
        res = []

        # 节点要么为空，要么节点为它自己
        # 子节点为helper(子节点)
        # 当前节点符合，添加子节点
        def helper(node):
            if not node: return None
            l, r = helper(node.left), helper(node.right)
            if node.val in d:
                if l: res.append(node.left)
                if r: res.append(node.right)
                return None
            node.left, node.right = l, r
            return node

        cur = helper(root)
        return res + [cur] if cur else res

    # 889. Construct Binary Tree from Preorder and Postorder Traversal
    def constructFromPrePost(self, pre: List[int], post: List[int]) -> TreeNode:
        if not pre or not post: return None
        root = TreeNode(pre[0])
        if len(pre) == 1: return root
        idx = pre.index(post[-2])
        root.left = self.constructFromPrePost(pre[1:idx], post[:idx - 1])
        root.right = self.constructFromPrePost(pre[idx:], post[idx - 1:-1])
        return root

    # 1026. Maximum Difference Between Node and Ancestor
    def maxAncestorDiff(self, root: TreeNode) -> int:
        self.max_diff = float('-inf')
        if not root:
            return

        def dfs(node, cur_min, cur_max):
            if not node:
                return
            self.max_diff = max(abs(cur_min - node.val), abs(cur_max - node.val), self.max_diff)
            dfs(node.left, min(cur_min, node.val), max(cur_max, node.val))
            dfs(node.right, min(cur_min, node.val), max(cur_max, node.val))

        dfs(root, root.val, root.val)
        return self.max_diff

    '''
    865. Smallest Subtree with all the Deepest Nodes
    https://blog.csdn.net/dai_qingyun/article/details/86167018
    首先找最深节点的问题可以转化为求解二叉树高度的问题，有了高度信息之后，就会产生以下两种情况：
    当前根节点的左右子树一样高，那么就返回当前根节点。
    当前根节点的左右子树不一样高，那么返回相对较高的子树的根节点。
    经过递归处理之后，得到最终的答案。
    '''

    def subtreeWithAllDeepest(self, root: TreeNode) -> TreeNode:
        def deep(root: TreeNode) -> int:
            if root is None: return 0
            return max(deep(root.left) + 1, deep(root.right) + 1)

        ldp = deep(root.left)
        rdp = deep(root.right)
        if ldp < rdp: return self.subtreeWithAllDeepest(root.right)
        if ldp > rdp: return self.subtreeWithAllDeepest(root.left)
        return root

    # 1123. Lowest Common Ancestor of Deepest Leaves
    # 和上面的一模一样
    def lcaDeepestLeaves(self, root: TreeNode) -> TreeNode:
        def deep(root: TreeNode) -> int:
            if root is None: return 0
            return max(deep(root.left) + 1, deep(root.right) + 1)

        ldp = deep(root.left)
        rdp = deep(root.right)
        if ldp < rdp: return self.lcaDeepestLeaves(root.right)
        if ldp > rdp: return self.lcaDeepestLeaves(root.left)
        return root

    # 112. Path Sum
    def hasPathSum(self, root: TreeNode, sum: int) -> bool:
        if root is None:
            return sum == 0
        if root.right is None and root.left is None:
            return root.val == sum
        else:
            return self.hasPathSum(root.left, sum - root.val) or self.hasPathSum(root.right, sum - root.val)

    def getMinimumDifference(self, root: TreeNode) -> int:
        diff = 999
        pre = 999

        def dif(root: TreeNode):
            if root is None:
                return
            self.diff = min(self.diff, abs(root.val - self.pre))
            self.pre = root.val
            dif(root.left)
            dif(root.right)

        dif(root)
        return diff
