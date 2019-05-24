//
// Created by ezlinho on 5/24/2019.
//
#include "ArraySolution.h"
#include <assert.h>
ArraySolution *arraySolution =new ArraySolution();

void searchTest(){
    vector<int> a({-1,0,3,5,9,12});
    vector<int>b({-1,0,3,5,9,12});
    assert(arraySolution->search(a,9)==4);
    assert(arraySolution->search(b,2)==-1);
    std::cout<<"search "<<std::endl;
}

void gardenNoAdjTest(){
    vector<vector<int>> a({{1,2},{2,3},{3,1}});
    vector<vector<int>> b({{1,2},{2,3},{3,4},{4,1},{1,3},{2,4}});
    auto  result =arraySolution->gardenNoAdj(4,b);

    std::cout<<result.size()<<std::endl;
    for (auto i : result){
        std::cout<<i <<" ";
    }
    std::cout<<std::endl;
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
