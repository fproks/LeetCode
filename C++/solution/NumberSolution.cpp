//
// Created by ezlinho on 5/28/2019.
//

#include "NumberSolution.h"
#include <math.h>

//斜率
bool NumberSolution::isBoomerang(vector<vector<int>> &points) {
    return (points[1][0]-points[0][0])*(points[2][1]-points[0][1]) - (points[2][0]-points[0][0])*(points[1][1]-points[0][1]) != 0;
}