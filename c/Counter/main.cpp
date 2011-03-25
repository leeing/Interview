/* 
 * File:   main.cpp
 * Author: leeing
 *
 * Created on March 24, 2011, 2:56 PM
 */

#include <cstdlib>
#include <iostream>

int counter(int num,int max);
using namespace std;


/*
 *　 f(n,1) = 1;
 *   f(n,m) = f(n,n) m>n
 *   f(n,n) = f(n,n-1) + 1
 *   f(n,m) = f(n-1,m)+ f(n-m,m)
 */
int main(int argc, char** argv) {
    int num;
    cout<<"input a num:"<<endl;
    cin>>num;
    int result = counter(num,num);
    cout<<"The result is "<<result<<endl;
    return EXIT_SUCCESS;
}

int counter(int num,int max){
    if(max == 1){
        return 1;
    }
    if(max>num){
        return counter(num,num);
    }

    if(num == max){
        return counter(num,max-1) + 1;
    }else{
        return counter(num,max-1)+counter(num-max,max);
    }

}
