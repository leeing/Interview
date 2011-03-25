/* 
 * File:   main.cpp
 * Author: leeing
 *
 * Created on March 24, 2011, 4:22 PM
 */

#include <cstdlib>
#include <iostream>

using namespace std;

void bubble_sort(int *a, int length);

/*
 * 
 */
int main(int argc, char** argv) {

    int a[10] = {1, 8, 7, 6, 6, 4, 2, 3, 5};
    bubble_sort(a, 10);
    for (int i = 0; i < 10; i++) {
        cout << a[i] << endl;
    }
    return 0;
}

void bubble_sort(int* a, int length) {
    bool isChange = false;
    for (int i = 0; i < length-1; i++) {
        for (int j = 0; j < length - i - 1; j++) {
            if (a[j] > a[j + 1]) {
                int temp = a[j];
                a[j] = a[j + 1];
                a[j + 1] = temp;
                isChange = true;
            } 
            isChange = false;
        }


    }
}
