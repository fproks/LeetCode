//
// Created by ezlinho on 9/19/2019.
//

#ifndef C___MYTUPLE_H
#define C___MYTUPLE_H


#include<tuple>

using namespace std;

class MyTuple {
public:
    MyTuple(int f, int s, int d) : distance(d), first(f), second(s) {}

    int get0() { return first; }

    int get1() { return second; }

    int getvalue() { return distance; }

    friend bool operator<(const MyTuple &pl, const MyTuple &pr) {
        if (pl.first == pr.first && pl.second == pr.second)return false;
        if (pl.first == pr.second && pl.second == pr.first) return false;
        if (pl.distance == pr.distance) return pl.first < pr.first;
        else return pl.distance < pr.distance;
    }

private:
    int first;
    int second;
    int distance;
};

#endif //C___MYTUPLE_H
