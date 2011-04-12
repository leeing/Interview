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
struct student{

}stu;

class A{
};
/*
 * 
 */
int main(int argc, char** argv) {
//    atexit(fn1);
//    int value = 100;
//    printf("::value is %d\n",::value);
//    printf("local value is %d\n",value);
//    printf("This is executed first.\n");
//
//    printf("this is v~\v");

    printf("the size of empty struct is: %d\n",sizeof(stu));
    printf("the size of empty class is :%d\n",sizeof(A));
    return 0;
}

void fn1(){
    printf("next.\n");
} 
