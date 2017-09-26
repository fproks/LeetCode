package LeetCode.medium;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @user: linhos
 * @Time: Create in 8:39 2017/9/26
 */
public class ArraySolutionTest {
    ArraySolution solution = new ArraySolution();

    @Test
    public void letterCombinations() throws Exception {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        Assert.assertEquals(4, solution.search(nums, 0));
    }

}