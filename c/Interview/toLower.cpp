/* 
 * File:   toLower.cpp
 * Author: leeing
 *
 * Created on May 4, 2011, 11:22 AM
 */
#include<iostream>
#include <cstdlib>
#include <string>
#include <cstring>
#include <cassert>

int tolower(char *str);
int testAssert(int num);
char* reverse(char *str);
using namespace std;

/*
 * 
 */
int main222(int argc, char** argv) {

    char str[] = "Hello world!";
    cout<<"sizeof str is:"<<sizeof(str)<<endl;
    int change = tolower(str); 
    cout<<str<<", changed "<<change<<" times"<<endl;
    cout<<"sizeof str is:"<<sizeof(str)<<endl;

    cout<<"after reverse is:"<<reverse(str)<<endl;
//    testAssert(3);
//    testAssert(5);
    return 0;
}

int testAssert(int num){
    assert(num == 3);
    cout<<"assert passed!"<<endl;
}

int tolower(char *str) {

    assert(NULL!=str);
    int i = 0, iCount = 0;
    cout << "length is:" << strlen(str) << endl;
    for (; i < strlen(str); i++) {
        if (str[i] <= 'Z' && str[i] >= 'A') {
            str[i] += 'z' - 'Z';
            iCount++;
        }
    }
    return iCount;
}


char* reverse(char *str){
    if(NULL==str){
        return NULL;
    }
    int len = strlen(str);
    for(int i = 0;i<len/2;i++){
        char temp = str[i];
        str[i] = str[len-i-1];
        str[len-i-1] = temp;
    }
    return str;
}