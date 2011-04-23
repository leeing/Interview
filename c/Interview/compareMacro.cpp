/* 
 * File:   compareMacro.cpp
 * Author: leeing
 *
 * Created on April 22, 2011, 7:19 PM
 */

#include <cstdlib>
#include <iostream>

#define MAX(a,b) (a)>(b)?(a):(b)
using namespace std;

/*
 * 
 */
int main(int argc, char** argv) {
//    cout<<"max is :"<<MAX(3+2,6*3)<<endl;
    cout<<__FILE__<<endl;
    cout<<__LINE__<<endl;
    

    for(int i = 9;i<=18;i++){
        for(int j = 0;j<9;j++){
            cout<<11*i+j<<":"<<i<<","<<j<<endl;
        }
    }

    return 0;
}

