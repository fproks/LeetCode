package LeetCode.medium;

import LeetCode.struct.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

}
