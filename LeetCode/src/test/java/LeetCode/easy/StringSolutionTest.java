package LeetCode.easy;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @user: linhos
 * @Time: Create in 14:04 2017/9/25
 */

public class StringSolutionTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private StringSolution solutions = new StringSolution();
    private StringSolutionKotlin solutionKotlin =new StringSolutionKotlin();

    @Test
    public void validPalindrome() throws Exception {
        String s = "abc";

        logger.info("test");
        Assert.assertFalse(solutions.validPalindrome(s));
    }

    @Test
    public void calPoints() throws Exception {
        String[] s = {"5", "-2", "4", "C", "D", "9", "+", "+"};
        Assert.assertEquals(27, solutions.calPoints(s));
    }

    @Test
    public void numJewelsInStones(){
        String J ="z";
        String S ="ZZ";
        Assert.assertEquals(solutionKotlin.numJewelsInStones(J,S),0);
    }

    public static void main(String[] args) {
        
    }

}