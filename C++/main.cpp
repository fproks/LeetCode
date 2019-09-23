#include <iostream>
#include "ArraySolution.h"
#include "test/ArraySolutionTest.cpp"
#include "test/StringSolutionTest.cpp"
#include "test/NumberSolutionTest.cpp"
#include "solution/MyTuple.h"
#include <set>
#include <tuple>


int main() {
    std::cout << "Hello, World!" << std::endl;
    //searchTest();
    // gardenNoAdjTest();
    //orangesRottingTest();
    //prefixesDivBy5Test();
    //numPairsDivisibleBy60Test();
    // isLongPressedNameTest();
    //isBoomerangTest();
    //isRobotBounded();
    //pivotIndexTest();
    //repeatedStringMatch();
    //buddyStrings();
    //largestValsFromLabelsTest();
    /*   vector<int> *a =new vector<int>();
       a->push_back(1);
       a->push_back(5);
       std::cout<< a->at(1)<<std::endl;
       std::cout<<"-------------"<<std::endl;*/
       auto sets = std::set<MyTuple>();
       auto tuple1 = MyTuple(1, 2, 10);
       auto tuple2 = MyTuple(2, 1, 9);
       auto tuple3 = MyTuple(10, 7, 6);
       auto tuple4 = MyTuple(9, 9, 8);
       auto tuple5 = MyTuple(15, 14, 4);
       sets.insert(tuple1);
       sets.insert(tuple2);
       sets.insert(tuple3);
       sets.insert(tuple4);
       sets.insert(tuple5);
       for_each(sets.begin(),sets.end(),[](MyTuple t){
           std::cout << t.get0()<<"------"<<t.get1()<<"-->"<<t.getvalue()<<std::endl;
       });



    return 0;
}