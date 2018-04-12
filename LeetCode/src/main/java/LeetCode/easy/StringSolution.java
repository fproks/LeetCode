package LeetCode.easy;

import LeetCode.struct.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.regex.Pattern;

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
      if(S.length ==index){
          res.add(new String(S));
          return;
      }else if(index<S.length) {
          if(Character.isAlphabetic(S[index])){
              char i =S[index];
              addLetterCase(res,S,index+1);
              if(i>='a' && i<='z')S[index]=(char)(i-'a'+'A');
              else  S[index]=(char)(i-'A'+'a');
              addLetterCase(res,S,++index);
          }else {
              addLetterCase(res,S,++index);
          }
      }
    }


}
