/* 
 * File:   main.c
 * Author: leeing
 *
 * Created on September 28, 2010, 10:06 AM
 *
 * 本程序练习各种排序算法。
 * 包括：冒泡排序，插入排序，快速排序
 */

#include <stdio.h>
#include <stdlib.h>

/*
 * 
 */
void insertionSort(int arr[]);
void bubbleSort(int *arr,int size);
void quickSort(int *arr,int size);

void printArr(int arr[],int length);
void getSize(int *arr);

int main(int argc, char** argv) {

    int arr[10] ={3,1,5,1,6,4,8,0,2,0};
    int len = sizeof(arr)/sizeof(int);

    bubbleSort(arr,len);
    printf("Bubble sort result: ");
    printArr(arr,len);
/*
    getSize(arr);
*/
    return (EXIT_SUCCESS);
}

/* 冒泡排序
 * 复杂度：O(n^2)
 * 稳定的
 * 
 *
 */
void bubbleSort(int *arr,int length) {
    for (int i = 0; i < length-1; i++) {
        for (int j = 0; j < length-i-1; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp; 
            }
        }
    }
}



void printArr(int arr[],int length) {
    for(int i = 0;i< length;i++){
        printf("%d\t",arr[i]);
    }
    printf("\n");
}

/**
 *
 * @param arr
 * 注意如果直接传入数组的首地址，其计算結果只是其一个数据类型的长度
 */
void getSize(int *arr){
    int size = sizeof(arr)/sizeof(int);
    printf("the size of the array is:%d\n",size);
}