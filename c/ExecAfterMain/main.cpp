/* 
 * File:   main.cpp
 * Author: leeing
 *
 * Created on March 31, 2011, 10:02 AM
 */

#include <cstdlib>
#include <cstdio>

int atexit(void (*function)(void));
void fn1(void);

int value = 10;
using namespace std;

/*
 * 
 */
int main(int argc, char** argv) {
    atexit(fn1);
    int value = 100;
    printf("::value is %d\n",::value);
    printf("local value is %d\n",value);
    printf("This is executed first.\n");

    printf("this is v~\v");
    return 0;
}

void fn1(){
    printf("next.\n");
} 
