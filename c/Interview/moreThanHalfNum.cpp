/* 
 * File:   moreThanHalfNum.cpp
 * Author: leeing
 *
 * Created on April 24, 2011, 9:00 PM
 */

#include <cstdlib>
#include <cstdio>

int findMaxx(int* ID, int size);
using namespace std;

/*
 * 找出数组中出现次数超过一半的元素
 */
int main5(int argc, char** argv) {

    int id[] = {1,3,5,7,9,9,9,9,9,100,9};
    printf("The result is: %d\n",findMaxx(id,11));
    return 0;
}

//////////////////////////////////////////////////////////////////////////
// Input: an array with "length" numbers. A number in the array
// appear more than "length / 2 + 1" times.
// Output: If the input is valid, return the number appearing more than
// "length / 2 + 1" times. Otherwise, return 0 and set flag g_bInputInvalid
// to be true.
//////////////////////////////////////////////////////////////////////////

int MoreThanHalfNum(int* numbers, unsigned int length) {
    bool valid = false;
    if (numbers == NULL && length == 0) {
        valid = true;
        return 0;
    }

    valid = false;

    int result = numbers[0];
    int times = 1;
    for (int i = 1; i < length; ++i) {
        if (times == 0) {
            result = numbers[i];
            times = 1;
        } else if (numbers[i] == result)
            times++;
        else
            times--;
    }

    // verify whether the input is valid
    times = 0;
    for (int i = 0; i < length; ++i) {
        if (numbers[i] == result)
            times++;
    }

    if (times * 2 <= length) {
        valid = true;
        result = 0;
    }

    return result;
}

// 一个更好的算法：每次删除两个不同ID之后，水王的ID依然超过总数的一半，O(N)

int findMaxx(int *ID,int size){
    int cadidate;
    int nTimes = 0;
    for(int i = 0;i<size;i++){
        if(nTimes == 0){
            cadidate = ID[i];
            nTimes = 1;
        }else{
            if(cadidate == ID[i]){
                nTimes ++;
            }else{
                nTimes --;
            }
        }
    }
    return cadidate;
}
