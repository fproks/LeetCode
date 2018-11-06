package LeetCode.easy;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @user: linhos
 * @Time: Create in 14:04 2017/9/25
 */

public class StringSolutionTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private StringSolution solutions = new StringSolution();
    private StringSolutionKotlin solutionKotlin = new StringSolutionKotlin();

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
    public void numJewelsInStones() {
        String J = "z";
        String S = "ZZ";
        Assert.assertEquals(solutionKotlin.numJewelsInStones(J, S), 0);
    }

    @Test
    public void letterCasePermutation() {
        String a = "a1b2";
        List<String> rmp =solutions.letterCasePermutation(a);
        Assert.assertEquals(4, rmp.size());
        rmp.forEach(it->{System.out.println(it);});

    }

    @Test
    public  void NumUniqueEmails(){
        String[] emails ={"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
        Assert.assertEquals(2,solutions.NumUniqueEmails(emails));
    }

    @Test
    public  void  numSpecialEquivGroups(){
        String[] input3 ={"abc","acb","bac","bca","cab","cba"};
        String[] input1={"a","b","c","a","c","c"};
        String[] input2 ={"aa","bb","ab","ba"};
        Assert.assertEquals(4,solutions.numSpecialEquivGroups(input2));
        Assert.assertEquals(3,solutions.numSpecialEquivGroups(input1));
        Assert.assertEquals(3,solutions.numSpecialEquivGroups(input3));
    }

    @Test
    public void mostCommonWord() {
        String s = "Bob hit a ball, the hit BALL flew far after it was hit.";
        Assert.assertEquals("ball", solutions.mostCommonWord(s, new String[]{"hit"}));
    }

}