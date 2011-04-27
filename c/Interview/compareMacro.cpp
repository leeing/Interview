/* 
 * File:   compareMacro.cpp
 * Author: leeing
 *
 * Created on April 22, 2011, 7:19 PM
 */

#include <cstdlib>
#include <iostream>

#define COMPARE(a,b) ((a)>(b))?(a):(b)
using namespace std;

/*
 * 
 */
int main153(int argc, char** argv) {
    int a = COMPARE(3+2,6*6+6);
    
    cout<<"max is :"<<a<<endl;// 注意不能把COMPARE(a,b)直接放到 << 里边。
    cout<<"max is :"<<(COMPARE(3+2,6*6+6))<<endl;
    cout<<"max is :"<<(((3+2)>(6*6+6))?(3+2):(6*6+6))<<endl;

    cout<<__FILE__<<endl;
    cout<<__LINE__<<endl;
    

    for(int i = 9;i<=18;i++){
        for(int j = 0;j<9;j++){
            cout<<11*i+j<<":"<<i<<","<<j<<endl;
        }
    }

    return 0;
}

