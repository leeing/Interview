/* 
 * File:   array.cpp
 * Author: leeing
 *
 * Created on April 22, 2011, 7:00 PM
 */

#include <cstdlib>
#include <iostream>

using namespace std;

/*
 * 腾讯笔试题，指针与数组间的关系
 */
int main0(int argc, char** argv) {
    int a[2][3] = {1,2,3,4,5};
    int (*p)[3];
    p =a;
    cout<<*(*(p+1)+1)<<endl;

}

