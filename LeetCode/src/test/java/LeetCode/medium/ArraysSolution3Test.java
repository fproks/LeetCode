package LeetCode.medium;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @user: linhos
 * @Time: Create in 14:18 2017/10/11
 */
public class ArraysSolution3Test {
    ArraysSolution3 solution = new ArraysSolution3();

    @Test
    public void singleNonDuplicate() throws Exception {
        int[] a = {1, 1, 2, 3, 3, 4, 4, 7, 7};
        Assert.assertEquals(2, solution.singleNonDuplicate(a));
    }

    @Test
    public  void  deckRevealedIncreasing(){
        int [] a = {17,13,11,2,3,5,7};
        Assert.assertArrayEquals(new int[]{2,13,3,11,5,17,7},solution.deckRevealedIncreasing(a));
    }

    @Test
    public void minFallingPathSum() {
        int[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] b = {{-19, 57}, {-40, -5}};
        Assert.assertEquals(12, solution.minFallingPathSum(a));
        System.out.println(solution.minFallingPathSum(b));
        Assert.assertEquals(-59, solution.minFallingPathSum(b));

    }

    @Test
    public  void  totalNQueens(){
        Assert.assertEquals(2,solution.totalNQueens(4));
    }

    @Test
    public  void  minTimeToVisitAllPoints(){
        Assert.assertEquals(7,solution.minTimeToVisitAllPoints(new int[][]{{1,1},{3,4},{-1,0}}));
    }




}