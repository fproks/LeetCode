using System;
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
        
        public class MyHashMap {
            private readonly int[] _hashArray;

            /** Initialize your data structure here. */
            public MyHashMap() {
                this._hashArray =new int[10000001];
                for (int i = 0; i < this._hashArray.Length; i++) {
                    this._hashArray[i] = -1;
                }
            }
    
            /** value will always be non-negative. */
            public void Put(int key, int value) {
                _hashArray[key] = value;
            }
    
            /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
            public int Get(int key) {
               return _hashArray[key];
            }
    
            /** Removes the mapping of the specified value key if this map contains a mapping for the key */
            public void Remove(int key) {
                this._hashArray[key] = -1;
            }
        }
    }
}