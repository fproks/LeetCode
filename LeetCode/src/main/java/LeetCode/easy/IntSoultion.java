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

    //836. Rectangle Overlap
    //https://www.cnblogs.com/king-3/p/9063963.html
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return Math.max(rec1[0],rec2[0])<Math.min(rec1[2],rec2[2]) && Math.max(rec1[1],rec2[1]) <Math.min(rec1[3],rec2[3]);
    }

    public int bitwiseComplement(int N) {
        if (N == 0) return 1;
        int tmp = 0, n = N;
        while (n > 0) {
            tmp = (tmp << 1) + 1;
            n = n >> 1;
        }
        return tmp ^ N;
    }
}
