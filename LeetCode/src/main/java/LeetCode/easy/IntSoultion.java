package LeetCode.easy;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

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
        return Math.max(rec1[0], rec2[0]) < Math.min(rec1[2], rec2[2]) && Math.max(rec1[1], rec2[1]) < Math.min(rec1[3], rec2[3]);
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


    //949. Largest Time for Given Digits
    public String largestTimeFromDigits(int[] A) {
        int H = 0, S = 0;
        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == i) continue;
                for (int k = 0; k < 4; k++) {
                    if (k == j || k == i) continue;
                    for (int l = 0; l < 4; l++) {
                        if (l == k || l == j || l == i) continue;
                        int h = A[i] * 10 + A[j];
                        int s = A[k] * 10 + A[l];
                        if (h < 24 && s < 60) {
                            flag = true;
                            if (H < h) {
                                H = h;
                                S = s;
                            }
                            if (H == h) {
                                S = S > s ? S : s;
                            }
                        }
                    }
                }
            }
        }
        String str = "";
        if (flag) {
            str += (H < 10) ? ("0" + H) : H;
            str += ":";
            str += (S < 10) ? ("0" + S) : S;
        }
        return str;
    }

    //1103. Distribute Candies to People
    public int[] distributeCandies(int candies, int num_people) {
        int remain =candies;
        int[] result=new int[num_people];
        int idx=0,current=0;
        while (remain>0){
            if(idx>=num_people){idx=0;continue;}
            else {
                current =++current >remain? remain :current;
                result[idx++]+=current;
                remain-=current;
            }
        }
        return  result;
    }

    public String dayOfTheWeek(int day, int month, int year) {
        LocalDate date =LocalDate.of(year,month,day);
        DayOfWeek dow =date.getDayOfWeek();
        return  dow.getDisplayName(TextStyle.FULL,Locale.US);
    }


    //1281. Subtract the Product and Sum of Digits of an Integer
    public int subtractProductAndSum(int n) {
        int mut =1;
        int sub=0;
        while (n>=10){
            int p =n %10;
            mut*=p;
            sub+=p;
            n=n/10;
        }
        mut*=n;
        sub+=n;
        return  mut-sub;
    }

    //1342. Number of Steps to Reduce a Number to Zero
    public int numberOfSteps (int num) {
        int n =num;
        int cu=0;
        while (n!=0){
          if(n%2==1)n--;
          else n=n/2;
          cu++;
        }
        return  cu;
    }


}
