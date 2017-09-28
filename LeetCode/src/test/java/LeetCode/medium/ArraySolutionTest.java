package LeetCode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void searchRange() throws Exception {
        int[] nums = {5, 7, 7, 8, 8, 10};
        Assert.assertArrayEquals(new int[]{3, 4}, solution.searchRange(nums, 8));
    }

    @Test
    public void combinationSum() throws Exception {
        int[] nums = {2, 3, 6, 7};
        Assert.assertEquals(2, solution.combinationSum(nums, 7).size());
    }

    @Test
    public void combinationSum2() throws Exception {
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
        Assert.assertEquals(4, solution.combinationSum2(nums, 8).size());
    }

    @Test
    public void permute() throws Exception {
        int[] nums = {1, 2, 3, 4};
        Assert.assertEquals(24, solution.permute(nums).size());
    }

    @Test
    public void spiralOrder() throws Exception {
        int[][] nums = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        List<Integer> list = solution.spiralOrder(nums);
        list.forEach(System.out::print);
    }

}