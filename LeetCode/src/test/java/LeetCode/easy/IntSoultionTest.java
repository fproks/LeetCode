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
    }

}