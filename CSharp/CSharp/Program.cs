using System;
using System.Collections.Generic;
using System.Linq;

namespace CSharp {
    class Program {
        static void Main(string[] args) {
            StringSolution solution = new StringSolution();
            string[] fi = new[] {"hello", "leetcode"};
            string order = "hlabcdefgijkmnopqrstuvwxyz";
            Console.Write(solution.IsAlienSorted(fi, order));

            Console.ReadKey();
        }
    }
}