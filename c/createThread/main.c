/* 
 * File:   main.c
 * Author: leeing
 *
 * Created on March 22, 2011, 10:38 AM
 */
#include <unistd.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void *thread_function(void *arg);
char message[] = "Hello world!";
/*
 * 
 */
int main(int argc, char** argv) {

    int res;
    pthread_t a_thread;
    void *thread_result;

    res = pthread_create(&a_thread,NULL,thread_function,(void*)message);
    if(res!=0){
        perror("Thread creation failed\n");
        exit(EXIT_FAILURE);
    }
    printf("waiting for thread to finish ...\n");
    res = pthread_join(a_thread,&thread_result);
    if(res!=0){
        perror("Thread join failed!\n");
        exit(EXIT_FAILURE);
    }

    printf("Thread joined,it returned %s\n",(char*)thread_result);
    printf("Message is now %s\n",message);
    printf("Message's length is: %d\n",strlen(message));
    exit(EXIT_SUCCESS);

}

void *thread_function(void* arg){
    printf("thread_function is running. Argument was %s\n",(char*)arg);
    sleep(3);
    strcpy(message,"Bye!");
    pthread_exit("Thank you for the CPU time!");
}
