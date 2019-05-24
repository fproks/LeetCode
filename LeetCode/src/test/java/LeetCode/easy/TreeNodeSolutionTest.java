package LeetCode.easy;

import LeetCode.struct.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @user: linhos
 * @Time: Create in 15:45 2017/9/19
 */
public class TreeNodeSolutionTest {

    @Test
    public void convertBST() throws Exception {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);
        TreeNodeSolution treeNodeSolution = new TreeNodeSolution();
        TreeNode treeNode = treeNodeSolution.convertBST(root);
        Assert.assertEquals(18, treeNode.val);
        Assert.assertEquals(20, treeNode.left.val);
        Assert.assertEquals(13, treeNode.right.val);
    }

    @Test
    public void reverseStrTest() throws Exception {
        String s = "hyzqyljrnigxvdtneasepfahmtyhlohwxmkqcdfehybknvdmfrfvtbsovjbdhevlfxpdaovjgunjqlimjkfnqcqnajmebeddqsgl";
        StringSolution treeNodeSolution = new StringSolution();
        System.out.println(s.length());
        System.out.println(treeNodeSolution.reverseStr(s, 39));
        // Assert.assertArrayEquals("bacdfeg".toCharArray(),treeNodeSolution.reverseStr(s,2).toCharArray());
    }

    @Test
    public void diameterOfBinaryTreeTest() throws Exception {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        TreeNode p = root.left;
        p.left = new TreeNode(4);
        p.right = new TreeNode(5);
        root.right = new TreeNode(3);
        TreeNodeSolution treeNodeSolution = new TreeNodeSolution();
        Assert.assertEquals(3, treeNodeSolution.diameterOfBinaryTree(root));
    }

    @Test
    public void reverseWordsTest() throws Exception {
        StringSolution treeNodeSolution = new StringSolution();
        System.out.println(treeNodeSolution.reverseWords("Let's take LeetCode contest"));
    }

    @Test
    public void tree2strTest() throws Exception {
        TreeNodeSolution solution = new TreeNodeSolution();
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        TreeNode p = tree.left;
        p.right = new TreeNode(4);
        tree.right = new TreeNode(3);
        System.out.println(solution.tree2str(tree));
    }

    @Test
    public void averageOfLevelsTest() throws Exception {
        TreeNodeSolution solution = new TreeNodeSolution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        TreeNode p = root.right;
        p.left = new TreeNode(15);
        p.right = new TreeNode(7);
        List<Double> tmp = solution.averageOfLevels(root);
        for (Double i : tmp) {
            System.out.println(i);
        }
    }
    @Test
    public void arrayPairSumTest() throws Exception {
        ArraysSolution treeNodeSolution = new ArraysSolution();
        int[] nums = new int[]{1, 4, 3, 2};
        Assert.assertEquals(4, treeNodeSolution.arrayPairSum(nums));
    }

    @Test
    public void filtTest() throws Exception {
        TreeNodeSolution treeNodeSolution = new TreeNodeSolution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        TreeNode p = root.left;
        p.left = new TreeNode(4);
        p = root.right;
        p.left = new TreeNode(5);
        Assert.assertEquals(11, treeNodeSolution.findTilts(root));
        // Assert.assertEquals(11,treeNodeSolution.filt(root));
    }



    @Test
    public void sumRootToLeaf(){
        TreeNodeSolution treeNodeSolution = new TreeNodeSolution();
        TreeNode root =new TreeNode(1);
        root.left=new TreeNode(0);
        root.right =new TreeNode(1);
        root.left.left =new TreeNode(0);
        root.left.right =new TreeNode(1);
        root.right.left =new TreeNode(0);
        root.right.right=new TreeNode(1);
        Assert.assertEquals(treeNodeSolution.sumRootToLeaf(root),22);
    }

    @Test
    public  void  minDiffInBST(){
        TreeNodeSolution treeNodeSolution = new TreeNodeSolution();
        TreeNode root =new TreeNode(4);
        root.left =new TreeNode(2);
        root.right =new TreeNode(6);
        root.left.left =new TreeNode(1);
        root.left.right =new TreeNode(3);
        System.out.println(treeNodeSolution.minDiffInBST(root));
    }

}