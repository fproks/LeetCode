package LeetCode.medium;

import java.util.ArrayList;
import java.util.List;

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


}
