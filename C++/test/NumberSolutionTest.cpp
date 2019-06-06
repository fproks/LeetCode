//
// Created by ezlinho on 5/28/2019.
//

#include "../solution/NumberSolution.h"
#include <assert.h>
NumberSolution *numberSolution=new NumberSolution();
void isBoomerangTest(){
   vector<vector<int>> test1({{1,1},{2,3},{3,2}});
   vector<vector<int>> test2({{1,1},{2,2},{3,3}});
   assert(numberSolution->isBoomerang(test1));
   assert(!numberSolution->isBoomerang(test2));
}
