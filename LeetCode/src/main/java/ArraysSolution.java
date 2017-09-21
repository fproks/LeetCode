import java.util.Arrays;

/**
 * @user: linhos
 * @Time: Create in 10:37 2017/9/21
 */
public class ArraysSolution implements Solution {
    //566. Reshape the Matrix
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int first = nums.length;
        int sec = nums[0].length;
        if (first * sec != r * c) return nums;
        int[][] arra = new int[r][c];
        int s = first * sec;
        for (int i = 0; i < s; i++) {
            arra[i / c][i % c] = nums[i / sec][i % sec];
        }
        return arra;
    }

    //561. Array Partition I
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length / 2;
        int maximum = 0;
        for (int i = 0; i < n; i++) {
            maximum += Math.min(nums[i * 2], nums[i * 2 + 1]);
        }
        return maximum;
    }
}
