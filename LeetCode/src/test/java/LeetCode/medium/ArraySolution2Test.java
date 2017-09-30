package LeetCode.medium;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @user: linhos
 * @Time: Create in 9:05 2017/9/30
 */
public class ArraySolution2Test {
    ArraySolution2 solution = new ArraySolution2();

    @Test
    public void minPathSum() throws Exception {
        int[][] nums = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        Assert.assertEquals(7, solution.minPathSum(nums));
    }

}