//
// Created by ezlinho on 5/24/2019.
//
#include "../ArraySolution.h"
#include <assert.h>

ArraySolution *arraySolution = new ArraySolution();

void searchTest() {
    vector<int> a({-1, 0, 3, 5, 9, 12});
    vector<int> b({-1, 0, 3, 5, 9, 12});
    assert(arraySolution->search(a, 9) == 4);
    assert(arraySolution->search(b, 2) == -1);
    std::cout << "search " << std::endl;
}

void gardenNoAdjTest() {
    vector<vector<int>> a({{1, 2},
                           {2, 3},
                           {3, 1}});
    vector<vector<int>> b({{1, 2},
                           {2, 3},
                           {3, 4},
                           {4, 1},
                           {1, 3},
                           {2, 4}});
    auto result = arraySolution->gardenNoAdj(4, b);

    std::cout << result.size() << std::endl;
    for (auto i : result) {
        std::cout << i << " ";
    }
    std::cout << std::endl;
}

void orangesRottingTest() {
    vector<vector<int>> a({{2, 1, 1},
                           {1, 1, 0},
                           {0, 1, 1}});
    vector<vector<int>> b({{2, 1, 1},
                           {0, 1, 1},
                           {1, 0, 1}});
    vector<vector<int>> c({{0, 2}});
    assert(arraySolution->orangesRotting(a) == 4);
    assert(arraySolution->orangesRotting(b) == -1);
    assert(arraySolution->orangesRotting(c) == 0);
}

void prefixesDivBy5Test() {
    vector<int> a({0, 1, 1});
    vector<int> b({1, 1, 1});
    vector<int> c({0, 1, 1, 1, 1, 1});
    vector<int> d({1, 1, 1, 0, 1});
    for (auto i : arraySolution->prefixesDivBy5(a))
        std::cout << i << " ";
    std::cout << std::endl;
    for (auto i : arraySolution->prefixesDivBy5(b))
        std::cout << i << " ";
    std::cout << std::endl;
    for (auto i : arraySolution->prefixesDivBy5(c))
        std::cout << i << " ";
    std::cout << std::endl;
    for (auto i : arraySolution->prefixesDivBy5(d))
        std::cout << i << " ";
    std::cout << std::endl;
}

void KthLargestTest() {
    vector<int> a = {1, 1};
    KthLargest kthLargest(3, a);
    assert(kthLargest.add(1) == 1);
    assert(kthLargest.add(1) == 1);
    assert(kthLargest.add(3) == 1);
    assert(kthLargest.add(3) == 1);
    assert(kthLargest.add(3) == 3);
    assert(kthLargest.add(4) == 3);
    assert(kthLargest.add(4) == 3);
    assert(kthLargest.add(4) == 4);
}

void numPairsDivisibleBy60Test() {
    vector<int> a = {30, 20, 150, 100, 40};
    vector<int> b = {60, 60, 60};
    vector<int> c = {15, 63, 451, 213, 37, 209, 343, 319};
    assert(arraySolution->numPairsDivisibleBy60(a) == 3);
    assert(arraySolution->numPairsDivisibleBy60(b) == 3);
    assert(arraySolution->numPairsDivisibleBy60(c) == 1);
}

void pivotIndexTest() {
    vector<int> a = {1, 7, 3, 6, 5, 6};
    vector<int> b = {1, 2, 3};
    vector<int> c ={-1,-1,-1,-1,-1,0};
    vector<int> d ={-1,-1,-1,0,1,1};
   // assert(arraySolution->pivotIndex(a) == 3);
   // assert(arraySolution->pivotIndex(b) == -1);
    assert(arraySolution->pivotIndex(c)==2);
    assert(arraySolution->pivotIndex(d)==0);
}