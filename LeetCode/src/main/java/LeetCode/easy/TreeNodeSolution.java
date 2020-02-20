package LeetCode.easy;

import LeetCode.struct.ListNode;
import LeetCode.struct.Node;
import LeetCode.struct.Solution;
import LeetCode.struct.TreeNode;

import java.sql.SQLOutput;
import java.util.*;
import java.util.function.ToIntFunction;

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


    //669. Trim a Binary Search Tree
    /*
     * 二叉树剪枝
     * */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return root;
        if (root.val < L) {
            root = root.right;
            return trimBST(root, L, R);
        } else if (root.val > R) {
            root = root.left;
            return trimBST(root, L, R);
        } else {
            root = trimTreeLeft(root, L);
            root = trimTreeRight(root, R);
            return root;
        }
    }


    private TreeNode trimTreeLeft(TreeNode root, int L) {
        if (root != null) {
            if (root.val == L) {
                root.left = null;
            } else if (root.val > L) {
                root.left = trimTreeLeft(root.left, L);
            } else {
                root = trimTreeLeft(root.right, L);
            }
        }
        return root;

    }

    private TreeNode trimTreeRight(TreeNode root, int R) {
        if (root != null) {
            if (root.val == R) root.right = null;
            else if (root.val < R) {
                root.right = trimTreeRight(root.right, R);
            } else root = trimTreeRight(root.left, R);
        }
        return root;
    }


    //671. Second Minimum Node In a Binary Tree
    public int findSecondMinimumValue(TreeNode root) {
        int[] data = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        help(root, data);
        return data[1] != Integer.MAX_VALUE ? data[1] : -1;
    }

    public void help(TreeNode root, int[] data) {
        if (root == null)
            return;
        if (root.val < data[0]) {
            data[1] = data[0];
            data[0] = root.val;
        } else if (root.val < data[1] && root.val > data[0])
            data[1] = root.val;
        help(root.left, data);
        help(root.right, data);
    }

    //674. Longest Continuous Increasing Subsequence 严格自增
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length <= 0) return 0;
        int count = 0;
        int first = 0, sen = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                count = count < sen - first + 1 ? sen - first + 1 : count;
                first = i;
            }
            sen = i;
        }
        count = count < sen - first + 1 ? sen - first + 1 : count;
        return count;
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ret = new LinkedList<>();

        if (root == null) return ret;

        Queue<Node> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> curLevel = new LinkedList<>();
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node curr = queue.poll();
                curLevel.add(curr.val);
                for (Node c : curr.children)
                    queue.offer(c);
            }
            ret.add(curLevel);
        }

        return ret;
    }


    public int sumRootToLeaf(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        sumRootToLeaf(root, list, 0);
        list.stream().forEach(System.out::print);
        return list.stream().mapToInt(Integer::intValue).sum();
    }

    public void sumRootToLeaf(TreeNode treeNode, ArrayList<Integer> sum, int binary) {
        if (treeNode == null) return;
        if (treeNode.right == null && treeNode.left == null) {
            binary = (binary << 1) + treeNode.val;
            sum.add(binary);
            return;
        }
        binary = (binary << 1) + treeNode.val;
        sumRootToLeaf(treeNode.left, sum, binary);
        sumRootToLeaf(treeNode.right, sum, binary);
    }

    public int minDiffInBST(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        deepsearch(root, list);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size() - 1; i++) {
            min = Math.min(min, list.get(i + 1) - list.get(i));
        }
        list.stream().forEach(System.out::print);
        return min;
    }


    public void deepsearch(TreeNode root, List<Integer> list) {
        if (root.left != null) deepsearch(root.left, list);
        list.add(root.val);
        if (root.right != null) deepsearch(root.right, list);

    }

    //面试题 04.10. 检查子树
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if(checkTree(t1,t2))return  true;
        else if(t1!=null){
            if(checkSubTree(t1.left, t2))return  true ;
            if(checkSubTree(t1.right, t2))return  true;
            return  false;
        }else return  false;
    }

    private  boolean checkTree(TreeNode t1,TreeNode t2){
        if(t1==null && t2!=null )return  false;
        if(t1!=null && t2==null)return  false;
        if(t1 == null)return  true;
        if(t1.val==t2.val){
            if(!checkTree(t1.left,t2.left))return  false;
            if(!checkTree(t1.right, t2.right))return  false;
            return  true;
        }
        else return  false;
    }


    //148. Sort List
    public ListNode sortList(ListNode head) {
        if(head==null)return  head;
        return  sortMerge(head);
    }

    private ListNode sortMerge(ListNode head){
        if(head.next==null) return  head;
        ListNode p =head, q=head,pre=null;
        while (q!=null && q.next!=null){
            pre=p;
            p=p.next;
            q=q.next.next;
        }
        pre.next =null;
        ListNode l =sortMerge(head);
        ListNode r =sortMerge(p);
        return  merge(l,r);
    }

    private  ListNode merge(ListNode l, ListNode r){
        ListNode head= new ListNode(0),last=head;
        while (l !=null && r !=null){
            if(l.val <=r.val){
                last.next =l;
                l =l.next;
            }else {
                last.next =r;
                r= r.next;
            }
            last =last.next;
        }
        if(l==null) last.next =r;
        else  last.next =l;
        return  head.next;
    }


}