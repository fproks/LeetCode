//
// Created by ezlinho on 5/21/2019.
//

#ifndef C_ARRAYSOLUTION_H
#define C_ARRAYSOLUTION_H

#include <vector>
#include <string>
#include <algorithm>
#include <queue>


using namespace std;

class ArraySolution {
public:
    int largestSumAfterKNegations(vector<int> &A, int K);

    int lastStoneWeight(vector<int> &stones);

    vector<vector<int>> largeGroupPositions(string S);

    int search(vector<int> &nums, int target);

    vector<int> gardenNoAdj(int N, vector<vector<int>> &paths);

    vector<bool> prefixesDivBy5(vector<int> &A);

    int orangesRotting(vector<vector<int>> &grid);
    int numPairsDivisibleBy60(vector<int>& time);
    int pivotIndex(vector<int>& nums);
    int maxIncreaseKeepingSkyline(vector<vector<int>>& grid);


};


class KthLargest {
public:
    KthLargest(int k, vector<int> &nums) {
        K = k;
        for (auto i :nums)addSort(i);
    }

    int add(int val) {
        addSort(val);
        return queue.top();
    }

private:
    int K;
    priority_queue<int,vector<int>, greater<int>> queue;

    void addSort(int data) {
        if (queue.size() < K)queue.push(data);
        else {
            if (queue.top() < data) {
                queue.pop();
                queue.push(data);
            }
        }
    }

};


#endif //C_ARRAYSOLUTION_H
