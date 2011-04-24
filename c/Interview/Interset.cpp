/* 
 * File:   Interset.cpp
 * Author: leeing
 *
 * Created on April 24, 2011, 2:13 PM
 */

#include <cstdlib>
#include <bitset>
#include <set>
#include <climits>
#include <iostream>

using namespace std;

/*
 * 
 */
int main4(int argc, char** argv) {

    int a[10000000];
    int b[10000000];
    set<int> set;
    bitset<INT_MAX> bitset;
    for(size_t i = 0;i<10000000;i++){
        bitset.set(a[i],true);
    }

    for(size_t i = 0;i<10000000;i++){
        if(bitset.test(b[i])){
            set.insert(b[i]);
        }
    }

    set<int>::const_iterator it;

    for(it = set.begin();it!=set.end();it++){
        cout<<*it<<endl;
    }
    return 0;
}

