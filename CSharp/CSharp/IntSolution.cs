using System;

namespace CSharp
{
    public class IntSolution
    {
        public int BinaryGap(int N)
        {
            var res = 0;
            for (var d = -32; N != 0; N /= 2, d++)
                if (N % 2 == 1)
                {
                    res = Math.Max(res, d);
                    d = 0;
                }

            return res;
        }

        public int RotatedDigits(int N)
        {
            var count = 0;
            for (var i = 1; i <= N; i++)
            {
                if (isValid(i)) count++;
            }

            return count;
        }

        private bool isValid(int N)
        {
            bool validFound = false;
            while (N > 0)
            {
                switch (N % 10)
                {
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
    }
}