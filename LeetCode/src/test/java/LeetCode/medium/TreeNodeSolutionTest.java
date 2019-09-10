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

    @Test
    public  void  bstFromPreorder(){
        solution.bstFromPreorder(new int[]{8,5,1,7,10,12});
    }

    @Test
    public void remormatterPreorder(){
       String[] res = solution.remormatterPreorder("-2--3--4-5--6--7");
       Assert.assertEquals(2,res.length);
        for (var s1 : res){
            System.out.println(s1);
        }
        res =solution.remormatterPreorder("-2--3---4-5--6---7");
        Assert.assertEquals(2,res.length);
        for (var s1 : res){
            System.out.println(s1);
        }

        TreeNode treeNode = solution.recoverFromPreorder("1-2--3---4-5--6---7");
        treeNode =solution.recoverFromPreorder("1-2--3--4-5--6--7");
        System.out.println(treeNode.val);
    }

}