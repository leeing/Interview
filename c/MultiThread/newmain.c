/* 
 * File:   newmain.c
 * Author: leeing
 *
 * Created on April 17, 2011, 10:41 AM
 */

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

/*
 * 
 */
int main(int argc, char** argv) {
    printf("new main function!\n");
    pthread_t thread;
    printf("size of pthread_t is: %d\n",sizeof(thread));
    for(int i = 0;i<100;i++){
        
        if(i%10 == 0){
            printf("\n");
        }
        printf("%d\t",i);
    }
    printf("\n");
    return (EXIT_SUCCESS);
}

