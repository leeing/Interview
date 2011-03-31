/* 
 * File:   main.cpp
 * Author: leeing
 *
 * Created on March 25, 2011, 11:01 AM
 */

#include <cstdlib>
#include <cstdio>
#define product(x) (x*x)

using namespace std;

/*
 * 用位运算操作实现两个整数的交换
 */
int main(int argc, char** argv) {
    int a,b;
    a = 5;
    b = 10;


    printf("Original : a = %d, b = %d\n",a,b);
    a = a^b;
    b = b^a;
    a = a^b;

    printf("After: a = %d, b = %d\n",a,b);


    printf("other method:\n");
    a = a+b;
    b = a-b;
    a = a-b;

    printf("Now a is : %d,b is :%d\n",a,b);


    printf("product macro:\n");
    int i = 3,j,k;
    j = product(i++);
    k = product(++i);
    printf("j = %d,k = %d\n",j,k);

    return EXIT_SUCCESS;
}

