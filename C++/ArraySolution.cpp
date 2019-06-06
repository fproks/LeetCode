//
// Created by ezlinho on 5/21/2019.
//

#include "ArraySolution.h"
#include <algorithm>
#include <queue>
#include <iostream>


int ArraySolution::largestSumAfterKNegations(vector<int> &A, int K) {
    sort(A.begin(), A.end());
    int checker = 0, sum = 0;
    for (int i = 0; i < K; ++i) {
        A[checker] = -A[checker];
        if (checker < (A.size() - 1) && A[checker] > A[checker + 1])
            checker++;
    }
    for (int a : A)
        sum += a;
    return sum;
}

int ArraySolution::lastStoneWeight(vector<int> &stones) {
    sort(stones.begin(), stones.end());
    while (stones.size() >= 2) {
        const int a = stones.back();
        stones.pop_back();
        int b = stones.back();
        stones.pop_back();
        int sub = a - b;
        if (sub > 0) {
            if (stones.size() == 0) return sub;
            if (stones.size() == 1) return abs(sub - stones[0]);
            for (int i = 0; i < stones.size(); ++i) {
                if (sub <= stones[i]) {
                    stones.insert(stones.begin() + i, sub);
                    break;
                }
                if (i == stones.size() - 1) {
                    stones.insert(stones.end(), sub);
                    break;
                }
            }
        }
    }
    return stones.size() == 0 ? 0 : stones[0];
}

vector<vector<int>> ArraySolution::largeGroupPositions(string S) {
    vector<vector<int>> result;
    int first, count = 0;
    char c = 0;
    int size = S.size();
    for (int i = 0; i < size; ++i) {
        if (c == S[i]) {
            count++;
        } else {
            if (count >= 3) {
                vector<int> tmp({first, i - 1});
                first = i, count = 1;
                c = S[i];
                result.push_back(tmp);
            } else {
                first = i;
                count = 1;
                c = S[i];
            }
        }
    }
    if (count >= 3) result.push_back(vector<int>({first, size - 1}));
    return result;
}

int ArraySolution::search(vector<int> &nums, int target) {
    int first = 0, last = nums.size() - 1;
    while (first <= last) {
        int mid = (first + last) / 2;
        if (nums[mid] < target) {
            first = mid + 1;
            continue;
        }
        if (nums[mid] > target) {
            last = mid - 1;
            continue;
        }
        if (nums[mid] == target)return mid;
    }
    return -1;
}

vector<int> ArraySolution::gardenNoAdj(int N, vector<vector<int>> &paths) {
    vector<int> array[N + 1];
    vector<int> result;
    for (auto path : paths) {
        auto i = path[0];
        auto j = path[1];
        array[i].push_back(j);
        array[j].push_back(i);
    }
    result.push_back(1);
    int i = 2;
    while (i <= N) {
        bool first = true, second = true, third = true, forth = true;
        auto idx = array[i];
        for (auto j : idx) {
            if (result.size() >= j) {
                switch (result[j - 1]) {
                    case 1:
                        first = false;
                        break;
                    case 2:
                        second = false;
                        break;
                    case 3:
                        third = false;
                        break;
                    case 4:
                        forth = false;
                        break;
                }
            }
        }
        i++;
        if (first) {
            result.push_back(1);
            continue;
        }
        if (second) {
            result.push_back(2);
            continue;
        }
        if (third) {
            result.push_back(3);
            continue;
        }
        if (forth) {
            result.push_back(4);
        }
    }
    return result;
}

int ArraySolution::maxIncreaseKeepingSkyline(vector<vector<int>> &grid) {
    int *heng=new int[grid.size()];
    int  *zong =new int[grid[0].size()];
    fill_n(heng,grid.size(),0);
    fill_n(zong,grid[0].size(),0);
    int first=0,last=0;
    for (int i = 0; i < grid.size(); ++i) {
        for (int j = 0; j < grid[i].size(); ++j) {
            first+=grid[i][j];
            heng[i] =max(heng[i],grid[i][j]);
            zong[j] =max(zong[j],grid[i][j]);
        }
    }
    for (int i = 0; i < grid.size(); ++i) {
        for (int j = 0; j <grid[0].size() ; ++j) {
            grid[i][j] =min(heng[i],zong[j]);
            last+=grid[i][j];
        }
    }
    return  last-first;
}

int ArraySolution::orangesRotting(vector<vector<int>> &grid) {

    int firstsum, lastsum = 0;
    int idx = 0;
    queue<vector<int>> tmp;
    for (auto i = 0; i < grid.size(); i++) {
        for (auto j = 0; j < grid[i].size(); j++) {
            if (grid[i][j] == 1) firstsum++;
            if (grid[i][j] == 2) tmp.push(vector<int>({i, j}));
        }
    }
    if (firstsum == 0)return 0;
    lastsum = firstsum;
    do {
        firstsum = lastsum;
        idx++;
        auto size = tmp.size();
        while (size > 0) {
            auto idx = tmp.front();
            auto i = idx[0], j = idx[1];
            if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                grid[i - 1][j] = 2;
                tmp.push(vector<int>({i - 1, j}));
                lastsum--;
            }
            if (i + 1 < grid.size() && grid[i + 1][j] == 1) {
                grid[i + 1][j] = 2;
                tmp.push(vector<int>({i + 1, j}));
                lastsum--;
            }
            if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                grid[i][j - 1] = 2;
                tmp.push(vector<int>({i, j - 1}));
                lastsum--;
            }
            if (j + 1 < grid[i].size() && grid[i][j + 1] == 1) {
                grid[i][j + 1] = 2;
                tmp.push(vector<int>({i, j + 1}));
                lastsum--;
            }
            size--;
            tmp.pop();
        }
    } while (lastsum != 0 && lastsum != firstsum);
    if (lastsum > 0)return -1;
    return idx;

}

//https://blog.csdn.net/qq_43274298/article/details/88927741
vector<bool> ArraySolution::prefixesDivBy5(vector<int>& A){
    vector<bool> result;
    int status[5][2]={{0,1},{2,3},{4,0},{1,2},{3,4}};
    int i =0;
    for (auto j : A){
        i=status[i][j];
        result.push_back(i == 0);
    }
    return  result;
}

int ArraySolution::numPairsDivisibleBy60(vector<int>& time){
    int sec[60];
    fill_n(sec,60,0);
    for (auto i : time){
        auto j=i%60;
        sec[j]++;
    }
    int result=0;
    result +=sec[0]*(sec[0]-1)/2;
    for(int i =1;i<=29;i++){
        if(sec[i]!=0 && sec[60-i]!=0)result += sec[i]*sec[60-i];
    }
    result+=sec[30]*(sec[30]-1)/2;
    return  result;
}

int ArraySolution::pivotIndex(vector<int> &nums) {
    if(nums.size()<3)return  -1;
    int sum =0;
    for(auto i : nums)sum+=i;
    int midsum=0;
    for(auto i =0;i<nums.size();i++){
        if(midsum*2+nums[i]==sum)return  i;
        else {
            midsum+=nums[i];
        }
    }
    return  -1;
}


