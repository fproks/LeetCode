package LeetCode.medium;

import LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @user: linhos
 * @Time: Create in 16:37 2017/9/28
 */
public class TreeNodeSolution {
    //94. Binary Tree Inorder Traversal
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list;
    }

    private void inorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) return;
        if (root.left != null)
            inorderTraversal(root.left, list);
        list.add(root.val);
        if (root.right != null)
            inorderTraversal(root.right, list);
    }

    //94题的非迭代算法
    private List<Integer> inorderTraversalWithRecursive(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();
        if (root == null) return list;
        stack.push(root);
        while (!stack.empty()) {
            pushTreeNode(stack, list);
        }
        return list;
    }

    private void pushTreeNode(Stack<TreeNode> stack, List<Integer> list) {
        TreeNode root = stack.pop();
        if (root.left == null) {
            list.add(root.val);
            if (root.right != null)
                stack.push(root.right);
        } else {
            TreeNode left = root.left;
            root.left = null;
            stack.push(root);
            stack.push(left);
        }
    }
}
