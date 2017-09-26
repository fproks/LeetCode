package LeetCode.medium;

import java.util.LinkedList;
import java.util.List;

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


}
