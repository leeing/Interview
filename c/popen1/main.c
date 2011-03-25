/* 
 * File:   main.c
 * Author: leeing
 *
 * Created on March 23, 2011, 10:35 AM
 */
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
void print(void);
/*
 * 
 */
int main(int argc, char** argv) {
    FILE *read_fp;
    static int i = 3;
    char buffer[BUFSIZ+1];
    int chars_read;
    memset(buffer,'\0',sizeof(buffer));
    read_fp = popen("uname -a","r");
    if(read_fp != NULL){
        chars_read = fread(buffer,sizeof(char),BUFSIZ,read_fp);
        if(chars_read >0){
            printf("Output was:-\n%s\n",buffer);
        }
        pclose(read_fp);
        exit(EXIT_SUCCESS);

    }

    return (EXIT_FAILURE);
}

void print(void){
    printf("static is: %d",0);
}

