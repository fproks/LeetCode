package LeetCode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * @user: linhos
 * @Time: Create in 8:37 2017/9/28
 */
public class NumberSolutionTest {
    private NumberSolution solution = new NumberSolution();

    @Test
    public void myPow() throws Exception {
        System.out.println(solution.myPow(34.00515, -3));
    }

    @Test
    public void superPow() throws Exception {

        /*
        * IntStream.of(nums).boxed().collect(Collectors.toList())
        * int[] to List
        * */
        int[] nums = new int[]{1, 7, 7, 4, 3, 1, 7, 0, 1, 4, 4, 9, 2,
                8, 5, 0, 0, 9, 3, 1, 2, 5, 9, 6, 0, 9, 9, 0, 9, 6, 0,
                5, 3, 7, 9, 8, 8, 9, 8, 2, 5, 4, 1, 9, 3, 8, 0, 5, 9,
                5, 6, 1, 1, 8, 9, 3, 7, 8, 5, 8, 5, 5, 3, 0, 4, 3, 1,
                5, 4, 1, 7, 9, 6, 8, 8, 9, 8, 0, 6, 7, 8, 3, 1, 1, 1,
                0, 6, 8, 1, 1, 6, 6, 9, 1, 8, 5, 6, 9, 0, 0, 1, 7, 1,
                7, 7, 2, 8, 5, 4, 4, 5, 2, 9, 6, 5, 0, 8, 1, 0, 9, 5,
                8, 7, 6, 0, 6, 1, 8, 7, 2, 9, 8, 1, 0, 7, 9, 4, 7, 6};
        Assert.assertEquals(solution.superPow1(3777, IntStream.of(nums).boxed().collect(Collectors.toList())),
                solution.superPow(3777, nums));
    }

}