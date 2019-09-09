package LeetCode.easy;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @user: linhos
 * @Time: Create in 10:42 2017/9/22
 */
public class IntSoultionTest {
    @Test
    public void judgeSquareSum() throws Exception {
        IntSoultion soultion = new IntSoultion();
        Assert.assertTrue(soultion.judgeSquareSum(5));
        Assert.assertFalse(soultion.judgeSquareSum(3));
        Assert.assertTrue(soultion.judgeSquareSum(2));
        Assert.assertEquals(soultion.bitwiseComplement(5), 2);
    }

    @Test
    public void largestTimeFromDigitsTest() {
        IntSoultion soultion = new IntSoultion();
        Assert.assertEquals(soultion.largestTimeFromDigits(new int[]{1, 2, 3, 4}), "23:41");
        Assert.assertEquals(soultion.largestTimeFromDigits(new int[]{5, 5, 5, 5}), "");
        Assert.assertEquals(soultion.largestTimeFromDigits(new int[]{0, 0, 0, 0}), "00:00");
    }

    @Test
    public void distributeCandiesTest(){
        IntSoultion soultion =new IntSoultion();
        Assert.assertArrayEquals(new int[]{5,2,3},soultion.distributeCandies(10,3));
        Assert.assertArrayEquals(new int[]{1,2,3,1},soultion.distributeCandies(7,4));
    }

}