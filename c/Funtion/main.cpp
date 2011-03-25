/* 
 * File:   main.cpp
 * Author: leeing
 *
 * Created on March 24, 2011, 8:14 PM
 */

#include <cstdlib>
#include <cstdio>

using namespace std;

void sayHello(void);
void sayNo(void);
typedef void (*fun)(void);
void tryFuntor(fun);

/*
 * 
 */
int main(int argc, char** argv) {
//    void (*say)(void);
//    say = sayHello;
//
//    (*say)();
//
//    say = &sayNo;
//    (*say)();
//
//    (*sayHello)();
//    (*sayNo)();

    fun myfun;
    myfun = sayHello;
//    (myfun)();
//    (*myfun)();
//    myfun = sayNo;
//    myfun();

    tryFuntor(myfun);
    return 0;
}

void sayHello(void){
    printf("hello I'm leeing!\n");
}

void sayNo(void){
    printf("No!\n");
}

void tryFuntor(fun fun1){
    fun1();
}


