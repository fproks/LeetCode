package LeetCode.medium;

import LeetCode.medium.Kotlin.StringSloutionKotlin;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @user: linhos
 * @Time: Create in 8:40 2017/9/26
 */
public class StringSolutionTest {
    StringSolution solution = new StringSolution();
    StringSloutionKotlin sloutionKotlin =new StringSloutionKotlin();

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

    @Test
    public void splitTest() throws Exception {
        String a = "1+-1i";
        String[] b = a.split("\\+");
        for (String s : b) {
            System.out.println(s);
        }
    }

    @Test
    public void complexNumberMultiply() throws Exception {
        String a = "1+-1i";
        String b = "1+-1i";
        System.out.println(solution.complexNumberMultiply(a, b));
    }

    @Test
    public void partitionLabels() {
        String s = "ababcbacadefegdehijhklij";
        ArrayList<Integer> e = new ArrayList<Integer>();
        e.add(9);
        e.add(7);
        e.add(8);
        List<Integer> res = solution.partitionLabels(s);
        Assert.assertArrayEquals(e.toArray(), res.toArray());
    }

    @Test
    public void partitionLabelsKotlin(){
        String s = "ababcbacadefegdehijhklij";
        ArrayList<Integer> e = new ArrayList<Integer>();
        e.add(9);
        e.add(7);
        e.add(8);
        List<Integer> res =sloutionKotlin.partitionLabels(s);
        Assert.assertArrayEquals(e.toArray(),res.toArray());
    }


    @Test
    public  void customSortStringKotlin(){
        String s ="cba";
        String t ="abcd";
        System.out.println(sloutionKotlin.customSortString(s,t));
    }

    @Test
    public void countSubstrings(){
        String s ="aaa";
        Assert.assertEquals(6,sloutionKotlin.countSubstrings(s));
    }

    @Test
    public  void shortestCompletingWord(){
        String licence ="1s3 PSt";
        String[] words= {"step", "steps", "stripe", "stepple"};
        Assert.assertEquals("steps",sloutionKotlin.shortestCompletingWord(licence,words));
    }

    @Test
    public void frequencySort(){
        String s ="tree";
        System.out.println(solution.frequencySort(s));
    }




}
