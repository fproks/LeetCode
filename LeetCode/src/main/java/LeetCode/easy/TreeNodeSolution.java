package LeetCode.easy;

import LeetCode.Solution;
import LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @user: linhos
 * @Time: Create in 15:25 2017/9/19
 */
public class TreeNodeSolution implements Solution {
    private int result = 0;
    private int convertBSTsum = 0;
    //543. Diameter of Binary Tree
    private int diameter = 0;

    TreeNode convertBST(TreeNode root) {
        convertFromRight(root);
        return root;
    }

    //538. Convert BST to Greater Tree
    // 后序遍历，从大到小
    private void convertFromRight(TreeNode root) {
        if (root == null) return;
        convertFromRight(root.right);
        root.val += convertBSTsum;
        convertBSTsum = root.val;
        convertFromRight(root.left);
    }


    public int diameterOfBinaryTree(TreeNode root) {
        deepthTree(root);
        return diameter <= 1 ? diameter : diameter - 1;
    }

    private int deepthTree(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int left = deepthTree(root.left);
        int right = deepthTree(root.right);
        int tmp = left + right + 1;
        diameter = diameter > tmp ? diameter : tmp;
        return left > right ? left + 1 : right + 1;
    }


    public int findTilt(TreeNode root) {
        if (root == null) return 0;
        int left = 0, right = 0;
        left = findTilt(root.left);
        right = findTilt(root.right);
        int ll = 0, rr = 0;
        if (root.left != null) ll = root.left.val;
        if (root.right != null) rr = root.right.val;
        return left + right + Math.abs(ll - rr);
    }

    public int filt(TreeNode root) {
        if (root == null) return 0;
        int left = root.left == null ? 0 : root.left.val;
        int right = root.right == null ? 0 : root.right.val;
        return Math.abs(left - right) + filt(root.left) + filt(root.right);
    }

    public int findTilts(TreeNode root) {
        postOrder(root);
        return result;
    }

    private int postOrder(TreeNode root) {
        if (root == null) return 0;

        int left = postOrder(root.left);
        int right = postOrder(root.right);

        result += Math.abs(left - right);

        return left + right + root.val;
    }


    //572. Subtree of Another Tree
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        if (isSameTree(s, t)) return true;
        else return (isSubtree(s.left, t) || isSubtree(s.right, t));

    }

    private boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s != null && t != null) {
            if (s.val == t.val) {
                return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
            }
        }
        return false;
    }

    //653. Two Sum IV - Input is a BST
    private ArrayList<Integer> findTargetList;

    public String tree2str(TreeNode t) {
        StringBuilder s = new StringBuilder();
        if (t != null) {
            s.append(t.val);
            if (t.left == null && t.right == null)
                return s.toString();
            s.append('(');
            s.append(tree2str(t.left));
            s.append(')');
            if (t.right != null) {
                s.append('(');
                s.append(tree2str(t.right));
                s.append(')');
            }
        }
        return s.toString();
    }

    //637. Average of Levels in Binary Tree
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int i = queue.size();
                int tmp = i;
                double count = 0.0;
                while (i > 0) {
                    TreeNode node = queue.poll();
                    count += node.val;
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                    i--;
                }
                list.add(count / tmp);
            }
        }
        return list;
    }

    public boolean findTarget(TreeNode root, int k) {
        findTargetList = new ArrayList<Integer>();
        midsearch(root);
        int n = findTargetList.size();
        for (int i = 0, j = n - 1; i < j; ) {
            int tmp = findTargetList.get(i) + findTargetList.get(j);
            if (tmp < k) i++;
            else if (tmp > k) j--;
            else return true;
        }
        return false;
    }

    private void midsearch(TreeNode root) {
        if (root == null) return;
        midsearch(root.left);
        findTargetList.add(root.val);
        midsearch(root.right);
    }




}
