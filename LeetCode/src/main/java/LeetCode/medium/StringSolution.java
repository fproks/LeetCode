package LeetCode.medium;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @user: linhos
 * @Time: Create in 8:39 2017/9/26
 */
public class StringSolution {

    //17. Letter Combinations of a Phone Number
    public List<String> letterCombinations(String digits) {
        LinkedList<String> letter = new LinkedList<>();
        String[] mapper = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        letter.add("");
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while (letter.peek().length() == i) {
                String tmp = letter.pop();
                for (char s : mapper[x].toCharArray()) {
                    letter.add(tmp+s);
                }
            }
        }
        return letter;
    }

    //43 Multiply-strings
    //两串字符串数字相乘
    //https://leetcode.com/problems/multiply-strings/description/
    //http://www.cnblogs.com/liujinhong/p/5999642.html
    public String multiply(String num1, String num2) {
        if (num1.isEmpty() || num2.isEmpty()) return new String();
        if (Objects.equals(num1, "0") || Objects.equals(num2, "0")) return "0";
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m+n];
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                int mul = (num1.charAt(i)-'0')*(num2.charAt(j)-'0');
                int p1 = i+j, p2 = i+j+1;
                int sum = mul+pos[p2];
                pos[p1] += sum/10;
                pos[p2] = sum%10;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int p : pos)
            if (!(builder.length() == 0 && p == 0))
                builder.append(p);
        return builder.toString();
    }




}
