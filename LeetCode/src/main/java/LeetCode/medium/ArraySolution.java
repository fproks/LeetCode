package LeetCode.medium;

import LeetCode.ListNode;

import java.util.*;

/**
 * @user: linhos
 * @Time: Create in 16:44 2017/9/25
 */
public class ArraySolution {

    //LeetCode 11 Container With Most Water（最大水容器）
    public int maxArea(int[] height) {
        int left = 0, right = height.length-1;
        int maxArea = 0;
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right])*(right-left));
            if (height[left] < height[right])
                left++;
            else right--;
        }
        return maxArea;
    }

    //33. Search in Rotated Sorted Array
    public int search(int[] nums, int target) {
        int min = 0, max = nums.length-1, mid = 0;
        while (min <= max) {
            mid = (max+min)/2;
            if (nums[mid] == target) return mid;
            if (nums[min] <= nums[mid]) {
                if (nums[min] <= target && nums[mid] > target)
                    max = mid-1;
                else min = mid+1;
            } else {
                if (nums[mid] < target && target <= nums[max]) {
                    min = mid+1;
                } else max = mid-1;
            }
        }
        return -1;
    }

    //34. Search for a Range

    /**
     * 边界搜索，分别搜索左边界和右边界
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums.length == 0) return res;
        int left = 0, right = nums.length-1;
        while (left <= right) {
            int mid = (left+right)/2;
            if (nums[mid] < target) {
                left = mid+1;
            } else right = mid-1;
        }
        int l = left;
        left = 0;
        right = nums.length-1;
        while (left <= right) {
            int mid = (left+right)/2;
            if (nums[mid] > target) right = mid-1;
            else left = mid+1;
        }
        if (l <= right) {
            res[0] = l;
            res[1] = right;
        }
        return res;
    }

    //36 Valid Sudoku
    public boolean isValidSudoku(char[][] board) {
        Set<Character> Rset = new HashSet<>();
        Set<Character> Cset = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            Rset.clear();
            Cset.clear();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && !Rset.add(board[i][j])) return false;
                if (board[j][i] != '.' && !Cset.add(board[j][i])) return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Rset.clear();
                for (int ti = i*3; ti < i*3+3; ti++) {
                    for (int tj = j*3; tj < j*3+3; tj++) {
                        char c = board[ti][tj];
                        if (c != '.') {
                            if (Rset.contains(c)) return false;
                            else Rset.add(c);
                        }
                    }
                }
            }
        }
        return true;
    }

    //39. Combination Sum
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> list = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        combinDFS(candidates, list, path, 0, target);
        return list;
    }

    private void combinDFS(int[] candid, List<List<Integer>> list, List<Integer> path, int idx, int target) {
        if (target == 0) {
            list.add(path);
            return;
        }
        for (int i = idx; i < candid.length && candid[i] <= target; i++) {
            List<Integer> tmp = new LinkedList<>(path);
            tmp.add(candid[i]);
            combinDFS(candid, list, tmp, i, target-candid[i]);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> list = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        combinDFS2(candidates, list, path, 0, target);
        return list;
    }

    //40. Combination Sum II
    private void combinDFS2(int[] candid, List<List<Integer>> list, List<Integer> path, int idx, int target) {
        if (target == 0) {
            list.add(path);
            return;
        } else if (idx == candid.length) return;
        for (int i = idx; i < candid.length && candid[i] <= target; i++) {
            List<Integer> tmp = new LinkedList<>(path);
            tmp.add(candid[i]);
            combinDFS2(candid, list, tmp, i+1, target-candid[i]);
            while (i+1 < candid.length && candid[i] == candid[i+1]) i++;
        }
    }

    //46. Permutations
    //获取数组的全排列，所有数字不重复
    //http://blog.csdn.net/happyaaaaaaaaaaa/article/details/51534048
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        for (int i = 0; i < used.length; i++)
            used[i] = false;
        createArrays(res, new ArrayList<Integer>(), nums, used);
        return res;
    }

    private void createArrays(List<List<Integer>> res, List<Integer> path, int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            res.add(path);
            return;
        }
        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                ArrayList<Integer> p = new ArrayList<>(path);
                p.add(nums[i]);
                used[i] = true;
                createArrays(res, p, nums, used);
                used[i] = false;
            }
        }
    }

    //46题的变种，题目中使用的数组可以是重复的
    //47. Permutations II
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) used[i] = false;
        createArraysUnique(res, new ArrayList<>(), nums, used);
        return res;
    }

    private void createArraysUnique(List<List<Integer>> res, List<Integer> path, int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            res.add(path);
            return;
        }
        for (int i = 0; i < used.length; i++) {
            if (i != 0 && nums[i] == nums[i-1] && !used[i-1]) continue;
            if (!used[i]) {
                used[i] = true;
                path.add(nums[i]);
                createArraysUnique(res, new ArrayList<>(path), nums, used);
                path.remove(path.size()-1);
                used[i] = false;
            }
        }
    }

    //48. Rotate Image
    //对数组进行90度旋转且不能再创建一个数组空间
    //http://www.cnblogs.com/93scarlett/p/6362085.html
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-1-i; j++) {
                int ri = n-i-1;
                int rj = n-j-1;
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[rj][ri];
                matrix[rj][ri] = tmp;
            }
        }
        int tmpn = n/2;
        for (int i = 0; i < tmpn; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n-1-i][j];
                matrix[n-1-i][j] = tmp;
            }
        }
    }







}
