/* 
 * File:   main.c
 * Author: leeing
 * desc : 带头节点的链表
 * Created on September 19, 2010, 9:50 AM
 */
/*
 * 要点：
 * 1. 头插入不需要临时节点来保存，每次直接对头节点进行操作。
 * 2. 尾插入需要一个临时节点来保存最后一个节点的位置。
 * 3. 注意 while 中的 scanf。
 */
#include <stdio.h>
#include <stdlib.h>

typedef struct node{
    int data;
    struct node* next;
}Node;

void head_insert();
void tail_insert();
void traverse();
int getLength(Node*);

int main(int argc, char** argv) {
    int choice;
    printf("input your choice:\n1.head insert.\n2.tail insert.\n");
    scanf("%d",&choice);

    switch(choice){
        case 1:
            head_insert();
            break;
        case 2:
            tail_insert();
            break;
        default:
            printf("wrong option!");
    }
    return (EXIT_SUCCESS);
}

void tail_insert() {
    Node* head = malloc(sizeof (Node));
    Node* temp = head;

    int num;
    printf("please input a num,ended with 0:\n");
    scanf("%d", &num);

    while (num != 0) {
        Node* node = malloc(sizeof (Node));
        node->data = num;
        node->next = NULL;
        temp->next = node;
        temp = node;
        scanf("%d", &num);
    }
    printf("the elements are:\n");
    traverse(head);
    printf("the length of the list is: %d\n",getLength(head));

}

void head_insert(){
    Node* head = malloc(sizeof(Node));
    head->next = NULL;
    
    int num;
    printf("please input a num,ended with 0:\n");
    scanf("%d",&num);
    
    while(num!=0){
        Node* node = malloc(sizeof(Node));
        
        node->data = num;
        node->next = NULL;

        node->next = head->next;
        head->next = node;
        scanf("%d",&num);
    }
    printf("the elements are:\n");
    traverse(head);
    printf("the length of the list is: %d\n",getLength(head));
   }

void traverse(Node* list){
    if(list == NULL){
        printf("empty list");
        exit(0);
    }
    Node* head = list->next;
    while(head!=NULL){
        printf("%d\n",head->data);
        head=head->next;
    }
}

int getLength(Node* list){
    if(list->next == NULL)
        return 0;
    Node* head = list->next;
    int length=0;

    while(head!=NULL){
        head = head->next;
        length++;
    }
    return length;
}
