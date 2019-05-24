//
// Created by ezlinho on 5/21/2019.
//

#ifndef C_ARRAYSOLUTION_H
#define C_ARRAYSOLUTION_H

#include <vector>
#include <string>

using namespace std;

class ArraySolution {
public:
    int largestSumAfterKNegations(vector<int> &A, int K);
    int lastStoneWeight(vector<int>& stones);
    vector<vector<int>> largeGroupPositions(string S);
    int search(vector<int>& nums, int target);
    vector<int> gardenNoAdj(int N, vector<vector<int>>& paths);
    int test(vector<int>& bit);

};


#endif //C_ARRAYSOLUTION_H
