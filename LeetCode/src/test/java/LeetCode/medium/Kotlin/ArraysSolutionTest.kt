package LeetCode.medium.Kotlin;

import org.junit.Assert;
import org.junit.Test;

public class ArraysSolutionTest {
    private  ArraysSolution solutionKotlin =new ArraysSolution();

    @Test
    public void dailyTemperatures(){
        int[]  temperatures = {73, 74, 75, 71, 69, 72, 76, 73};

        int[]  result ={1, 1, 4, 2, 1, 1, 0, 0};

        Assert.assertArrayEquals(result,solutionKotlin.dailyTemperatures(temperatures));
    }
}
