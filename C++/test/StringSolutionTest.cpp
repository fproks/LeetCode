//
// Created by ezlinho on 5/27/2019.
//

#include "../StringSolution.h"
#include <assert.h>

StringSolution *stringSolution =new StringSolution();

void isLongPressedNameTest(){
    string name1="alex", typed1="aaleexd";
    string name2 = "saeed", typed2 = "ssaaedd";
    assert(stringSolution->isLongPressedName(name1, typed1));
    assert(!stringSolution->isLongPressedName(name2, typed2));
    assert(stringSolution->isLongPressedName( "leelee", "lleeelee"));
    assert(stringSolution->isLongPressedName("laiden",  "laiden"));
}

void isRobotBounded(){
    assert(!stringSolution->isRobotBounded("GG"));
    assert(stringSolution->isRobotBounded("GL"));
    assert(stringSolution->isRobotBounded("GGLLGG"));
}


void repeatedStringMatch(){
    assert(stringSolution->repeatedStringMatch( "abcd","cdabcdab")==3);
}

void buddyStrings(){
    assert(stringSolution->buddyStrings("ab","ba"));
    assert(!stringSolution->buddyStrings("ab", "ab"));
    assert(stringSolution->buddyStrings("aa","aa"));
    assert(stringSolution->buddyStrings("aaaaaaabc","aaaaaaacb"));
    assert(!stringSolution->buddyStrings("","aa"));


}