package LeetCode.medium;

/**
 * @user: linhos
 * @Time: Create in 16:44 2017/9/25
 */
public class ArraySolution {
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
}
