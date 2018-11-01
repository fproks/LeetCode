using System;
using System.Collections.Generic;
using System.Linq;

namespace CSharp
{
    class Program
    {
        static string ReverseString(string s)
        {
            return new string(s.Reverse().ToArray());
        }

        static void Main(string[] args)
        {
            var list = new List<int>();

            for (int i = 1; i <= 100; i++)
            {
                list.Add(i);
            }

            var result = list.FindAll(i => i % 2 == 0);
            
            result.ForEach(Console.WriteLine);
           
        
        }
    }


   
}