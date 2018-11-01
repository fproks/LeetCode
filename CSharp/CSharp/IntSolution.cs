using System;

namespace CSharp
{
    public class IntSolution
    {
        public int BinaryGap(int N)
        {
            var res = 0;
            for (var d = -32; N!=0; N /= 2, d++)
                if (N % 2 == 1)
                {
                    res = Math.Max(res, d);
                    d = 0;
                }
            return res;
        }
    }
}