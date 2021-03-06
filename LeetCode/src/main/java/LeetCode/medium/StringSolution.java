package LeetCode.medium;

import java.util.*;
import java.util.stream.Stream;

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
                    letter.add(tmp + s);
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
        int[] pos = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];
                pos[p1] += sum / 10;
                pos[p2] = sum % 10;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int p : pos)
            if (!(builder.length() == 0 && p == 0))
                builder.append(p);
        return builder.toString();
    }

    private HashMap<String, List<Integer>> hm = new HashMap<>();

    //241. Different Ways to Add Parentheses
    public List<Integer> diffWaysToCompute(String input) {
        if (hm.containsKey(input)) return hm.get(input);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*')
                for (Integer l : diffWaysToCompute(input.substring(0, i)))
                    for (Integer r : diffWaysToCompute(input.substring(i + 1, input.length())))
                        if (ch == '+') res.add(l + r);
                        else if (ch == '-') res.add(l - r);
                        else res.add(l * r);
        }
        if (res.size() == 0) res.add(Integer.valueOf(input));
        hm.put(input, res);
        return res;
    }

    //592. Fraction Addition and Subtraction
    //分子式加减
    //先对分子式进行切个
    //再对分子式进行运算
    //除以公约数
    public String fractionAddition(String expression) {
        String[] fs = expression.split("(?=[-,+])");
        int f = 0, m = 1;
        for (String s : fs) {
            int[] tmp = Stream.of(s.split("/")).mapToInt(Integer::parseInt).toArray();
            f = f * tmp[1];
            f += tmp[0] * m;
            m *= tmp[1];
        }
        String res = "";
        int gdc = Math.abs(gcd(f, m));
        res += f / gdc + "/" + m / gdc;
        return res;
    }

    //求任意两数的最大公约数
    private int gcd(int x, int y) {
        return x == 0 || y == 0 ? x + y : gcd(y, x % y);
    }

    //537. Complex Number Multiplication
    //复数的求积
    public String complexNumberMultiply(String a, String b) {
        String[] f = a.split("\\+");
        String[] s = b.split("\\+");
        int m = Integer.valueOf(f[0]) * Integer.valueOf(s[0]);
        int fi = Integer.valueOf(f[1].substring(0, f[1].length() - 1));
        int si = Integer.valueOf(s[1].substring(0, s[1].length() - 1));
        m = m - fi * si;
        int mi = Integer.valueOf(f[0]) * si + Integer.valueOf(s[0]) * fi;
        return "" + m + "+" + mi + "i";
    }


    //763. Partition Labels
    public List<Integer> partitionLabels(String S) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] chars = new int[26];
        for (int i = 0; i < S.length(); i++) {
            chars[S.charAt(i) - 'a'] = i;
        }
        int first = -1;
        int end = 0;
        for (int i = first + 1; i < S.length(); i++) {
            end = Math.max(end, chars[S.charAt(i) - 'a']);
            if (end == i) {
                list.add(end - first);
                first = end;
            }
        }
        return list;
    }

    //451. Sort Characters By Frequency
    public String frequencySort(String s) {
        int[] map = new int[256];
        StringBuilder stringBuilder = new StringBuilder();
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) map[s.charAt(i)]++;
        for (int i = 0; i < s.length() + 1; i++) list.add(new ArrayList<>());
        for (int i = 0; i < map.length; i++) if (map[i] != 0) list.get(map[i]).add(i);
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) != null) {
                List<Integer> tmp = list.get(i);
                for (int k = 0; k < tmp.size(); k++) {
                    for (int m = 0; m < i; m++) {
                        stringBuilder.append(Character.toChars(tmp.get(k)));
                    }
                }

            }
        }
        return stringBuilder.toString();
    }

    //https://leetcode.com/problems/letter-tile-possibilities/
    /*
    *AAB 的次数等于 AB 的次数+1 + AA 的次数+1 (每一个字母减1个)
    * AB 的次数等于(A 的次数+1) +(B 的次数+1) (每一个字母减1个)
    * AA 的次数等于(A 的次数+1)(每一个字母减1个)
    *
    *  */
    public int numTilePossibilities(String tiles) {
        int[] chars = new int[26];
        for (int i = 0; i < tiles.length(); i++) {
            chars[tiles.charAt(i) - 'A'] += 1;
        }
        return dfs(chars);
    }

    private int dfs(int[] chars) {
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            if (chars[i] == 0) continue;
            sum++;
            chars[i]--;
            sum += dfs(chars);
            chars[i]++;
        }
        return sum;
    }


    //131. Palindrome Partitioning
    //
    public List<List<String>> partition(String s) {
       List<List<String>> result=new ArrayList<>();
       List<String> list =new ArrayList<>();
       help(s,result,list,0);
       return  result;
    }

    public  void help(String s, List<List<String>> result,List<String> list,int start){
        if(start >=s.length()){
            result.add(new ArrayList<>(list));
            return;
        }
        for(int i =start;i<s.length();i++){
            if(isPalindrome(s,start,i)){
                list.add(s.substring(start,i+1));
                help(s,result,list,i+1);
                list.remove(list.size()-1);
            }
        }
    }

    private  boolean isPalindrome(String s ,int start,int end){
        while (start<end){
            if(s.charAt(start)!=s.charAt(end))return  false;
            start++;
            end--;
        }
        return  true;
    }


}
