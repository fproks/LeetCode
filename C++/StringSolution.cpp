//
// Created by ezlinho on 5/27/2019.
//

#include <iostream>
#include "StringSolution.h"


//https://leetcode.com/problems/long-pressed-name/discuss/298788/Go-0ms-with-2-pointers
bool StringSolution::isLongPressedName(string name, string typed) {
    int i = 0, j = 0;
    while (i < name.size() && j < typed.size()) {
        if (name[i] == typed[j]) i++, j++;
        else j++;
    }
    return i == name.size();
}

bool StringSolution::isRobotBounded(string instructions) {
    int x = 0, y = 0;
    int dx = 0, dy = 1;
    for (int i = 0; i < 4; ++i) {
        for (auto c : instructions) {
            switch (c) {
                case 'G':
                    if (dx == 1) ++x;
                    if (dx == -1)--x;
                    if (dy == 1)++y;
                    if (dy == -1)--y;
                    break;
                case 'L':
                    if (dx == 0) {
                        swap(dx, dy);
                    } else {
                        dy = -dx;
                        dx = 0;
                    }
                    break;
                case 'R':
                    if (dx == 0) {
                        dx = -dy;
                        dy = 0;
                    } else {
                        swap(dx, dy);
                    }
                    break;
            }
        }
        if (x == 0 && y == 0) return true;
    }
    return false;
}

int StringSolution::repeatedStringMatch(string A, string B) {
    if (B.size() == 0)return 0;
    auto tmp = string(A, 0, A.size());
    int i = 1, index = 0;
    while (tmp.size() < B.size()) {
        tmp += A;
        i++;
    }
    for (auto j = 0; j < 10; j++) {
        if (index == string::npos) return -1;
        else {
            if (tmp.find(B, index) != string::npos) return i;
            else {
                tmp += A;
                i++;
                if (tmp.size() - index > B.size())index = tmp.find(B[0], index + 1);
            }
        }
    }
    return -1;
}


bool StringSolution::buddyStrings(string A, string B) {
    if (A.size() != B.size()) return false;
    int charcount[26];
    bool same = false;
    fill_n(charcount, 26, 0);
    auto first = -1, second = -1;
    for (auto i = 0; i < A.size(); i++) {
        charcount[A[i] - 'a']++;
        same = charcount[A[i] - 'a'] >= 2;
        if (A[i] != B[i]) {
            if (first != -1) {
                if (second != -1) return false;
                else second = i;
            } else {
                first = i;
            }
        }
    }
    if (first != second) {
        return A[first] == B[second] && A[second] == B[first];
    } else return  same;
}