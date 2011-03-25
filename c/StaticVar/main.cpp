/* 
 * File:   main.cpp
 * Author: leeing
 *
 * Created on March 25, 2011, 7:20 PM
 */

#include <cstdlib>
#include <cstdio>

using namespace std;

int fun1();
int fun2();

  static int i = 0;
/*
 * properties of static
 */
int main(int argc, char** argv) {

  
//    fun1();
//    fun1();
//    fun1();
    fun2();
    fun2();
    return 0;
}

int fun1(){
    static int j = 1; // 下次调用时
    j++;
    printf("j is:%d\n",j);
}

int fun2(){
    i++;
    printf("i is:%d\n",i);
}
