package LeetCode.easy;

import LeetCode.struct.Solution;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @user: linhos
 * @Time: Create in 10:34 2017/9/21
 */
public class StringSolution implements Solution {
    //541. Reverse String II
    public String reverseStr(String s, int k) {
        if (k == 0) return s;
        StringBuilder sBuilder = new StringBuilder(s);
        int count = s.length();
        int i = 0;
        while (i < count) {
            int j = Math.min(i + k - 1, count - 1);
            reverse(sBuilder, i, j);
            i += 2 * k;
        }
        return sBuilder.toString();
    }

    private void reverse(StringBuilder s, int start, int end) {
        while (start < end) {
            char startChar = s.charAt(start);
            char endChar = s.charAt(end);
            s.setCharAt(start, endChar);
            s.setCharAt(end, startChar);
            start++;
            end--;
        }
    }

    public boolean checkRecord(String s) {
        boolean A = false;
        int L = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                if (L >= 2) return false;
                else L++;
            } else {
                L = 0;
            }
            if (s.charAt(i) == 'A') {
                if (A) return false;
                else A = true;
            }
        }
        return true;
    }


    //557. Reverse Words in a String III
    public String reverseWords(String s) {
        if (s.length() <= 1) return s;
        char[] array = s.toCharArray();
        int first = 0, second = 0;
        for (; second < array.length; second++) {
            if (array[second] == ' ') {
                reverseArray(array, first, second - 1);
                first = second + 1;
            }
        }
        if (array[--second] != ' ') reverseArray(array, first, second);
        return new String(array);
    }

    private void reverseArray(char[] array, int first, int last) {
        while (first < last) {
            char tmp = array[first];
            array[first++] = array[last];
            array[last--] = tmp;
        }

    }

    //657. Judge Route Circle
    public boolean judgeCircle(String moves) {
        int ud = 0, lr = 0;
        int s = moves.length();
        for (int i = 0; i < s; i++) {
            switch (moves.charAt(i)) {
                case 'U':
                    ud++;
                    break;
                case 'D':
                    ud--;
                    break;
                case 'L':
                    lr++;
                    break;
                default:
                    lr--;
            }
        }
        return ud == 0 && lr == 0;
    }

    //680. Valid Palindrome II
    public boolean validPalindrome(String s) {
        int fir = 0, sec = s.length() - 1;
        boolean ispalind;
        while (fir <= sec) {
            if (s.charAt(fir) == s.charAt(sec)) {
                fir++;
                sec--;
            } else {
                ispalind = Partpalind(s, fir + 1, sec);
                return ispalind || Partpalind(s, fir, sec - 1);
            }
        }
        return true;
    }

    private boolean Partpalind(String s, int first, int sec) {
        while (first < sec) {
            if (s.charAt(first) != s.charAt(sec)) return false;
            first++;
            sec--;
        }
        return true;
    }

    //682. Baseball Game
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        int first, sec, tmp = 0;
        int sum = 0;
        for (String o : ops) {
            if (Pattern.matches("^-?\\d+$", o)) {
                tmp = Integer.parseInt(o);
                sum += tmp;
                stack.push(tmp);
            } else {
                if (stack.size() <= 0) continue;
                if (Objects.equals(o, "+")) {
                    first = stack.pop();
                    sec = stack.peek();
                    tmp = first + sec;
                    sum += tmp;
                    stack.push(first);
                    stack.push(tmp);
                } else if (Objects.equals(o, "D")) {
                    tmp = stack.peek() * 2;
                    sum += tmp;
                    stack.push(tmp);
                } else {
                    tmp = stack.pop();
                    sum -= tmp;
                }
            }
        }
        return sum;
    }

    //784. Letter Case Permutation
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        char[] c = S.toCharArray();
        //res.add(S);
        addLetterCase(res, c, 0);
        return res;
    }

    private void addLetterCase(List<String> res, char[] S, int index) {
        if (S.length == index) {
            res.add(new String(S));
            return;
        } else if (index < S.length) {
            if (Character.isAlphabetic(S[index])) {
                char i = S[index];
                addLetterCase(res, S, index + 1);
                if (i >= 'a' && i <= 'z') S[index] = (char) (i - 'a' + 'A');
                else S[index] = (char) (i - 'A' + 'a');
                addLetterCase(res, S, ++index);
            } else {
                addLetterCase(res, S, ++index);
            }
        }
    }


    public int peakIndexInMountainArray(int[] A) {
        int result = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) {
                result = i - 1;
                break;
            }
        }
        return result;
    }


    //TODO   rember
    //10. Regular Expression Matching
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1]) dp[0][i + 1] = true;
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public int NumUniqueEmails(String[] emails) {
        Set<String> emailSet = new HashSet<>();
        Stream.of(emails).forEach((email) -> {
            String[] ls = email.split("@");
            String name = ls[0];
            String[] tmp = name.split("/+")[0].split(".");
            name = String.join("", tmp);
            emailSet.add(name + "@" + ls[1]);
        });
        return emailSet.size();
    }


    public int numSpecialEquivGroups(String[] A) {
        HashSet<String> set =new HashSet<>();
        for(var str :A){
            if(!set.contains(str) && !set.contains(reverse(str))){
                set.add(str);
            }
        }
        return  set.size();
    }

    private String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }


}
