package LeetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @user: linhos
 * @Time: Create in 8:21 2017/9/28
 */
public class NumberSolution {
    //50 Pow(x,n) 快速求幂，用的是二分法
    public double myPow(double x, int n) {
        if (n < 0) {
            return 1.0/power(x, -n);
        }
        return power(x, n);
    }

    //快速幂算法a^b =(a^(b/2))^2
    private double power(double x, int n) {
        if (n == 0) return 1;
        double tmp = power(x, n/2);
        if ((n & 0x01) == 1) {
            return (tmp*tmp*x);
        } else return (tmp*tmp);
    }

    //a*b%c =a%c *b%c
    private int powerInt(int x, int n) {
        if (n == 0) return 1;
        int tmp = powerInt(x, n/2);
        if (n%2 == 1)
            return (tmp*tmp)%1337*x%1337;
        else return (tmp*tmp)%1337;
    }

    //372. Super Pow
    //求大数幂的摸
    //http://blog.csdn.net/wdlsjdl2/article/details/51910900

    /**
     * a^21 =(a^10)^2 *a^1
     * 因此tmp =a^1
     * a=a^10;
     * tmp =tmp *a^2
     * a*b%c =a%c *b%c
     * a^b =(a^(b/2))^2
     */
    public int superPow(int a, int[] b) {
        a = a%1337;
        int tmp = 1;
        for (int i = b.length-1; i >= 0; i--) {
            tmp = (tmp*powerInt(a, b[i]))%1337;
            a = powerInt(a, 10)%1337;
          /*  int jin = powerInt(10,b.length-1-i);
            tmp=(tmp*powerInt(powerInt(a,b[i]),jin))%1337;*/
        }
        return tmp;
    }

    //372. Super Pow 的另一种算法
    public int superPow1(int a, List<Integer> b) {
        if (b.isEmpty()) return 1;
        a = a%1337;
        int lastBit = b.remove(b.size()-1);
        return (powerInt(superPow1(a, b), 10)*powerInt(a, lastBit))%1337;
    }

    //89. Gray Code
    //求格雷码  G(N) = B(n) XOR (B(n)/2)
    //http://blog.csdn.net/makuiyu/article/details/44926463
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i) {
            list.add(i ^ i >> 1);
        }
        return list;
    }

    //62. Unique Paths 动态规划 (m,n)=(m-1,n)+(m,n-1);
    public int uniquePaths(int m, int n) {
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 1;
            }
        }
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                matrix[i][j] = matrix[i][j-1]+matrix[i-1][j];
        return matrix[m-1][n-1];
    }

    //63. Unique Paths II
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) obstacleGrid[i][j] = 0;
                else obstacleGrid[i][j] = 1;
            }
        }
        for (int j = 1; j < n; j++)
            if (obstacleGrid[0][j-1] == 0)
                obstacleGrid[0][j] = 0;

        for (int j = 1; j < m; j++)
            if (obstacleGrid[j-1][0] == 0)
                obstacleGrid[j][0] = 0;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 0)
                    obstacleGrid[i][j] = obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
            }
        }
        return obstacleGrid[m-1][n-1];
    }

    public double nthPersonGetsNthSeat(int n) {
        if(n==1) return  1.0;
        else  return  0.5;
    }







}
