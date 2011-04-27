/* 
 * File:   sizeOfChar.cpp
 * Author: leeing
 *
 * Created on April 26, 2011, 11:03 AM
 */

#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;

/*
 * 2011.04.25 腾讯一面的题。
 */
int main(int argc, char** argv) {

    char ch[10];
    printf("Define ch: char ch[10]\n");
    printf("strlen(ch) is %d\n",strlen(ch));
    printf("sizeof(ch) is: %d\n",sizeof(ch));
    for(int i = 0;i<sizeof(ch);i++){
        ch[i] = 0;
    }

    printf("strlen(ch) is: %d\n",strlen(ch));
    for(int i = 0;i<sizeof(ch);i++){
        printf("%d : %c\n",i,ch[i]);
    }

    if(0 == '\0'){
        printf("0 is equal '\\0'\n");
    }else{
        printf("0 is not equal '\\0'\n");
    }

    int j = 0;
    int A[9];
    for(;j<10;j++){
        A[j] = 0;
    }

    for(int j = 0;j<10;j++){
        printf("%d\t",A[j]);
    }
    return 0;
}

