/* 
 * File:   main.c
 * Author: leeing
 *
 * Created on March 23, 2011, 3:41 PM
 */
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

/*
 * 
 */
int main(int argc, char** argv) {
    FILE* write_fp;
    char buffer[BUFSIZ+1];
    sprintf(buffer,"Once upon a time,there was ...\n");
    printf("buffer is:%s\n",buffer);
    write_fp = popen("od -c","w");
    if(write_fp!= NULL){
        fwrite(buffer,sizeof(char),strlen(buffer),write_fp);
        pclose(write_fp);
        exit(EXIT_SUCCESS);
    }
    
    return (EXIT_FAILURE);
}

