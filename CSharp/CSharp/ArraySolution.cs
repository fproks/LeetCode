using System;
using System.Collections;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net.Sockets;
using System.Runtime.InteropServices;

namespace CSharp {
    public class ArraySolution {
        public bool IsMonotonic(int[] A) {
            if (A.Length <= 1) return true;
            bool desc = false;
            bool asc = false;
            int i = 0, j = 1;
            while (j < A.Length) {
                if (A[i] < A[j]) asc = true;
                if (A[i] > A[j]) desc = true;
                j++;
                i++;
            }

            return !(desc && asc);
        }

        public IList<int> GetRow(int rowIndex) {
            IList<int> result = new int[rowIndex + 1];
            result[0] = 1;
            for (int i = 1; i <= rowIndex; i++) {
                result[i] = (int) ((long) result[i - 1] * (rowIndex - (i - 1)) / i);
            }

            return result;
        }

        public int NumMagicSquaresInside(int[][] grid) {
            int count = 0;
            for (int i = 0; i <= grid.Length - 3; i++) {
                for (int j = 0; j <= grid[i].Length - 3; j++) {
                    if (MagicSquare(grid, i, j)) count++;
                }
            }

            return count;
        }

        private bool MagicSquare(int[][] grid, int i, int j) {
            if (grid[i].Length < j + 3 || grid[i + 1].Length < j + 3 || grid[i + 2].Length < j + 3) return false;
            for (int k = i; k < i + 3; k++) {
                for (int l = j; l < j + 3; l++) {
                    if (grid[k][l] > 9 || grid[k][l] <= 0) return false;
                }
            }

            int sum = 0;
            sum = grid[i][j] + grid[i + 1][j] + grid[i + 2][j];
            if (grid[i][j + 1] + grid[i + 1][j + 1] + grid[i + 2][j + 1] != sum) return false;
            if (grid[i][j + 2] + grid[i + 1][j + 2] + grid[i + 2][j + 2] != sum) return false;
            if (grid[i][j] + grid[i][j + 1] + grid[i][j + 2] != sum) return false;
            if (grid[i + 1][j] + grid[i + 1][j + 1] + grid[i + 1][j + 2] != sum) return false;
            if (grid[i + 2][j] + grid[i + 2][j + 1] + grid[i + 2][j + 2] != sum) return false;
            if (grid[i][j] + grid[i + 1][j + 1] + grid[i + 2][j + 2] != sum) return false;
            if (grid[i + 2][j] + grid[i + 1][j + 1] + grid[i][j + 2] != sum) return false;
            return true;
        }

        public int NetworkDelayTime(int[,] times, int N, int K) {
            int k = K - 1;
            int[,] costs = new int[N, N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++)
                    costs[i, j] = Int32.MaxValue;
            }


            for (int i = 0; i < times.Length / 3; i++) {
                int x = times[i, 0] - 1, y = times[i, 1] - 1, value = times[i, 2];
                costs[x, y] = Math.Min(costs[x, y], value);
            }

            bool flag = true;
            while (flag) {
                flag = false;

                for (int y = 0; y < N; y++) {
                    if (costs[k, y] != Int32.MaxValue) {
                        for (int i = 0; i < N; i++) {
                            if (costs[y, i] != Int32.MaxValue) {
                                int v = costs[k, i];
                                costs[k, i] = Math.Min(v, costs[k, y] + costs[y, i]);
                                if (costs[k, i] != v)
                                    flag = true;
                            }
                        }
                    }
                }
            }

            int count = 0;
            for (int i = 0; i < N; i++) {
                costs[k, k] = 0;
                if (costs[k, i] != Int32.MaxValue) count = Math.Max(count, costs[k, i]);
                else return -1;
            }

            return count;
        }


        public bool IsOneBitCharacter(int[] bits) {
            var flag = false;
            var i = 0;
            while (i < bits.Length) {
                if (bits[i] == 1) {
                    flag = false;
                    i += 2;
                }
                else {
                    flag = true;
                    i++;
                }
            }

            return flag;
        }

        //1207. Unique Number of Occurrences
        public bool UniqueOccurrences(int[] arr) {
            Dictionary<int, int> dict = new Dictionary<int, int>();
            foreach (var item in arr) {
                if (dict.ContainsKey(item)) dict[item]++;
                else dict.Add(item, 1);
            }

            return dict.Values.Count == new HashSet<int>(dict.Values).Count;
        }

        //1219. Path with Maximum Gold
        public int GetMaximumGold(int[][] grid) {
            bool[][] visited = new bool[grid.Length][];
            for (int i = 0; i < grid.Length; i++) {
                visited[i] = new bool[grid[i].Length];
                for (int j = 0; j < grid[i].Length; j++) {
                    visited[i][j] = false;
                }
            }

            int max = 0;
            for (int i = 0; i < grid.Length; i++) {
                for (int j = 0; j < grid[i].Length; j++) {
                    max = Math.Max(max, countGold(grid, i, j, visited));
                }
            }

            return max;
        }

        private int countGold(int[][] grid, int startX, int startY, bool[][] visited) {
            var n = grid.Length;
            var m = grid[0].Length;
            if (startX < 0 || startX >= n || startY < 0 || startY >= m || grid[startX][startY] == 0 ||
                visited[startX][startY]) return 0;
            var count = grid[startX][startY];
            visited[startX][startY] = true;
            var tmpCount = 0;
            tmpCount = Math.Max(tmpCount, countGold(grid, startX - 1, startY, visited));
            tmpCount = Math.Max(tmpCount, countGold(grid, startX + 1, startY, visited));
            tmpCount = Math.Max(tmpCount, countGold(grid, startX, startY - 1, visited));
            tmpCount = Math.Max(tmpCount, countGold(grid, startX, startY + 1, visited));
            count += tmpCount;
            visited[startX][startY] = false;
            return count;
        }
    }


//933. Number of Recent Calls
    public class RecentCounter {
        private LinkedList<int> _arrayList;

        public RecentCounter() {
            this._arrayList = new LinkedList<int>();
        }

        public int Ping(int t) {
            _arrayList.AddLast(t);
            while (_arrayList.First.Value < t - 3000) {
                _arrayList.RemoveFirst();
            }

            return _arrayList.Count;
        }
    }
}