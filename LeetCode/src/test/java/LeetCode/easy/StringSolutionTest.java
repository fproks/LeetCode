package LeetCode.easy;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        List<String> rmp = solutions.letterCasePermutation(a);
        Assert.assertEquals(4, rmp.size());
        rmp.forEach(it -> {
            System.out.println(it);
        });

    }

    @Test
    public void NumUniqueEmails() {
        String[] emails = {"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"};
        Assert.assertEquals(2, solutions.NumUniqueEmails(emails));
    }

    @Test
    public void numSpecialEquivGroups() {
        String[] input3 = {"abc", "acb", "bac", "bca", "cab", "cba"};
        String[] input1 = {"a", "b", "c", "a", "c", "c"};
        String[] input2 = {"aa", "bb", "ab", "ba"};
        Assert.assertEquals(4, solutions.numSpecialEquivGroups(input2));
        Assert.assertEquals(3, solutions.numSpecialEquivGroups(input1));
        Assert.assertEquals(3, solutions.numSpecialEquivGroups(input3));
    }

    @Test
    public void mostCommonWord() {
        String s = "Bob hit a ball, the hit BALL flew far after it was hit.";
        Assert.assertEquals("ball", solutions.mostCommonWord(s, new String[]{"hit"}));
    }

    @Test
    public void removeOuterParentheses() {
        String input = "(()())(())";
        String output = "()()()";
        String input2 = "(()())(())(()(()))";
        Assert.assertEquals(output, solutions.removeOuterParentheses(input));
        Assert.assertEquals("()()()()(())", solutions.removeOuterParentheses(input2));
        Assert.assertEquals("", solutions.removeOuterParentheses("()()"));

    }

    @Test
    public void removeDuplicatesTest() {
        Assert.assertEquals("ca", solutions.removeDuplicates("abbaca"));
        Assert.assertEquals("a", solutions.removeDuplicates("aaaaaaaaa"));
    }

    @Test
    public  void  defangIPaddrTest(){
        Assert.assertEquals("1[.]1[.]1[.]1",solutions.defangIPaddr("1.1.1.1"));
    }

    @Test
    public  void countCharactersTest(){
        Assert.assertEquals(10,solutions.countCharacters( new String[]{"hello","world","leetcode"},"welldonehoneyr"));
    }

    @Test
    public  void numSmallerByFrequencyTest(){
       // Assert.assertArrayEquals(new int[]{1},solutions.numSmallerByFrequency(new String[] {"cbd"},   new String[]{"zaaaz"}));
       // Assert.assertArrayEquals(new int[]{1,2},solutions.numSmallerByFrequency(new String[] {"bbb","cc"},   new String[]{"a","aa","aaa","aaaa"}));
        Assert.assertArrayEquals(new int[]{6,1,1,2,3,3,3,1,3,2},solutions.numSmallerByFrequency(new String[] {"bba","abaaaaaa","aaaaaa","bbabbabaab","aba","aa","baab","bbbbbb","aab","bbabbaabb"},
                new String[]{"aaabbb","aab","babbab","babbbb","b","bbbbbbbbab","a","bbbbbbbbbb","baaabbaab","aa"}));
    }

    @Test
    public  void  maxNumberOfBalloons(){
        Assert.assertEquals(2,solutions.maxNumberOfBalloons("loonbalxballpoon"));
        Assert.assertEquals(0,solutions.maxNumberOfBalloons("leetcode"));
        Assert.assertEquals(1,solutions.maxNumberOfBalloons("nlaebolko"));
    }

    @Test
    public  void firstUniqChar(){
        Assert.assertEquals('b',solutions.firstUniqChar("abaccdeff"));
    }


    @Test
    public  void reverseLeftWords(){
        Assert.assertEquals(solutions.reverseLeftWords("12345",2),"34512");
    }
}