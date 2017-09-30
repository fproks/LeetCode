package LeetCode.easy;

import java.util.*;

import LeetCode.struct.Solution;

/**
 * @user: linhos
 * @Time: Create in 10:37 2017/9/21
 */
public class ArraysSolution implements Solution {
    //566. Reshape the Matrix
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int first = nums.length;
        int sec = nums[0].length;
        if (first*sec != r*c) return nums;
        int[][] arra = new int[r][c];
        int s = first*sec;
        for (int i = 0; i < s; i++) {
            arra[i/c][i%c] = nums[i/sec][i%sec];
        }
        return arra;
    }

    //561. Array Partition I
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length/2;
        int maximum = 0;
        for (int i = 0; i < n; i++) {
            maximum += Math.min(nums[i*2], nums[i*2+1]);
        }
        return maximum;
    }

    //575. Distribute Candies
    public int distributeCandies(int[] candies) {
        HashSet<Integer> set = new HashSet<>();
        for (int c : candies) {
            set.add(c);
        }
        if (set.size() > candies.length/2) return candies.length/2;
        else return set.size();

    }

    // 581. Shortest Unsorted Continuous Subarray
    public int findUnsortedSubarray(int[] nums) {
        int[] tmp = Arrays.copyOf(nums, nums.length);
        Arrays.sort(tmp);
        int start = 0;
        while (start < nums.length && nums[start] == tmp[start]) start++;
        int end = nums.length-1;
        while (end > start && nums[end] == tmp[end]) end--;
        return end-start+1;
    }

    //594. Longest Harmonious Subsequence
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int start = 0, end = 0;
        int size = 0;
        while (end < nums.length) {
            if (nums[start] == nums[end]) end++;
            else {
                if (nums[end]-nums[start] == 1) {
                    size = size > end-start+1 ? size : end-start+1;
                    end++;
                } else start++;
            }
        }
        return size;
    }

    //598. Range Addition II
    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length <= 0) return m*n;
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        for (int[] tmp : ops) {
            a = a < tmp[0] ? a : tmp[0];
            b = b < tmp[1] ? b : tmp[1];
        }

        return a*b > m*n ? m*n : a*b;
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        int count = Integer.MAX_VALUE;
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                int tmp = map.get(list2[i])+i;
                if (count > tmp) {
                    list.clear();
                    count = tmp;
                }
                if (count == tmp)
                    list.add(list2[i]);
            }
        }
        return list.toArray(new String[list.size()]);
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int i = 0;
        while (i < flowerbed.length && n > 0) {
            if (flowerbed[i] == 0) {
                int next = (i == flowerbed.length-1) ? 0 : flowerbed[i+1];
                int prev = (i == 0) ? 0 : flowerbed[i-1];
                if (next == 0 && prev == 0) {
                    n--;
                    flowerbed[i] = 1;
                    i += 2;
                    continue;
                }
            }
            i++;
        }
        return n <= 0;
    }

    //628. Maximum Product of Three Numbers
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int maxf = 0, maxsf = 0;
        if (nums[0] < 0 && nums[1] < 0) {
            maxf = nums[0];
            maxsf = nums[1];
        }
        int size = nums.length-1;
        int max1 = nums[size], max2 = nums[size-1], max3 = nums[size-2];
        int tmp = maxf*maxsf*max1;
        int tmp1 = max1*max2*max3;
        return tmp > tmp1 ? tmp : tmp1;
    }

    //643. Maximum Average Subarray I
    public double findMaxAverage(int[] nums, int k) {
        int count = 0;
        double maxcount = 0.0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            if (i == k-1) maxcount = count;
            else if (i >= k) {
                count -= nums[i-k];
                maxcount = maxcount > count ? maxcount : count;
            }
        }
        return maxcount/k;
    }

    //645. Set Mismatch
    public int[] findErrorNums(int[] nums) {
        int[] buffer = new int[nums.length];
        for (int c : nums)
            buffer[c]++;
        int[] res = new int[2];
        for (int i = 0; i < buffer.length; i++) {
            if (buffer[i] == 2) res[0] = i;
            if (buffer[i] == 0) res[1] = i;
        }
        return res;
    }

    //661. Image Smoother 卷积的计算
    public int[][] imageSmoother(int[][] M) {
        int x = M.length, y = M[0].length;
        int[][] M1 = new int[x][y];
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++) {
                M1[i][j] = M[i][j];
            }
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++)
                M1[i][j] = pixSmoo(M, i, j);
        return M1;
    }

    private int pixSmoo(int[][] M, int x, int y) {
        int m = M.length-1 < x+1 ? M.length-1 : x+1;
        int n = M[0].length-1 < y+1 ? M[0].length-1 : y+1;
        int i = 0 > x-1 ? 0 : x-1;
        int j = 0 > y-1 ? 0 : y-1;
        int sum = 0, cnt = 0;
        for (; i <= m; i++)
            for (; j <= n; j++) {
                sum += M[i][j];
                cnt++;
            }
        return sum/cnt;
    }

    //665. Non-decreasing Array
    /*
    * http://blog.csdn.net/u010455714/article/details/77621972
    * 对于给定的数组 a1,a2,a3,a4,a5,…
    * 假设a4 < a3. 为了实现数组的单调非减，我们必须改变a4和a3其中的一个值，
    * 与此同时，为了后续计算的需要，我们应当尽可能使a4相对较小。
    * 此时，究竟是改变a3,还是a4取决于a2值的大小。
    * 我们假设a4 < a3， 那么a3 >= a2。
    * 因此，如果a4 < a2, 那么我们将改变a4, 最好的选择就是令 a4 = a3;
    * 否则的话，我们令a3 = a4， 但是这种情况我们并不需要考虑。
    * */
    public boolean checkPossibility(int[] nums) {
        if (nums.length <= 2) return true;
        boolean modifed = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i-1]) {
                if (modifed) return false;
                if (i-2 >= 0 && nums[i] < nums[i-2]) nums[i] = nums[i-1];
                modifed = true;
            }
        }
        return true;
    }


}
