using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.Immutable;
using System.Collections.Specialized;
using System.IO;
using System.Linq;
using System.Text;

namespace CSharp {
    public class StringSolution {
        public int[] ShortestToChar(string S, char C) {
            var idx = new int[S.Length];
            var pos = -S.Length;
            for (var i = 0; i < S.Length; i++) {
                if (S[i] == C) pos = i;
                idx[i] = i - pos;
            }

            for (var i = S.Length - 1; i >= 0; i--) {
                if (S[i] == C) pos = i;
                idx[i] = Math.Min(idx[i], Math.Abs(i - pos));
            }

            return idx;
        }

        public string[] UncommonFromSentences(string A, string B) {
            var arr = (A + " " + B).Split(' ');
            return arr.Where(i => arr.Count(j => j == i) == 1).ToArray();
        }

        public string ReverseOnlyLetters(string S) {
            var builder = new StringBuilder();
            var map = new SortedDictionary<int, char>();
            for (var i = 0; i < S.Length; i++) {
                if (char.IsLetter(S[i])) builder.Append(S[i]);
                else {
                    map.Add(i, S[i]);
                }
            }

            builder = new StringBuilder(new string(builder.ToString().Reverse().ToArray()));
            foreach (var kv in map) {
                builder.Insert(kv.Key, kv.Value);
            }

            return builder.ToString();
        }

        public string ToGoatLatin(string S) {
            var list = S.Split(' ');
            for (int i = 0; i < list.Length; i++) {
                var tmp = list[i];
                if (tmp.StartsWith("a") || tmp.StartsWith("A") || tmp.StartsWith("e") || tmp.StartsWith("E") ||
                    tmp.StartsWith("i") || tmp.StartsWith("I") || tmp.StartsWith("o") || tmp.StartsWith("O") ||
                    tmp.StartsWith("u") || tmp.StartsWith("U")) {
                    tmp = tmp + "ma";
                }
                else {
                    if (tmp.Length > 0)
                        tmp = tmp.Substring(1) + tmp[0] + "ma";
                }

                for (int j = 0; j <= i; j++) {
                    tmp += 'a';
                }

                list[i] = tmp;
            }

            return string.Join(" ", list).Trim();
        }

        public bool BackspaceCompare(string S, string T) {
            return getBackString(S).Equals(getBackString(T));
        }

        private string getBackString(string S) {
            Stack<char> stack = new Stack<char>();
            foreach (var c in S) {
                if (c != '#') stack.Push(c);
                else if (stack.Count > 0) stack.Pop();
            }

            string sre = "";
            foreach (var c in stack) {
                sre += c;
            }

            return sre;
        }

        public int[] DiStringMatch(string S) {
            int n = S.Length + 1;
            int[] result = new int[n];
            int first = S.Count(c => c == 'D');
            result[0] = first;
            int low = first;
            int high = first;
            for (int i = 1; i < n; i++) {
                if (S[i - 1] == 'I') {
                    high++;
                    result[i] = high;
                }
                else {
                    low--;
                    result[i] = low;
                }
            }

            return result;
        }

        public string[] ReorderLogFiles(string[] logs) {
            Array.Sort(logs, (log1, log2) => {
                var split1 = log1.Split(" ", 2);
                var split2 = log2.Split(" ", 2);
                bool isDig1 = char.IsDigit(split1[1][0]);
                bool isDig2 = char.IsDigit(split2[1][0]);
                if (!isDig1 && !isDig2) {
                    return string.Compare(split1[1], split2[1], StringComparison.Ordinal);
                }

                return isDig1 ? (isDig2 ? 0 : 1) : -1;
            });
            return logs;
            /*var orderedEnumerable = logs.Where(s => {
                var tmp = s.Substring(s.IndexOf(" ", StringComparison.Ordinal));
                return Char.IsDigit(tmp[0]);
            }).OrderBy((s1) => s1.Substring(s1.IndexOf(" ", StringComparison.Ordinal)));
            var number = logs.Where(s => {
                var tmp = s.Substring(s.IndexOf(" ", StringComparison.Ordinal));
                return Char.IsDigit(tmp[0]);
            });
           var list = orderedEnumerable.ToList();
           list.AddRange(number.ToList());
           return list.ToArray();*/
        }

        //953. Verifying an Alien Dictionary
        public bool IsAlienSorted(string[] words, string order) {
            Dictionary<char, int> dict = new Dictionary<char, int>();
            for (var i = 0; i < order.Length; i++) {
                dict[order[i]] = i;
            }

            for (int i = 0; i < words.Length - 1; i++) {
                string str1 = words[i];
                string str2 = words[i + 1];
                if (!isSort(str1, str2, dict)) return false;
            }

            return true;
        }

        private bool isSort(string str1, string str2, Dictionary<char, int> dict) {
            int len = str1.Length > str2.Length ? str2.Length : str1.Length;
            for (int j = 0; j < len; j++) {
                if (str1[j] == str2[j]) continue;
                return dict[str1[j]] <= dict[str2[j]];
            }

            return str1.Length <= str2.Length;
        }
    }
}