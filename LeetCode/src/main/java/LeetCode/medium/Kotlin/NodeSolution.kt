package LeetCode.medium.Kotlin

import LeetCode.struct.TreeNode

class NodeSolution {
    fun expandBinaryTree(root: TreeNode?): TreeNode? {
        if (root == null) return root
        if (root.left == null && root.right == null) return root
        if (root.left != null) {
            val node = TreeNode(-1)
            node.left = root.left
            root.left = node
            node.left = expandBinaryTree(node.left)
        }
        if (root.right != null) {
            val node = TreeNode(-1)
            node.right = root.right
            root.right = node
            node.right = expandBinaryTree(node.right)
        }
        return root
    }
}