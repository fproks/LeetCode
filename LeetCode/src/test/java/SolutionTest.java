import org.junit.Assert;
import org.junit.Test;

/**
 * @user: linhos
 * @Time: Create in 15:45 2017/9/19
 */
public class SolutionTest {

    @Test
    public void convertBST() throws Exception {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);
        Solution solution = new Solution();
        TreeNode treeNode = solution.convertBST(root);
        Assert.assertEquals(18, treeNode.val);
        Assert.assertEquals(20, treeNode.left.val);
        Assert.assertEquals(13, treeNode.right.val);
    }

}