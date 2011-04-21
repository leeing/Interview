/* 
 * File:   main.cpp
 * Author: leeing
 *
 * Created on April 21, 2011, 4:24 PM
 */

#include <cstdlib>
#include <iostream>

using namespace std;

struct A {
    char t : 4; // 注意冒号起的作用
    char k : 4;
    unsigned short i : 8;
    unsigned long m;
} a;

struct B {
    char t ;
    char k ;
    short i ;
    long m;
} b;

/*
 *  关于 sizeof 的问题
 */
int main(int argc, char** argv) {

    cout<<"size of struct A is:"<<sizeof(a)<<endl;
    cout<<"size of struct B is:"<<sizeof(B)<<endl;
    return 0;
}

