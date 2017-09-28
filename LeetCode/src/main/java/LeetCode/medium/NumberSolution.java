package LeetCode.medium;

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
}
