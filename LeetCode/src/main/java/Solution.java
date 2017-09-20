import java.util.Arrays;

/**
 * @user: linhos
 * @Time: Create in 15:25 2017/9/19
 */
public class Solution {
    int result = 0;
    private int convertBSTsum = 0;
    //543. Diameter of Binary Tree
    private int diameter = 0;

    TreeNode convertBST(TreeNode root) {
        convertFromRight(root);
        return root;
    }

    //538. Convert BST to Greater Tree
    // 后序遍历，从大到小
    private void convertFromRight(TreeNode root) {
        if (root == null) return;
        convertFromRight(root.right);
        root.val += convertBSTsum;
        convertBSTsum = root.val;
        convertFromRight(root.left);
    }

    //541. Reverse String II
    public String reverseStr(String s, int k) {
        if (k == 0) return s;
        StringBuilder sBuilder = new StringBuilder(s);
        int count = s.length();
        int i = 0;
        while (i < count) {
            int j = Math.min(i + k - 1, count - 1);
            reverse(sBuilder, i, j);
            i += 2 * k;
        }
        return sBuilder.toString();
    }

    private void reverse(StringBuilder s, int start, int end) {
        while (start < end) {
            char startChar = s.charAt(start);
            char endChar = s.charAt(end);
            s.setCharAt(start, endChar);
            s.setCharAt(end, startChar);
            start++;
            end--;
        }
    }

    public int diameterOfBinaryTree(TreeNode root) {
        deepthTree(root);
        return diameter <= 1 ? diameter : diameter - 1;
    }

    private int deepthTree(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int left = deepthTree(root.left);
        int right = deepthTree(root.right);
        int tmp = left + right + 1;
        diameter = diameter > tmp ? diameter : tmp;
        return left > right ? left + 1 : right + 1;
    }

    public boolean checkRecord(String s) {
        boolean A = false;
        int L = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                if (L >= 2) return false;
                else L++;
            } else {
                L = 0;
            }
            if (s.charAt(i) == 'A') {
                if (A) return false;
                else A = true;
            }
        }
        return true;
    }

    //557. Reverse Words in a String III
    public String reverseWords(String s) {
        if (s.length() <= 1) return s;
        char[] array = s.toCharArray();
        int first = 0, second = 0;
        for (; second < array.length; second++) {
            if (array[second] == ' ') {
                reverseArray(array, first, second - 1);
                first = second + 1;
            }
        }
        if (array[--second] != ' ') reverseArray(array, first, second);
        return new String(array);
    }

    private void reverseArray(char[] array, int first, int last) {
        while (first < last) {
            char tmp = array[first];
            array[first++] = array[last];
            array[last--] = tmp;
        }
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

    public int findTilt(TreeNode root) {
        if (root == null) return 0;
        int left = 0, right = 0;
        left = findTilt(root.left);
        right = findTilt(root.right);
        int ll = 0, rr = 0;
        if (root.left != null) ll = root.left.val;
        if (root.right != null) rr = root.right.val;
        return left + right + Math.abs(ll - rr);
    }

    public int filt(TreeNode root) {
        if (root == null) return 0;
        int left = root.left == null ? 0 : root.left.val;
        int right = root.right == null ? 0 : root.right.val;
        return Math.abs(left - right) + filt(root.left) + filt(root.right);
    }

    public int findTilts(TreeNode root) {
        postOrder(root);
        return result;
    }

    private int postOrder(TreeNode root) {
        if (root == null) return 0;

        int left = postOrder(root.left);
        int right = postOrder(root.right);

        result += Math.abs(left - right);

        return left + right + root.val;
    }

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

}
