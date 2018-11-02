using System.Collections.Generic;

namespace CSharp
{
    public class ClassSolution
    {
        public class MyHashSet
        {
            private LinkedList<int> set;

            public MyHashSet()
            {
                set = new LinkedList<int>();
            }

            public void Add(int key)
            {
                if (!set.Contains(key))
                    set.AddLast(key);
            }

            public void Remove(int key)
            {
                if (set.Contains(key))
                {
                    set.Remove(key);
                }
            }

            public bool Contains(int key)
            {
                return set.Contains(key);
            }
        }
    }
}