package LeetCode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @user: linhos
 * @Time: Create in 8:40 2017/9/26
 */
public class StringSolutionTest {
    StringSolution solution = new StringSolution();

    @Test
    public void letterCombinations() throws Exception {
        List<String> letter = solution.letterCombinations("7");
        Assert.assertEquals(4, letter.size());
        for (String s : letter) {
            System.out.println(s);
        }


    }
}
