/* 
 * File:   main.c
 * Author: leeing
 * 1. 二叉树的前序创建。
 * 2. 前序遍历，中序遍历，后序遍历,层次遍历。
 * 3. 求叶子节点的个数。
 * 4. 求二叉树的深度。
 * Created on September 19, 2010, 7:57 PM
 */
/* important: 为避免错误，以后使用 if( NULL == root->left ) 这样的写法
 */
#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 100

typedef struct BinaryTreeNode{
    int data;
    struct BinaryTreeNode* left;
    struct BinaryTreeNode* right;
}BTreeNode;

BTreeNode* create();
void preorder(BTreeNode* root);
void postorder(BTreeNode* root);
void inorder(BTreeNode* root);
void levelorder(BTreeNode* root);
int getLeavesNum(BTreeNode* root);

int main(int argc, char** argv) {

    BTreeNode* root = create();

    printf("preorder :\n");
    preorder(root);

    printf("\ninorder  :\n");
    inorder(root);

    printf("\npostorder:\n");
    postorder(root);
    printf("\n");

    printf("\nleverorder:\n");
    levelorder(root);
    printf("\n");
    
    printf("the number of leaves is : %d \n",getLeavesNum(root));
    printf("the depth of the binary tree is :%d\n",getDepth(root));
    return (EXIT_SUCCESS);
}
/*
 以前序方式的输入顺序递归建立二叉树
 */
BTreeNode* create(){
    BTreeNode* node;
    int num;
    printf("input a num:\n");
    scanf("%d",&num);
    if(num == 0){
        return NULL;
    }else{
        node = malloc(sizeof(BTreeNode));
        node->data = num;
        printf("输入 %d 的左儿子:\n",num);
        node->left = create();
        printf("输入 %d 的右儿子:\n",num);
        node->right = create();
    }
    return node;
}

void preorder(BTreeNode* root){
    if(root!=NULL){
        printf("%d \t",root->data);
        preorder(root->left);
        preorder(root->right);
    }
}

void inorder(BTreeNode* root){
    if(root!=NULL){
        inorder(root->left);
        printf("%d \t",root->data);
        inorder(root->right);
    }
}

void postorder(BTreeNode* root){
    if(root!=NULL){
        postorder(root->left);
        postorder(root->right);
        printf("%d \t",root->data);
    }
}

void levelorder(BTreeNode* root){
    BTreeNode* queue[MAX_SIZE];
    int front = 0;
    int rear = 0;
    queue[0] = root;
    while(front <= rear){
        BTreeNode* head = queue[front];
        if(head!=NULL){
            printf("%d \t",head->data);
            front++;
        }

        if(head->left != NULL){
            queue[++rear] = head->left;
        }

        if(head->right != NULL){
            queue[++rear] = head->right;
        }
    }

}
 
int getLeavesNum(BTreeNode* root){
    if(root == NULL)
        return 0;
    else if(root->left == NULL && root->right == NULL)
        return 1;
        
    return getLeavesNum(root->left)+getLeavesNum(root->right);
}

int getDepth(BTreeNode* root){
    int leftdepth,rightdepth;
    if(root==NULL){
        return 0;
    }else{
        leftdepth = getDepth(root->left);
        rightdepth = getDepth(root->right);
        if(leftdepth>rightdepth){
            return leftdepth + 1;
        }else{
            return rightdepth + 1;
        }
    }
}
