using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.Immutable;
using System.Collections.Specialized;
using System.Linq;
using System.Text;

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

        public string[] UncommonFromSentences(string A, string B)
        {
            var arr = (A + " " + B).Split(' ');
            return arr.Where(i => arr.Count(j => j == i) == 1).ToArray();
        }
        
        public string ReverseOnlyLetters(string S) {
            var builder =new StringBuilder();
            var map =new SortedDictionary<int, char>();
            for (var i =0;i<S.Length;i++ )
            {
                if (char.IsLetter(S[i])) builder.Append(S[i]);
                else
                {
                    map.Add(i,S[i]);
                }
            }
            builder =new StringBuilder(new string(builder.ToString().Reverse().ToArray()));
            foreach (var kv in map)
            {
                builder.Insert(kv.Key, kv.Value);
            }

            return builder.ToString();
        }
        
        public string ToGoatLatin(string S)
        {
            var list =S.Split(' ');
            for (int i = 0; i < list.Length; i++)
            {
                var tmp = list[i];
                if (tmp.StartsWith("a") || tmp.StartsWith("A") || tmp.StartsWith("e") || tmp.StartsWith("E") ||
                    tmp.StartsWith("i") || tmp.StartsWith("I") || tmp.StartsWith("o") || tmp.StartsWith("O") ||
                    tmp.StartsWith("u") || tmp.StartsWith("U"))
                {
                    tmp = tmp + "ma";
                }
                else
                {
                    if(tmp.Length>0)
                        tmp = tmp.Substring(1)+tmp[0]+"ma";
                }

                for (int j = 0; j <=i; j++)
                {
                    tmp += 'a';
                }

                list[i] = tmp;
            }

            return string.Join(" ", list).Trim();
        }
        public bool BackspaceCompare(string S, string T)
        {
            return getBackString(S).Equals(getBackString(T));
        }

        private string getBackString(string S)
        {
            Stack<char> stack =new Stack<char>();
            foreach (var c in S)
            {
                if (c != '#') stack.Push(c);
                else  if(stack.Count>0)stack.Pop();
            }

            string sre = "";
            foreach (var c in stack)
            {
                sre += c;
            }
            return sre;
        }
        
        
      
        
        
    }
}