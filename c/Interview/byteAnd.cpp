/* 
 * File:   byteAnd.cpp
 * Author: leeing
 *
 * Created on April 22, 2011, 7:11 PM
 */

#include <cstdlib>
#include <iostream>

using namespace std;

int func(int x){
    int counterx = 0;
    while(x)
    {
        counterx ++;
        x = x&(x-1);
    }
    return counterx;
}
/*
 * 腾讯笔试题，实际答案是 x 转换成二进制数后 1 的个数。
 */
int main1(int argc, char** argv) {

    cout<<"The result is:"<<func(1429)<<endl;
    return 0;
}

