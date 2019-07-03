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




}