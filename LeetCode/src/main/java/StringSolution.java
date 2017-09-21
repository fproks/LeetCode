import java.util.Arrays;

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


}
