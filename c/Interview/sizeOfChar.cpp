/* 
 * File:   sizeOfChar.cpp
 * Author: leeing
 *
 * Created on April 26, 2011, 11:03 AM
 */

#include <cstdlib>
#include <cstdio>
#include <cstring> 
#include <iostream>

// 百度笔试里的

struct s1 {
    char ch, *ptr;

    union {
        //        short a;
        //        unsigned int c : 2, d : 1;
    };
    struct s1 *next;
    void say(){
        printf("hello world!\n");
    }
};

struct A {
    unsigned short i : 8;
    unsigned long m;
    char k : 4;
    char t : 4;
};

enum Color{
    GREEN = 1,
    RED,
    BLUE,
    GREEN_RED = 10,
    GREEN_BLUE
};

union{
    int i;
    char a[2];
} *p,u;
using namespace std;

bool isSmallEndian();

/*
 * 2011.04.25 腾讯一面的题。
 */
int main(int argc, char** argv) {

    //    char ch[10];
    //    printf("Define ch: char ch[10]\n");
    //    printf("strlen(ch) is %d\n", strlen(ch));
    //    printf("sizeof(ch) is: %d\n", sizeof (ch));
    //    for (int i = 0; i<sizeof (ch); i++) {
    //        ch[i] = 0;
    //    }
    //
    //    printf("strlen(ch) is: %d\n", strlen(ch));
    //    for (int i = 0; i<sizeof (ch); i++) {
    //        printf("%d : %c\n", i, ch[i]);
    //    }
    //
    //    if (0 == '\0') {
    //        printf("0 is equal '\\0'\n");
    //    } else {
    //        printf("0 is not equal '\\0'\n");
    //    }
    //
    //    int j = 0;
    //    int A[9];
    //    for (; j < 10; j++) {
    //        A[j] = 0;
    //    }
    //
    //    for (int j = 0; j < 10; j++) {
    //        printf("%d\t", A[j]);
    //    }

//    cout << "sizeof the struct is: " << sizeof (s1) << endl;
//    s1 *ssss = new s1();
//    ssss->say();
//    char *chs;
//    cout << "size of *char is:" << sizeof (chs) << endl;
//
//    short a[100];
//    cout << "size of a[100] of a is:" << sizeof (a) << endl;
//
//    cout << "size of A is: " << sizeof (A) << endl;
//
//    int b[5] = {1,2,3,4,5};
//    int *ptr1 = (int *)(&b+1); // 地址会比 b 增加 4*5 = 20 个字节
//    int *ptr2 = ((int *)(int)b + 1);  // 地址只比 b 增加 1
//    printf("%x , %x\n", ptr1[-1],*ptr2);
//    printf("b is %d\n",b);
//    printf("&b is %d\n",&b);
//    printf("&b + 1 is %d\n",(&b+1));
//    printf("(int)b + 1 is %d\n",(int)b + 1);
//    printf("ptr[-1] is %d\n",&ptr1[-1]);
//    printf("ptr2 is %d\n",ptr2);




    printf("small ? %d\n",isSmallEndian());
    p = &u;
    p->a[0] = 0x00;
    p->a[1] = 0x01;
    printf("p.i is %d\n",p->i);

    printf("size of enum Color is :%d\n",sizeof(Color));
    return 0;
}

bool isSmallEndian(){
    union check{
        char ch;
        int i;
    } checks;

    checks.i = 1;
    return checks.ch == checks.i;
}
