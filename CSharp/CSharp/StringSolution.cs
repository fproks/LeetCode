using System;
using System.Collections.Generic;
using System.Linq;

namespace CSharp
{
    public class StringSolution
    {
        public int[] ShortestToChar(string S, char C)
        {
            var idx = new int[S.Length];
            var pos = -S.Length;
            for (var i = 0; i < S.Length; i++)
            {
                if (S[i] == C) pos = i;
                idx[i] = i - pos;
            }

            for (var i = S.Length - 1; i >= 0; i--)
            {
                if (S[i] == C) pos = i;
                idx[i] = Math.Min(idx[i], Math.Abs(i - pos));
            }

            return idx;
        }
    }
}