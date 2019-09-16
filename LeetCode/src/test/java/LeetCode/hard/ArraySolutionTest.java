package LeetCode.hard;


import org.junit.Assert;
import org.junit.Test;

public class ArraySolutionTest {
    private  ArraySolution solution =new ArraySolution();
    @Test
    public void uniquePathsIII() {
        Assert.assertEquals(2,solution.uniquePathsIII(new int[][]{{1,0,0,0},{0,0,0,0},{0,0,2,-1}}));
    }
}
