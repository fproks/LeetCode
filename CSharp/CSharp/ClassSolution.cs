using System;
using System.Collections.Generic;
using System.Threading;

namespace CSharp {
    public class ClassSolution {
        public class MyHashSet {
            private LinkedList<int> set;

            public MyHashSet() {
                set = new LinkedList<int>();
            }

            public void Add(int key) {
                if (!set.Contains(key))
                    set.AddLast(key);
            }

            public void Remove(int key) {
                if (set.Contains(key)) {
                    set.Remove(key);
                }
            }

            public bool Contains(int key) {
                return set.Contains(key);
            }
        }

        public class MyHashMap {
            private readonly int[] _hashArray;

            /** Initialize your data structure here. */
            public MyHashMap() {
                this._hashArray = new int[10000001];
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

    public class FooBar {
        private int n;
        private readonly AutoResetEvent _fooResetEvent = new AutoResetEvent(true);
        private readonly AutoResetEvent _barResetEvent = new AutoResetEvent(false);

        public FooBar(int n) {
            this.n = n;
        }

        public void Foo(Action printFoo) {
            for (int i = 0; i < n; i++) {
                _fooResetEvent.WaitOne();
                // printFoo() outputs "foo". Do not change or remove this line.
                printFoo();
                _barResetEvent.Set();
            }
        }

        public void Bar(Action printBar) {
            for (int i = 0; i < n; i++) {
                _barResetEvent.WaitOne();
                // printBar() outputs "bar". Do not change or remove this line.
                printBar();
                _fooResetEvent.Set();
            }
        }
    }
}