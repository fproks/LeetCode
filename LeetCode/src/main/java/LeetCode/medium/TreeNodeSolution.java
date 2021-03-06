package LeetCode.medium;

import LeetCode.struct.TreeNode;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
        dfsLevel(list, node.left, level + 1);
        dfsLevel(list, node.right, level + 1);
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

    //Most Frequent Subtree Sum
    public int[] findFrequentTreeSum(TreeNode root) {
        HashMap<Integer, Integer> maop = new HashMap<>();
        frequentTreeSumHashMap(maop, root);
        final int[] i = {0};
        ArrayList<Integer> list = new ArrayList<>();
        maop.forEach((k, v) -> {
            if (v > i[0]) i[0] = v;
        });
        maop.forEach((k, v) -> {
            if (v == i[0])
                list.add(k);
        });
        //将list 转换成int[]
        return list.stream().mapToInt(j -> j).toArray();
    }

    private int frequentTreeSumHashMap(HashMap<Integer, Integer> map, TreeNode root) {
        if (root != null) {
            int i = root.val + frequentTreeSumHashMap(map, root.left) + frequentTreeSumHashMap(map, root.right);
            map.merge(i, 1, (a, b) -> a + b);
            return i;
        } else return 0;
    }


    //654. Maximum Binary Tree
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 0) return null;
        return subConstructMaximumBinaryTree(nums, 0, nums.length);
    }

    private TreeNode subConstructMaximumBinaryTree(int[] nums, int start, int end) {
        if (start >= end) return null;
        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = start; i < end; i++) {
            if (max < nums[i]) {
                max = nums[i];
                index = i;
            }
        }
        TreeNode node = new TreeNode(max);
        node.left = subConstructMaximumBinaryTree(nums, start, index);
        node.right = subConstructMaximumBinaryTree(nums, index + 1, end);
        return node;
    }

    //617. Merge Two Binary Trees
    //两个树合并
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        else if (t1 == null) return t2;
        else if (t2 == null) return t1;
        TreeNode node = new TreeNode(t1.val + t2.val);
        node.left = mergeTrees(t1.left, t2.left);
        node.right = mergeTrees(t1.right, t2.right);
        return node;
    }

    //513. Find Bottom Left Tree Value
    //求最后一层的最左边节点的值
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int tmp = 0;
        int size = queue.size();
        while (!queue.isEmpty()) {
            tmp = queue.peek().val;
            while (size > 0) {
                TreeNode node = queue.poll();
                size--;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            size = queue.size();
        }
        return tmp;
    }

    //515. Find Largest Value in Each Tree Row
    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) return list;
        queue.add(root);
        while (!queue.isEmpty()) {
            int tmp = queue.peek().val;
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                tmp = tmp > node.val ? tmp : node.val;
                size--;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            list.add(tmp);
        }
        return list;
    }

    //526. Beautiful Arrangement
    public int countArrangment(int n) {
        int[] nums = new int[n];
        int res = findWay(nums, 1);
        return res;
    }

    private int findWay(int[] num, int index) {
        if (index == num.length + 1) return 1;
        int total = 0;
        for (int i = 0; i < num.length; i++) {
            if (num[i] != 1) {
                if ((i + 1) % index == 0 || index % (i + 1) == 0) {
                    int[] newNum = num.clone();
                    newNum[i] = 1;
                    total += findWay(newNum, index + 1);
                }
            }
        }
        return total;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder.length == 0) return null;
        if (preorder.length == 1) return new TreeNode(preorder[0]);
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            int tmp = preorder[i];
            TreeNode tmpNode = root;
            while (true) {
                if (tmpNode.val > tmp) {
                    if (tmpNode.left != null) {
                        tmpNode = tmpNode.left;
                        continue;
                    } else tmpNode.left = new TreeNode(tmp);
                    break;
                } else {
                    if (tmpNode.right != null) {
                        tmpNode = tmpNode.right;
                        continue;
                    } else tmpNode.right = new TreeNode(tmp);
                    break;
                }
            }
        }
        return root;
    }

    public TreeNode recoverFromPreorder(String S) {
        if (S.length() == 0) return null;
        int val = 0;
        if (S.indexOf('-') <= 0) {
            val = Integer.parseInt(S);
            return new TreeNode(val);
        } else {
            val = Integer.parseInt(S.substring(0, S.indexOf('-')));
            TreeNode tree = new TreeNode(val);
            String[] list = this.remormatterPreorder(S.substring(S.indexOf("-")));
            if (list.length > 0) tree.left = this.recoverFromPreorder(list[0]);
            if (list.length == 2) tree.right = this.recoverFromPreorder(list[1]);
            return tree;
        }
    }

    public String[] remormatterPreorder(String s) {
        List<String> result = new ArrayList<>();
        int idxList = this.findOnlyOnePreorder(s);

        if (idxList <= 0) result.add(this.reduceByPreorder(s.substring(1)));
        else {
            result.add(this.reduceByPreorder(s.substring(1, idxList)));
            result.add(this.reduceByPreorder(s.substring(idxList + 1)));
        }
        return result.toArray(new String[0]);
    }

    private String reduceByPreorder(String s) {
        StringBuilder builder = new StringBuilder();
        StringBuilder dp = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '-') dp.append('-');
            else {
                if (dp.length() != 0) builder.append(dp.delete(0, 1));
                builder.append(c);
                dp.delete(0, dp.length());
            }
        }
        return builder.toString();
    }

    private int findOnlyOnePreorder(String s) {
        int dp = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '-') dp++;
            else {
                if (dp == 1) return i - 1;
                else dp = 0;
            }
        }
        return 0;
    }

    // 1161. Maximum Level Sum of a Binary Tree
    public int maxLevelSum(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        int curridx = 1, idx = 0, maxValue = 0;
        list.add(root);
        while (!list.isEmpty()) {
            List<TreeNode> tmp = new ArrayList<>();
            int tmpMax = 0;
            for (TreeNode node : list) {
                if (node.left != null) tmp.add(node.left);
                if (node.right != null) tmp.add(node.right);
                tmpMax += node.val;

            }
            if (tmpMax > maxValue) {
                maxValue = tmpMax;
                idx = curridx;
            }
            curridx++;
            list =tmp;
        }
        return idx;
    }

    //145 二叉树后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result =new ArrayList<>();
        postorderTraversal(result,root);
        return  result;
    }

    private  void postorderTraversal(List<Integer> list,TreeNode root){
        if(root!=null){
            postorderTraversal(list,root.left);
            postorderTraversal(list, root.right);
            list.add(root.val);
        }
    }



    //106. Construct Binary Tree from Inorder and Postorder Traversal
    public TreeNode buildTree(int[] inorder, int[] postorder) {
       return  buildTree(inorder, postorder,0,inorder.length-1, 0,postorder.length-1);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder,int inorderStart,int inorderEnd,int postStart,int postEnd){
        if(inorderStart>inorderEnd || postStart >postEnd)return  null;
        int tmp =postorder[postEnd];
        TreeNode t =new TreeNode(tmp);
        int idx=0;
        while(inorder[inorderStart+idx]!=tmp){
            idx++;
        }
        t.left =buildTree(inorder,postorder,inorderStart,inorderStart+idx-1,postStart,postStart+idx-1);
        t.right=buildTree(inorder,postorder,inorderStart+idx+1,inorderEnd,postStart+idx,postEnd-1);
        return  t;
    }


    //105. Construct Binary Tree from Preorder and Inorder Traversal  前序和中序
    public TreeNode buildTreeWithPreAndInorder(int[] preorder, int[] inorder) {
        return  buildTreeWithPreAndInorder(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }
    private  TreeNode buildTreeWithPreAndInorder(int[] preorder, int[] inorder,int preStart,int preEnd,int inorStart,int inorEnd){
        if(preEnd<preStart || inorEnd<inorStart)return  null;
        int tmp =preorder[preStart];
        TreeNode t =new TreeNode(tmp);
        int idx=0;
        while (inorder[inorStart+idx]!=tmp){
            idx++;
        }
        t.left =buildTreeWithPreAndInorder(preorder, inorder,preStart+1,preStart+idx,inorStart,inorStart+idx-1);
        t.right=buildTreeWithPreAndInorder(preorder, inorder,preStart+idx+1,preEnd,inorStart+idx+1,inorEnd);
        return  t;
    }



}

