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

    @Test
    public void canPlaceFlowersTest() throws Exception {
        int[] flowered = {1, 0, 0, 0, 1};
        ArraysSolution solution = new ArraysSolution();
        Assert.assertTrue(solution.canPlaceFlowers(flowered, 1));
    }

    @Test
    public void findMaxAverageTest() throws Exception {
        int[] nums = {1, 12, -5, -6, 50, 3};
        ArraysSolution solution = new ArraysSolution();
        Assert.assertEquals(12.75, solution.findMaxAverage(nums, 4), 0.01);
    }

    @Test
    public void findErrorNumsTest() throws Exception {
        int[] nums = {3, 2, 4, 3};
        ArraysSolution solution = new ArraysSolution();
        Assert.assertArrayEquals(new int[]{3, 1}, solution.findErrorNums(nums));
    }

    @Test
    public void imageSmootherTest() throws Exception {
        // int[][]M={{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        int[][] M = {{6, 9, 7}};
        ArraysSolution solution = new ArraysSolution();
        int[][] m = solution.imageSmoother(M);
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    @Test
    public void maxAreaOfIsland() {
        int[][] grid ={{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        LeetCode.easy.Kotlin.ArraysSolution kotlinSolution =new LeetCode.easy.Kotlin.ArraysSolution();
        Assert.assertEquals(4,kotlinSolution.maxAreaOfIsland(grid));
    }

    @Test
    public  void  countBinarySubstrings(){
        ArraysSolution solution =new ArraysSolution();
        Assert.assertEquals(6,solution.countBinarySubstrings("00110011"));
        Assert.assertEquals(4,solution.countBinarySubstrings("10101"));
    }

    @Test
    public void nextGreatestLetter() {
        char[] letter ={'c','f','j'};
        char target ='j';
        ArraysSolution solution =new ArraysSolution();
        char c =solution.nextGreatestLetter(letter,target);
        System.out.println(c);
    }

}