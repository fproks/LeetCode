package LeetCode.easy;

import java.util.*;
import java.util.stream.Stream;

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

    //575. Distribute Candies
    public int distributeCandies(int[] candies) {
        HashSet<Integer> set = new HashSet<>();
        for (int c : candies) {
            set.add(c);
        }
        if (set.size() > candies.length / 2) {
            return candies.length / 2;
        } else {
            return set.size();
        }

    }

    // 581. Shortest Unsorted Continuous Subarray
    public int findUnsortedSubarray(int[] nums) {
        int[] tmp = Arrays.copyOf(nums, nums.length);
        Arrays.sort(tmp);
        int start = 0;
        while (start < nums.length && nums[start] == tmp[start]) {
            start++;
        }
        int end = nums.length - 1;
        while (end > start && nums[end] == tmp[end]) {
            end--;
        }
        return end - start + 1;
    }

    //594. Longest Harmonious Subsequence
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int start = 0, end = 0;
        int size = 0;
        while (end < nums.length) {
            if (nums[start] == nums[end]) {
                end++;
            } else {
                if (nums[end] - nums[start] == 1) {
                    size = size > end - start + 1 ? size : end - start + 1;
                    end++;
                } else {
                    start++;
                }
            }
        }
        return size;
    }

    //598. Range Addition II
    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length <= 0) return m * n;
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        for (int[] tmp : ops) {
            a = a < tmp[0] ? a : tmp[0];
            b = b < tmp[1] ? b : tmp[1];
        }

        return a * b > m * n ? m * n : a * b;
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
                int tmp = map.get(list2[i]) + i;
                if (count > tmp) {
                    list.clear();
                    count = tmp;
                }
                if (count == tmp) {
                    list.add(list2[i]);
                }
            }
        }
        return list.toArray(new String[list.size()]);
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int i = 0;
        while (i < flowerbed.length && n > 0) {
            if (flowerbed[i] == 0) {
                int next = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
                int prev = (i == 0) ? 0 : flowerbed[i - 1];
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
        int size = nums.length - 1;
        int max1 = nums[size], max2 = nums[size - 1], max3 = nums[size - 2];
        int tmp = maxf * maxsf * max1;
        int tmp1 = max1 * max2 * max3;
        return tmp > tmp1 ? tmp : tmp1;
    }

    //643. Maximum Average Subarray I
    public double findMaxAverage(int[] nums, int k) {
        int count = 0;
        double maxcount = 0.0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            if (i == k - 1) maxcount = count;
            else if (i >= k) {
                count -= nums[i - k];
                maxcount = maxcount > count ? maxcount : count;
            }
        }
        return maxcount / k;
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
        int m = M.length - 1 < x + 1 ? M.length - 1 : x + 1;
        int n = M[0].length - 1 < y + 1 ? M[0].length - 1 : y + 1;
        int i = 0 > x - 1 ? 0 : x - 1;
        int j = 0 > y - 1 ? 0 : y - 1;
        int sum = 0, cnt = 0;
        for (; i <= m; i++)
            for (; j <= n; j++) {
                sum += M[i][j];
                cnt++;
            }
        return sum / cnt;
    }

    //665. Non-decreasing Array

    /**
     * http://blog.csdn.net/u010455714/article/details/77621972
     * 对于给定的数组 a1,a2,a3,a4,a5,…
     * 假设a4 < a3. 为了实现数组的单调非减，我们必须改变a4和a3其中的一个值，
     * 与此同时，为了后续计算的需要，我们应当尽可能使a4相对较小。
     * 此时，究竟是改变a3,还是a4取决于a2值的大小。
     * 我们假设a4 < a3， 那么a3 >= a2。
     * 因此，如果a4 < a2, 那么我们将改变a4, 最好的选择就是令 a4 = a3;
     * 否则的话，我们令a3 = a4， 但是这种情况我们并不需要考虑。
     */
    public boolean checkPossibility(int[] nums) {
        if (nums.length <= 2) return true;
        boolean modifed = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                if (modifed) return false;
                if (i - 2 >= 0 && nums[i] < nums[i - 2]) nums[i] = nums[i - 1];
                modifed = true;
            }
        }
        return true;
    }

    //696. Count Binary Substrings
    public int countBinarySubstrings(String s) {
        int len = s.length();
        if (len <= 1) return 0;
        char[] sc = s.toCharArray();
        int i = 0, prev = -1, res = 0;
        while (i < len) {
            int j = i;
            char c = sc[i];
            //统计相同元素的个数
            while (i < len && sc[i] == c) i++;
            int cur = i - j;
            //对相邻连续子串的较小值进行求和。这里使用两个变量来代替之前的一个数组，而且再一次遍历中执行计数和求和两部分功能
            if (prev != -1) res += Math.min(prev, cur);
            prev = cur;
        }
        return res;
    }

    //746. Min Cost Climbing Stairs
    public int minCostClimbingStairs(int[] cost) {
        int coast_1 = 0;
        int count_2 = 0;
        int total = 0;
        for (int i = 2; i <= cost.length; i++) {
            total = Math.min(cost[i - 1] + coast_1, cost[i - 2] + count_2);
            count_2 = coast_1;
            coast_1 = total;
        }
        return total;
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int min = Integer.MAX_VALUE;
        char s = letters[0];
        int size = 0;
        for (char c : letters) {
            if (c <= target) {
                size = ('z' - target) + (c - 'a') + 1;
            } else {
                size = c - target;
            }
            if (size < min) {
                s = c;
                min = size;
            }
        }
        return s;
    }


    //268. Missing Number
    // 0 ^ missing num = mission num
    public int missingNumber(int[] nums) {

        int res = nums.length;
        for (int i = nums.length - 1; i >= 0; i--) {
            res = res ^ i;
            res = res ^ nums[i];
        }
        return res;
    }


    public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> set = new HashSet<>();
        String res = "";
        for (String s : words) {
            if (s.length() == 1 || set.contains(s.substring(0, s.length() - 1))) {
                res = s.length() > res.length() ? s : res;
                set.add(s);
            }
        }
        return res;
    }


    public int dominantIndex(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sMax = Integer.MIN_VALUE;
        int idx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (max <= nums[i]) {
                sMax = max;
                idx = i;
                max = nums[i];
            } else if (sMax < nums[i]) {
                sMax = nums[i];
            }
        }
        if (sMax * 2 <= max) return idx;
        else return -1;
    }


    public int maxDistToClosest(int[] seats) {
        int tmplength = 0, reslength = 0;
        for (int i = 0; i <= seats.length - 1; i++) {
            if (seats[i] == 0) {
                tmplength++;
            } else {
                if (tmplength > reslength)
                    reslength = tmplength;
                tmplength = 0;
            }
        }
        int starti = 0, endi = 0;
        if (seats[0] == 0) {
            while (seats[starti] != 1) {
                starti++;
            }
        }
        if (seats[seats.length - 1] == 0) {
            endi += tmplength;
        }
        if (reslength % 2 == 0) {
            reslength = reslength / 2;
        } else reslength = reslength / 2 + 1;
        return reslength > endi ? Math.max(reslength, starti) : Math.max(endi, starti);
    }


    //832. Flipping an Image
    public int[][] flipAndInvertImage(int[][] A) {
        int rowLength = A[0].length;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0, z = rowLength - 1; j <= z; j++, z--) {
                int first = A[i][j], second = A[i][z];
                A[i][j] = second > 0 ? 0 : 1;
                A[i][z] = first > 0 ? 0 : 1;
            }
        }
        return A;
    }


    public int projectionArea(int[][] grid) {
        int Xsum = 0;
        int Ysum = 0;
        int Zsum = 0;
        for (int i = 0; i < grid.length; i++) {
            int jMax = 0;
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    Xsum++;
                }
                if (grid[i][j] > jMax) jMax = grid[i][j];
            }
            Ysum += jMax;
        }
        for (int i = 0; i < grid[0].length; i++) {
            int zMax = 0;
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] > zMax) zMax = grid[j][i];
            }
            Zsum += zMax;
        }
        return Xsum + Ysum + Zsum;
    }

    public int Compress(char[] chars) {
        int idx = 0;
        int count = 0;
        char c = chars[0];
        for (int i = 0; i <= chars.length; i++) {
            if (i == chars.length || c != chars[i]) {
                chars[idx++] = c;
                if (count != 1) {
                    char[] num = String.valueOf(count).toCharArray();
                    for (char tmp : num) {
                        chars[idx++] = tmp;
                    }
                }
                if (i < chars.length) {
                    count = 1;
                    c = chars[i];
                }
            } else {
                count++;
            }
        }
        return idx;
    }

    //754
    public int reachNumber(int target) {
        target = Math.abs(target);
        int step = 0, sum = 0;
        while (true) {
            step++;
            sum += step;
            if (sum == target) return step;
            if (sum > target && (sum - target) % 2 == 0) return step;
        }
    }

    public boolean hasGroupsSizeX(int[] deck) {
        if (deck.length % 2 != 0) return false;
        Arrays.sort(deck);
        int num = deck[0];
        int count = 1;
        for (int i = 1; i < deck.length; i++) {
            if (deck[i] == num) count++;
            else {
                if (count % 2 == 1) return false;
                count = 1;
                num = deck[i];
            }
        }
        if (count % 2 == 1) return false;
        return true;
    }

    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; i--) {
            if (A[i] < A[i - 1] + A[i - 2]) {
                return A[i] + A[i - 1] + A[i - 2];
            }
        }
        return 0;
    }

    public int[] fairCandySwap(int[] A, int[] B) {
        int result_a = 0;
        int result_b = 0;
        for (int x : A) result_a += x;
        for (int x : B) result_b += x;
        int delta = (result_a - result_b) / 2;
        Set<Integer> SetA = new HashSet<>();
        for (int x : A) SetA.add(x);
        for (int x : B)
            if (SetA.contains(x + delta))
                return new int[]{x + delta, x};
        return null;

    }

    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int[] result = new int[queries.length];
        int sumD = 0;
        for (int a : A) {
            if (a % 2 == 0) sumD += a;
        }
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            int tmp = A[q[1]];
            A[q[1]] += q[0];
            if (tmp % 2 == 0) {
                if (A[q[1]] % 2 != 0) sumD -= tmp;
                else sumD += q[0];
            } else {
                if (A[q[1]] % 2 == 0) sumD += A[q[1]];
            }
            result[i] = sumD;
        }
        return result;
    }

    public int[] sortedSquares(int[] A) {
        for (int i = 0; i < A.length; ++i) {
            A[i] = A[i] * A[i];
        }
        Arrays.sort(A);
        return A;
    }

    public int numRookCaptures(char[][] board) {
        int i = 0, j = 0;
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                if (board[k][l] == 'R') {
                    i = k;
                    j = l;
                }
            }
        }
        int t = i;
        int count = 0;
        while (t >= 0) {
            if (board[t][j] == 'p') {
                count++;
                break;
            }
            if (board[t][j] == 'B') {
                break;
            }
            t--;
        }
        t = i;
        while (t < 8) {
            if (board[t][j] == 'p') {
                count++;
                break;
            }
            if (board[t][j] == 'B') {
                break;
            }
            t++;
        }
        t = j;
        while (t < 8) {
            if (board[i][t] == 'p') {
                count++;
                break;
            }
            if (board[i][t] == 'B') {
                break;
            }
            t++;
        }
        t = j;
        while (t >= 0) {
            if (board[i][t] == 'p') {
                count++;
                break;
            }
            if (board[i][t] == 'B') {
                break;
            }
            t--;
        }
        return count;

    }

    //1030. Matrix Cells in Distance Order
    //给定数组的大小和起始位置，根据数组中各个位置的曼哈顿距离对位置进行排序
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        boolean[][] cell = new boolean[R][C];
        int[][] result = new int[R * C][2];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r0, c0});
        int idx = 0;
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            if (cell[tmp[0]][tmp[1]]) continue;
            result[idx++] = tmp;
            cell[tmp[0]][tmp[1]] = true;
            if (tmp[0] + 1 < R && !cell[tmp[0] + 1][tmp[1]]) queue.offer(new int[]{tmp[0] + 1, tmp[1]});
            if (tmp[0] - 1 >= 0 && !cell[tmp[0] - 1][tmp[1]]) queue.offer(new int[]{tmp[0] - 1, tmp[1]});
            if (tmp[1] + 1 < C && !cell[tmp[0]][tmp[1] + 1]) queue.offer(new int[]{tmp[0], tmp[1] + 1});
            if (tmp[1] - 1 >= 0 && !cell[tmp[0]][tmp[1] - 1]) queue.offer(new int[]{tmp[0], tmp[1] - 1});

        }
        return result;
    }

    public List<String> commonChars(String[] A) {
        int[] value = new int[26];
        for (char c : A[0].toCharArray()) {
            value[c - 'a']++;
        }
        int[] tmp = new int[26];
        for (int i = 1; i < A.length; i++) {
            Arrays.fill(tmp, 0);
            for (char c : A[i].toCharArray()) {
                tmp[c - 'a']++;
            }
            for (int j = 0; j < 26; j++) {
                value[j] = value[j] <= tmp[j] ? value[j] : tmp[j];
            }
        }
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            while (value[i] != 0) {
                list.add(Character.toString((char) ('a' + i)));
                value[i]--;
            }
        }
        return list;
    }

    public int findJudge(int N, int[][] trust) {
        if (trust.length < N - 1) return -1;
        HashSet<Integer> set = new HashSet<>();
        int[] p = new int[N];
        for (int[] c : trust) {
            set.add(c[0]);
            p[c[1] - 1]++;
        }
        for (int i = 0; i < N; i++) {
            if (p[i] == N - 1 && !set.contains(i + 1)) return i + 1;
        }
        return -1;
    }

    //1029. Two City Scheduling
    //动态规划， 假如取 5个a,那么它应该等于上一次取4个a 加本次取一个a 和 上次取5个a，本次取一个b的最小值
    public int twoCitySchedCost(int[][] costs) {
        int N = costs.length + 1;
        int[][] dp = new int[costs.length + 1][costs.length + 1];
        dp[0][0] = 0;
        dp[1][0] = costs[0][0];
        dp[1][1] = costs[0][1];

        for (int i = 2; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) dp[i][j] = dp[i - 1][j] + costs[i - 1][0];
                else if (j == i) dp[i][j] = dp[i - 1][j - 1] + costs[i - 1][1];
                else
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + costs[i - 1][1], dp[i - 1][j] + costs[i - 1][0]);
            }
        }

        return dp[N - 1][(N - 1) / 2];
    }

    public boolean lemonadeChange(int[] bills) {
        int buil5 = 0;
        int buil10 = 0;
        for (int bill : bills) {
            if (bill == 5) buil5++;
            if (bill == 10)
                if (buil5 > 0) {
                    buil5--;
                    buil10++;
                } else return false;
            if (bill == 20) {
                if (buil10 > 0) {
                    if (buil5 > 0) {
                        buil10--;
                        buil5--;
                    } else return false;
                } else if ((buil5 -= 3) < 0) return false;
            }
        }
        return true;
    }

    public int largestSumAfterKNegations(int[] A, int K) {
        Arrays.sort(A);
        int tracker = 0;
        int sum = 0;
        for (int i = 0; i < K; i++) {
            A[tracker] = -A[tracker];
            if (tracker < (A.length - 1) && A[tracker] > A[tracker + 1])
                tracker++;
        }
        for (int a : A) sum += a;
        return sum;
    }

    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> result = new LinkedList<>();
        int jin = 0;
        int idx = A.length - 1;
        while (K > 0 || jin > 0 || idx >= 0) {
            int sum = 0;
            if (idx >= 0) sum += A[idx--];
            if (K > 0) {
                sum += K % 10;
                K = K / 10;
            }
            sum += jin;
            result.add(0, sum % 10);
            jin = sum / 10;

        }

        return result;
    }

    public int heightChecker(int[] heights) {
        int[] tmp = Arrays.copyOf(heights, heights.length);
        Arrays.sort(tmp);
        int count = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != tmp[i]) count++;
        }
        return count;
    }

    private int findNextNumber(int x, ArrayList<Integer> map, boolean dirs) {
        if (dirs) {
            for (int i : map) {
                if (i > x) return i;
            }
            return Integer.MAX_VALUE;
        } else {
            int j = Integer.MIN_VALUE;
            for (int i : map) {
                if (i > j && i < x) j = i;
                else break;
            }
            return j;
        }
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        int x = 0, y = 0;
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dir = 0;
        int maxDistance = 0;
        HashMap<Integer, ArrayList<Integer>> xmap = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> ymap = new HashMap<>();
        for (int[] pos : obstacles) {
            if (!xmap.containsKey(pos[0])) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                arrayList.add(pos[1]);
                xmap.put(pos[0], arrayList);
            } else {
                xmap.get(pos[0]).add(pos[1]);
            }
            if (!ymap.containsKey(pos[1])) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                arrayList.add(pos[0]);
                ymap.put(pos[1], arrayList);
            } else {
                ymap.get(pos[1]).add(pos[0]);
            }
        }
        xmap.values().forEach(list -> list.sort(Integer::compareTo));
        ymap.values().forEach(list -> list.sort(Integer::compareTo));
        for (int i : commands) {
            if (i == -1) dir = (dir + 1) % 4;
            else if (i == -2) dir = (dir + 4 - 1) % 4;
            else {
                int[] ops = dirs[dir];
                if (ops[0] == -1) {
                    if (ymap.containsKey(y)) {
                        int next = findNextNumber(x, ymap.get(y), false);
                        x = x - i > next + 1 ? x - i : next + 1;
                    } else x = x - i;
                }
                if (ops[0] == 1) {
                    if (ymap.containsKey(y)) {
                        int next = findNextNumber(x, ymap.get(y), true);
                        x = x + i > next - 1 ? next - 1 : x + i;
                    } else x = x + i;
                }
                if (ops[1] == -1) {
                    if (xmap.containsKey(x)) {
                        int next = findNextNumber(y, xmap.get(x), false);
                        y = y - i > next + 1 ? y - i : next + 1;
                    } else y = y - i;
                }
                if (ops[1] == 1) {
                    if (xmap.containsKey(x)) {
                        int next = findNextNumber(y, xmap.get(x), true);
                        y = y + i > next - 1 ? next - 1 : y + i;
                    } else y = y + i;
                }
                maxDistance = Math.max(maxDistance, x * x + y * y);
            }
        }
        return maxDistance;
    }
    public int game(int[] guess, int[] answer) {
        int res=0;
        for(int i=0; i<guess.length;i++){
            if(guess[i]==answer[i])res++;
        }
        return res;
    }

    //1295. Find Numbers with Even Number of Digits
    public int findNumbers(int[] nums) {
        int result=0;
        for (int n : nums){
            if(isDigitsNumber(n))result++;
        }
        return  result;
    }

    private  boolean isDigitsNumber(int num){
        while (num>99){
            num=num/100;
        }
        return  num >=10;
    }

}
