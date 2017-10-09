package LeetCode.medium;

import LeetCode.struct.Interval;

import java.util.*;

/**
 * @user: linhos
 * @Time: Create in 14:47 2017/9/29
 */
public class ArraySolution2 {

    //90. Subsets II
    //对应的是存在重复时候的情况
    //https://segmentfault.com/a/1190000010155861
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        getsubset(nums, 0, lists, new ArrayList<>());
        return lists;
    }

    private void getsubset(int[] nums, int idx, List<List<Integer>> list, List<Integer> path) {
        list.add(new ArrayList<>(path));
        for (int i = idx; i < nums.length; i++) {
            if (i > idx && nums[i] == nums[i-1]) continue;  //排除了当前情况，导致已经可以添加重复的
            path.add(nums[i]);
            getsubset(nums, i+1, list, path);
            path.remove(path.size()-1);
        }
    }

    //56. Merge Intervals
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 0) return intervals;
        List<Interval> list = new ArrayList<>();
        intervals.sort(Comparator.comparingInt(a -> a.start));
        Interval tmp;
        for (Interval interval : intervals) {
            tmp = interval;
            if (list.size() == 0) list.add(tmp);
            else {
                Interval val = list.remove(list.size()-1);
                if (val.end >= tmp.start) {
                    val.end = val.end > tmp.end ? val.end : tmp.end;
                    list.add(val);
                } else {
                    list.add(val);
                    list.add(tmp);
                }
            }
        }
        return list;
    }


    //56. Merge Intervals 的另一种方法
    public List<Interval> merge1(List<Interval> intervals) {
        int n = intervals.size();
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            Interval tmp = intervals.get(i);
            start[i] = tmp.start;
            end[i] = tmp.end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        List<Interval> res = new ArrayList<>();
        for (int i = 0, j = 0; i < n; i++) {
            if (i == n-1 || start[i+1] > end[i]) {
                res.add(new Interval(start[j], end[i]));
                j = i+1;
            }
        }
        return res;
    }

    //64. Minimum Path Sum 动态规划
    //当前状态只和上一个状态有关系
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 1; i < m; i++)
            grid[i][0] += grid[i-1][0];
        for (int i = 1; i < n; i++)
            grid[0][i] += grid[0][i-1];
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) {
                grid[i][j] = Math.min(grid[i-1][j], grid[i][j-1])+grid[i][j];
            }
        return grid[m-1][n-1];
    }

    //75. Sort Colors
    public void sortColors(int[] nums) {
        int size = nums.length;
        int r = 0, w = 0, b = 0;
        for (int i : nums) {
            switch (i) {
                case 0:
                    r++;
                    break;
                case 1:
                    w++;
                    break;
                case 2:
                    b++;
                    break;
            }
        }
        for (int i = 0; i < size; i++)
            if (r != 0) {
                nums[i] = 0;
                r--;
            } else if (w != 0) {
                nums[i] = 1;
                w--;
            } else nums[i] = 2;
    }

    //215 Kth Largest Element in an Array
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i : nums) {
            if (queue.size() < k) queue.add(i);
            else {
                if (i > queue.peek()) {
                    queue.poll();
                    queue.add(i);
                }
            }
        }
        return queue.peek();
    }

    //240. Search a 2D Matrix II
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int n = matrix.length;
        int m = matrix[0].length;
        int i = 0, j = m-1;
        while (i < n && j >= 0) {
            if (matrix[i][j] < target)
                i++;
            else if (matrix[i][j] > target)
                j--;
            else return true;
        }
        return false;
    }

    //201. Bitwise AND of Numbers Range
    //http://blog.csdn.net/xudli/article/details/45912649
    public int rangeBitwiseAnd(int m, int n) {
        int bit = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            bit++;
        }
        return m << bit;
    }

}
