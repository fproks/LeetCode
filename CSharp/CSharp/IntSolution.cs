using System;

namespace CSharp {
    public class IntSolution {
        public int BinaryGap(int N) {
            var res = 0;
            for (var d = -32; N != 0; N /= 2, d++)
                if (N % 2 == 1) {
                    res = Math.Max(res, d);
                    d = 0;
                }

            return res;
        }

        public int RotatedDigits(int N) {
            var count = 0;
            for (var i = 1; i <= N; i++) {
                if (isValid(i)) count++;
            }

            return count;
        }

        private bool isValid(int N) {
            bool validFound = false;
            while (N > 0) {
                switch (N % 10) {
                    case 2:
                    case 5:
                    case 6:
                    case 9:
                        validFound = true;
                        break;
                    case 3:
                    case 4:
                    case 7:
                        return false;
                }

                N = N / 10;
            }

            return validFound;
        }


        //509. Fibonacci Number
        private int[] fib = new int[33];

        public int Fib(int N) {
            if (N == 0) return 0;
            fib[1] = 1;
            fib[2] = 1;
            if (fib[N] == 0) {
                for (int i = 3; i <= N; i++) {
                    fib[i] = fib[i - 2] + fib[i - 1];
                }
            }

            return fib[N];
        }
    }

    // 1137. N-th Tribonacci Number
    public class TribonacciSolution {
        private int[] tribon;

        public TribonacciSolution() {
            tribon = new int[38];
            for (int i = 0; i < 38; i++) {
                tribon[i] = -1;
            }

            tribon[0] = 0;
            tribon[1] = 1;
            tribon[2] = 1;
        }

        public int Tribonacci(int n) {
            if (tribon[n] != -1) return tribon[n];
            else {
                tribon[n] = Tribonacci(n - 1) + Tribonacci(n - 2) + Tribonacci(n - 3);
            }

            return tribon[n];
        }
    }
}