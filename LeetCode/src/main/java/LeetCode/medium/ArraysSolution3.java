package LeetCode.medium;

import java.util.*;

/**
 * @Author : linhos
 * @Time : Created in 上午9:31 17-10-11
 * @Site :
 * @PACKAGE : LeetCode.medium
 * @File : ArraysSolution3.java
 */
public class ArraysSolution3 {
    //419. Battleships in a Board
    public int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    if (i-1 >= 0 && board[i-1][j] == 'X') continue;
                    if (j-1 >= 0 && board[i][j-1] == 'X') continue;
                    count++;
                }
            }
        }
        return count;
    }

    //338. Counting Bits
    public int[] countBits(int num) {
        int[] res = new int[num+1];
        res[0] = 0;
        int base = 1;
        while (base <= num) {
            int next = base*2;
            for (int i = base; i < next && i <= num; i++)
                res[i] = res[i-base]+1;
            base = next;
        }
        return res;
    }

    //690. Employee Importance
    public int getImportance(List<Employee> employees, int id) {
        int imp = 0;
        for (Employee employee : employees) {
            if (employee.id == id) {
                imp += employee.importance;
                for (Integer subordinate : employee.subordinates) {
                    imp += getImportance(employees, subordinate);
                }
            }
        }
        return imp;
    }

    //442. Find All Duplicates in an Array
    public List<Integer> findDuplicates(int[] nums) {
        int i = 0;
        for (; i < nums.length; ) {
            if (nums[nums[i]-1] != nums[i]) {
                int tmp = nums[i];
                nums[i] = nums[tmp-1];
                nums[tmp-1] = tmp;
            } else i++;
        }
        List<Integer> list = new ArrayList<>();
        for (i = 0; i < nums.length; i++)
            if (nums[i] != i+1) list.add(nums[i]);
        return list;
    }

    private class Employee {
        int id;
        int importance;
        List<Integer> subordinates;
    }
}
