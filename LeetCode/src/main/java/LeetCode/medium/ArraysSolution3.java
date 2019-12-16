package LeetCode.medium;

import java.util.*;
import java.util.stream.IntStream;

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
                    if (i - 1 >= 0 && board[i - 1][j] == 'X') continue;
                    if (j - 1 >= 0 && board[i][j - 1] == 'X') continue;
                    count++;
                }
            }
        }
        return count;
    }

    //338. Counting Bits
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        res[0] = 0;
        int base = 1;
        while (base <= num) {
            int next = base * 2;
            for (int i = base; i < next && i <= num; i++)
                res[i] = res[i - base] + 1;
            base = next;
        }
        return res;
    }

    //406. Queue Reconstruction by Height
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> o1[0] != o2[0] ? -o1[0] + o2[0] : o1[1] - o2[1]);
        List<int[]> res = new LinkedList<>();
        for (int[] person : people) {
            res.add(person[1], person);
        }
        return res.toArray(new int[people.length][]);
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
            if (nums[nums[i] - 1] != nums[i]) {
                int tmp = nums[i];
                nums[i] = nums[tmp - 1];
                nums[tmp - 1] = tmp;
            } else i++;
        }
        List<Integer> list = new ArrayList<>();
        for (i = 0; i < nums.length; i++)
            if (nums[i] != i + 1) list.add(nums[i]);
        return list;
    }

    public int singleNonDuplicate(int[] nums) {
        int f = 0, s = nums.length - 1;
        int idx = 0;
        while (f < s) {
            idx = (s + f) / 2;
            if (nums[idx] != nums[idx + 1] && nums[idx] != nums[idx - 1])
                return nums[idx];
            else if ((nums[idx] == nums[idx + 1] && idx % 2 == 0) || (nums[idx] == nums[idx - 1] && idx % 2 == 1))
                f = idx + 1;
            else s = idx - 1;
        }
        return nums[f];
    }

    private class Employee {
        int id;
        int importance;
        List<Integer> subordinates;
    }

    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        LinkedList<Integer> result = new LinkedList<>();
        for (int i = deck.length - 1; i >= 0; i--) {
            if (result.size() >= 2) result.addFirst(result.pollLast());
            result.addFirst(deck[i]);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    //931. Minimum Falling Path Sum
    public int minFallingPathSum(int[][] A) {
        int N = A.length;
        int[][] dt = new int[N][2];
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            dt[i][0] = A[0][i];
        }
        for (int i = 1; i < N; i++) {
            int ni = i % 2;
            int fi = (i + 1) % 2;
            for (int j = 0; j < N; j++) {
                if (j == 0) dt[j][ni] = Math.min(dt[j][fi], dt[j + 1][fi]) + A[i][j];
                else if (j == N - 1) dt[j][ni] = Math.min(dt[j - 1][fi], dt[j][fi]) + A[i][j];
                else
                    dt[j][ni] = Math.min(Math.min(dt[j - 1][fi], dt[j][fi]), dt[j + 1][fi]) + A[i][j];
            }
        }
        int ri = (N + 1) % 2;
        for (int i = 0; i < N; i++)
            result = Math.min(result, dt[i][ri]);
        return result;
    }

    // n 皇后 51
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n == 0) return res;
        Set<Integer> col = new HashSet<>();
        Set<Integer> master = new HashSet<>();
        Set<Integer> slave = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        backtrack(0, n, col, master, slave, stack, res);
        return res;
    }

    private void backtrack(int row, int n,
                           Set<Integer> col,
                           Set<Integer> master,
                           Set<Integer> slave,
                           Stack<Integer> stack,
                           List<List<String>> res) {
        if (row == n) {
            List<String> board = cover2Board(stack, n);
            res.add(board);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!col.contains(i) && !master.contains(row + i) && !slave.contains(row - i)) {
                stack.add(i);
                col.add(i);
                master.add(row + i);
                slave.add(row - i);
                backtrack(row + 1, n, col, master, slave, stack, res);
                stack.pop();
                col.remove(i);
                master.remove(row + i);
                slave.remove(row - i);
            }
        }
    }

    private List<String> cover2Board(Stack<Integer> stack, int n) {
        List<String> board = new ArrayList<>();
        for (Integer num : stack) {
            StringBuilder builder = new StringBuilder();
            IntStream.range(0, n).forEach(i -> builder.append('.'));
            builder.replace(num, num + 1, "Q");
            board.add(builder.toString());
        }
        return board;
    }


    //[52] n 皇后
    private int count;

    public int totalNQueens(int n) {
        HashSet<Integer> row = new HashSet<>();
        HashSet<Integer> left = new HashSet<>();
        HashSet<Integer> right = new HashSet<>();
        count = 0;
        backTrack(0, n, row, left, right);
        return count;
    }

    private void backTrack(int row, int n,
                           HashSet<Integer> col,
                           HashSet<Integer> left,
                           HashSet<Integer> right) {
        if (row == n) count++;
        for (int i = 0; i < n; i++) {
            if (!col.contains(i) && !left.contains(i + row) && !right.contains(i - row)) {
                col.add(i);
                left.add(i + row);
                right.add(i - row);
                backTrack(row + 1, n, col, left, right);
                col.remove(i);
                left.remove(i + row);
                right.remove(i - row);
            }
        }
    }

    //1266. Minimum Time Visiting All Points
    public int minTimeToVisitAllPoints(int[][] points) {
        int result = 0;
        int[] firstPoint = points[0];
        for (int i = 1; i < points.length; i++) {
            result += Math.max(Math.abs(points[i][0] - firstPoint[0]), Math.abs(points[i][1] - firstPoint[1]));
            firstPoint = points[i];
        }
        return result;
    }

    //1282
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            if (map.containsKey(groupSizes[i])) map.get(groupSizes[i]).add(i);
            else {
                ArrayList<Integer> tmp = new ArrayList<>();
                tmp.add(i);
                map.put(groupSizes[i], tmp);
            }
        }
        List<List<Integer>> resut = new ArrayList<>();
        map.forEach((key, list) -> {
            int mut = list.size() / key;
            for (int i = 0; i < mut; i++) {
                resut.add(list.subList(key * i, key * (i + 1)));
            }
        });

        return resut;
    }


    //1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix
    public int minFlips(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int[][] zeroMat = new int[row][col];
        String finalStr = matrixToString(zeroMat);
        HashMap<String, Integer> fixIndex = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Queue<int[][]> matQueue = new LinkedList<>();
        matQueue.add(mat);
        fixIndex.put(matrixToString(mat), 0);
        while (!matQueue.isEmpty()) {
            int[][] m = matQueue.poll();
            String mstr = matrixToString(m);
            int v = fixIndex.get(mstr);
            visited.add(mstr);
            if (mstr.equals(finalStr)) return v;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    int[][] tmp = flip(m, i, j);
                    String tmpStr = matrixToString(tmp);
                    if (tmpStr.equals(finalStr)) return v + 1;
                    if (!visited.contains(tmpStr)) {
                        fixIndex.put(tmpStr, v + 1);
                        matQueue.add(tmp);
                    }
                }
            }
        }
        return -1;


    }


    private String matrixToString(int[][] mat) {
        String tm = "";
        for (int[] m : mat) {
            tm += Arrays.toString(m);
        }
        return tm;
    }


    private int[][] flip(int[][] mat, int row, int col) {
        int[][] flipMat = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                flipMat[i][j] = mat[i][j];
            }
        }
        flipMat[row][col] = 1 - flipMat[row][col];
        if (row > 0)
            flipMat[row - 1][col] = 1 - flipMat[row - 1][col];
        if (row < mat.length - 1) flipMat[row + 1][col] = 1 - flipMat[row + 1][col];
        if (col > 0)
            flipMat[row][col - 1] = 1 - flipMat[row][col - 1];
        if (col < mat[row].length - 1) flipMat[row][col + 1] = 1 - flipMat[row][col + 1];
        return flipMat;
    }

    //1277. Count Square Submatrices with All Ones
    public int countSquares(int[][] matrix) {
        int result=0;
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[i].length;j++)
                result += countSquaresWithIndex(matrix,i,j);
        }
        return  result;
    }

    public  int countSquaresWithIndex(int[][] matrix, int row, int col){
        int result=0;
        for(int i=0;i<Math.min(matrix.length-row,matrix[row].length-col);i++){
            if(isSquare(matrix,row,col,i))result++;
            else  return  result;
        }
        return  result;
    }

    private  boolean isSquare(int[][] matrix,int row,int col,int count){
        for(int i=0;i<=count;i++) {
            if (matrix[row + count][col + i] == 0) return false;
            if (matrix[row + i][col + count] == 0) return false;
        }
        return  true;
    }



}
