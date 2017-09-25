package LeetCode.easy;

/**
 * @user: linhos
 * @Time: Create in 10:38 2017/9/22
 */
public class IntSoultion {
    //633. Sum of Square Numbers
    public boolean judgeSquareSum(int c) {
        int first = 0, sec = (int) Math.sqrt(c);
        while (first <= sec) {
            int cur = first * first + sec * sec;
            if (cur == c) return true;
            else if (cur < c) {
                first++;
            } else
                sec--;
        }
        return false;
    }
}
