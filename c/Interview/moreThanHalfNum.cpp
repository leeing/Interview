/* 
 * File:   moreThanHalfNum.cpp
 * Author: leeing
 *
 * Created on April 24, 2011, 9:00 PM
 */

#include <cstdlib>

using namespace std;

/*
 * 找出数组中出现次数超过一半的元素
 */
int main(int argc, char** argv) {

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

