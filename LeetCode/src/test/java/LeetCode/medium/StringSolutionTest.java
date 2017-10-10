package LeetCode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
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

    @Test
    public void multiply() throws Exception {
        String s1 = "123456789123456789123456789";
        String s2 = "987654321987654321987654321";
        System.out.println(solution.multiply(s1, s2));
        //Assert.assertEquals("2460", solution.multiply(s1, s2));
    }

    @Test
    public void SplitTest() throws Exception {
        String a = "-1/2+1/2+1/3";
        String[] tar = a.split("(?=[-,+])");
        Assert.assertEquals(3, tar.length);
        for (String s : tar) System.out.println(s);
    }

    @Test
    public void fractionAddition() throws Exception {
        String a = "1/3-1/2";
        System.out.println(solution.fractionAddition(a));
    }
}
