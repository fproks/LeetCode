package LeetCode.medium;

import LeetCode.struct.TreeNode;

import java.util.*;

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

    //102 Binary Tree Level Order Traversal
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<List<Integer>> list = new LinkedList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        getLevel(list, queue);
        return list;
    }

    //广度遍历
    private void getLevel(List<List<Integer>> list, Queue<TreeNode> queue) {
        int tmp = queue.size();
        if (tmp != 0) {
            ArrayList<Integer> level = new ArrayList<>();
            while (tmp != 0) {
                TreeNode node = queue.remove();
                level.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                tmp--;
            }
            list.add(level);
            getLevel(list, queue);
        }
    }

    //通过深度遍历达到广度的目的
    private void dfsLevel(List<List<Integer>> list, TreeNode node, int level) {
        if (node == null) return;
        if (level >= list.size())
            list.add(new LinkedList<>());
        list.get(level).add(node.val);
        dfsLevel(list, node.left, level+1);
        dfsLevel(list, node.right, level+1);
    }

    //230. Kth Smallest Element in a BST
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode n = root;
        while (n != null) {
            stack.push(n);
            n = n.left;
        }
        while (k > 0 && !stack.isEmpty()) {
            n = stack.pop();
            if (--k == 0) return n.val;
            n = n.right;
            while (n != null) {
                stack.push(n);
                n = n.left;
            }
        }
        return -1;
    }


}

