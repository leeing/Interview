/* 
 * File:   heapSort.cpp
 * Author: leeing
 *
 * Created on May 19, 2011, 4:05 PM
 */

#include <cstdio>

#define SIZE 10

#define Left(i)        ((i) << 1) // left(i) = 2*i
#define Right(i)    (((i) << 1) + 1) // right(i) = 2*i+1

void heapsort(int a[], int heapsize);
void maxheapify(int a[], int i, int heapsize);
void swap(int *x, int *y);

int main4456(int argc, char **argv) {
    int a[SIZE + 1] = {0, 16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
    /* note a[0] is not used */
    int i, heapsize;

    heapsize = SIZE;
    heapsort(a, heapsize);

    for (i = 1; i < SIZE + 1; i++) /* print the sorted array */
        printf("%d ", a[i]);
    printf("\n");

    return 0;
}

/*
 * heapsort: contains two procedures, one is to build the max-heap,
 * the other is to delete max to gain the sorted array.
 */
void heapsort(int a[], int heapsize) {

    for (int i = heapsize / 2; i >= 1; i--) /* build max-heap */
        maxheapify(a, i, heapsize);

    for (int i = heapsize; i >= 2; i--) /* delete max */ {
        swap(&a[1], &a[i]);
        heapsize--;
        maxheapify(a, 1, heapsize);
    }

}

/*
 * maxheapify: used to let the value at a[i] "float down" in the
 * max-heap so that the subtree rooted at index i becomes a max-heap.
 */
/**
 * 
 * @param a 数组
 * @param i 将要调整的节点下标
 * @param heapsize 当前的堆大小
 */
void maxheapify(int a[], int i, int heapsize) {
    int largest;

    int left = Left(i);
    int right = Right(i);

    // 求出 根，左子，右子之中，值最大的下标
    if (left <= heapsize && a[left] > a[i])
        largest = left;
    else
        largest = i;

    if (right <= heapsize && a[right] > a[largest])
        largest = right;


    // 如果不是根的值最大，则说明需要进行调整，所以要进行交换。
    if (largest != i) {
        swap(&a[i], &a[largest]);
        maxheapify(a, largest, heapsize); /* recurrsion */
    }

    return; /* return when largest == i */
}

void swap(int *x, int *y) {
    int temp;

    temp = *x;
    *x = *y;
    *y = temp;
}

