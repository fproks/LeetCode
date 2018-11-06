using System;
using System.Collections;
using System.Collections.Generic;
using System.Globalization;

namespace CSharp{
    public class ArraySolution{
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
            

            for (int i = 0; i < times.Length/3; i++) {
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
                                if(costs[k,i]!=v)
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
    }
}