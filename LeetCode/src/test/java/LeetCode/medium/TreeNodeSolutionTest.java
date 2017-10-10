package LeetCode.medium;

import LeetCode.struct.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * @user: linhos
 * @Time: Create in 14:20 2017/10/9
 */
public class TreeNodeSolutionTest {
    private TreeNodeSolution solution = new TreeNodeSolution();

    @Test
    public void findFrequentTreeSum() throws Exception {
        TreeNode p = new TreeNode(5);
        p.left = new TreeNode(2);
        p.right = new TreeNode(-5);
        Assert.assertArrayEquals(new int[]{2}, solution.findFrequentTreeSum(p));
    }

}