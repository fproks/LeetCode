package LeetCode.easy;

import LeetCode.struct.Solution;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
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
        HashSet<String> set = new HashSet<>();
        for (String str : A) {
            if (!set.contains(str) && !set.contains(reverse(str))) {
                set.add(str);
            }
        }
        return set.size();
    }

    private String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    public String mostCommonWord(String paragraph, String[] banned) {

        Set<String> bannedSet = Arrays.stream(banned).collect(Collectors.toSet());
        String chars = paragraph.toLowerCase().replaceAll("\\pP", " ").replaceAll("  ", " ");

        return Arrays.stream(chars.split(" ")).
                filter(str -> !bannedSet.contains(str)).
                collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).  //统计
                entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();  //获取最大
    }

    public String removeOuterParentheses(String S) {
        StringBuilder builder = new StringBuilder();
        int idx = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(') {
                if (idx != 0) builder.append('(');
                idx++;
            }
            if (S.charAt(i) == ')') {
                idx--;
                if (idx != 0) builder.append(')');
            }
        }
        return builder.toString();
    }

    public String removeDuplicates2(String S) {
        StringBuilder builder = new StringBuilder();
        Character deleteChar = null;
        for (char c : S.toCharArray()) {
            if (builder.length() == 0) {
                if (deleteChar == null || deleteChar != c) {
                    builder.append(c);
                    deleteChar = null;
                } else {
                    continue;
                }
            } else {
                if (deleteChar != null && deleteChar == c) {
                    continue;
                } else if (builder.charAt(builder.length() - 1) == c) {
                    builder.deleteCharAt(builder.length() - 1);
                    deleteChar = c;
                } else {
                    builder.append(c);
                    deleteChar = null;
                }
            }
        }
        return builder.toString();
    }

    public String removeDuplicates(String S) {
        StringBuilder builder = new StringBuilder();
        for (char c : S.toCharArray()) {
            if (builder.length() == 0 || builder.charAt(builder.length() - 1) != c)
                builder.append(c);
            else {
                builder.deleteCharAt(builder.length() - 1);
            }
        }
        return builder.toString();
    }

    public String[] findOcurrences(String text, String first, String second) {
        String[] strings = text.split(" ");
        if (strings.length <= 2) return new String[0];
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i <= strings.length - 3; i++) {
            if (strings[i].equals(first) && strings[i + 1].equals(second)) arrayList.add(strings[i + 2]);
        }
        return arrayList.toArray(new String[0]);
    }

    public String gcdOfStrings(String str1, String str2) {
        int minLength = str1.length() < str2.length() ? str1.length() : str2.length();
        int commonDivisor = minLength;
        StringBuilder builder1 = new StringBuilder();
        while (commonDivisor > 0) {
            if (str1.length() % commonDivisor == 0 && str2.length() % commonDivisor == 0) {
                builder1.delete(0, builder1.length());
                String result = str1.substring(0, commonDivisor);
                int div = str1.length() / commonDivisor;
                while ((div--) > 0) {
                    builder1.append(result);
                }
                if (builder1.toString().equals(str1)) {
                    div = str2.length() / commonDivisor;
                    builder1.delete(0, builder1.length());
                    while ((div--) > 0) {
                        builder1.append(result);
                    }
                    if (builder1.toString().equals(str2)) return result;
                }
            }
            commonDivisor--;
        }
        return "";

    }

    // 1108. Defanging an IP Address
    public String defangIPaddr(String address) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < address.length(); i++) {
            if (address.charAt(i) != '.') {
                builder.append(address.charAt(i));
            } else
                builder.append("[.]");
        }
        return builder.toString();
    }

    //1160. Find Words That Can Be Formed by Characters
    public int countCharacters(String[] words, String chars) {
        int[] charsNumber = new int[26];
        for (char c : chars.toCharArray()) {
            charsNumber[c - 'a']++;
        }

        int result = 0;
        for (String str : words) {
            boolean flag = true;
            int[] strNumber = new int[26];
            for (char c : str.toCharArray()) {
                strNumber[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                if (strNumber[i] > charsNumber[i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result += str.length();
            }
        }
        return result;
    }

    // 1170. Compare Strings by Frequency of the Smallest Character
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] queriesInt = this.stringFrequency(queries);
        int[] wordsInt = this.stringFrequency(words);
        Arrays.sort(wordsInt);
        int[] result = new int[queries.length];
        for (int i = 0; i < queriesInt.length; i++) {
            int tmp = 0;
            for (; tmp < wordsInt.length; tmp++) {
                if (wordsInt[tmp] > queriesInt[i]) break;
            }
            result[i] = wordsInt.length - tmp;
        }
        return result;
    }

    private int[] stringFrequency(String[] words) {
        int[] wordsInt = new int[words.length];
        for (int j = 0; j < words.length; j++) {
            String q = words[j];
           int max=0;
           char tmpc='z';
           for (int i =0;i<q.length();i++){
               char c =q.charAt(i);
               if(tmpc==c)max++;
               else if(tmpc >c){
                   tmpc=c;
                   max=1;
               }
           }
            wordsInt[j] = max;
        }
        return wordsInt;
    }
    //1189. Maximum Number of Balloons
    public int maxNumberOfBalloons(String text) {
        int b =0,a=0,l=0,o=0,n=0;
        for (char c : text.toCharArray()){
            if(c=='b')b++;
            if(c=='a')a++;
            if(c=='l')l++;
            if(c=='o')o++;
            if(c=='n')n++;
        }
        o=o/2;
        l=l/2;
        int result=b;
        result = Math.min(Math.min(Math.min(Math.min(result, a),l),o),n);
        return  result;
    }

    public char firstUniqChar(String s) {
        List<Character> list =new LinkedList<>();
        Set<Character> set =new HashSet<>();
        for (char c : s.toCharArray()){
           if(set.contains(c))continue;
           if(list.contains(c)){
               set.add(c);
              list.remove(Character.valueOf(c));
           }else list.add(c);
        }
        if(list.size()>0)return  list.get(0);
        else return  ' ';
    }

}


