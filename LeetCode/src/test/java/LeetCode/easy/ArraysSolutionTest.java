package LeetCode.easy;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @user: linhos
 * @Time: Create in 11:48 2017/9/21
 */
public class ArraysSolutionTest {
    @Test
    public void findLHS() throws Exception {
        int[] nums = {1, 3, 2, 2, 5, 2, 3, 7};
        ArraysSolution solution = new ArraysSolution();
        Assert.assertEquals(5, solution.findLHS(nums));
    }

}