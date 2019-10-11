using System;
using System.Collections.Generic;
using System.Net.Http.Headers;
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

    //1195. Fizz Buzz Multithreaded
    public class FizzBuzz {
        private int n;
        private int i;
        bool d3;
        bool d5;
        private Dictionary<int, int> dict;

        public FizzBuzz(int n) {
            this.n = n;
            i = 1;
            dict = new Dictionary<int, int> {
                {i, 0}
            };
        }

        public void Fizz(Action printFizz) {
            while (i <= n) {
                if (dict[i] == 1) {
                    if (d3 && !d5) {
                        printFizz();
                    }

                    dict[i]++;
                }

                System.Threading.Thread.Sleep(1);
            }
        }

        public void Buzz(Action printBuzz) {
            while (i <= n) {
                if (dict[i] == 2) {
                    if (!d3 && d5) {
                        printBuzz();
                    }

                    dict[i]++;
                }

                System.Threading.Thread.Sleep(1);
            }
        }

        public void Fizzbuzz(Action printFizzBuzz) {
            while (i <= n) {
                if (dict[i] == 3) {
                    if (d3 && d5) {
                        printFizzBuzz();
                    }

                    dict.Add(i + 1, 0);
                    i++;
                }

                System.Threading.Thread.Sleep(1);
            }
        }

        public void Number(Action<int> printNumber) {
            while (i <= n) {
                if (dict[i] == 0) {
                    d3 = (i % 3 == 0);
                    d5 = (i % 5 == 0);
                    if (!d3 && !d5) {
                        printNumber(i);
                    }

                    dict[i]++;
                }

                System.Threading.Thread.Sleep(1);
            }
        }
    }

    //1114. Print in Order
    public class Foo {
        private int count = 1;
        public Foo() { }

        public void First(Action printFirst) {
            while (true) {
                if (count % 3 == 1) {
                    // printFirst() outputs "first". Do not change or remove this line.
                    printFirst();
                    count++;
                    break;
                }

                Thread.Sleep(1);
            }
        }

        public void Second(Action printSecond) {
            while (true) {
                if (count % 3 == 2) {
                    // printSecond() outputs "second". Do not change or remove this line.
                    printSecond();
                    count++;
                    break;
                }

                Thread.Sleep(1);
            }
        }

        public void Third(Action printThird) {
            while (true) {
                if (count % 3 == 0) {
                    // printThird() outputs "third". Do not change or remove this line.
                    printThird();
                    count = 1;
                    break;
                }

                Thread.Sleep(1);
            }
        }
    }
}