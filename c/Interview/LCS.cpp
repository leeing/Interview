/* 
 * File:   LCS.cpp
 * Author: leeing
 *
 * Created on April 25, 2011, 10:51 AM
 */

#include <cstdlib>
#include <cstdio>
#include <cstring>

#define M 100
char* LCS(char left[], char right[]);
using namespace std;

/*
 * 求两个字符串的最长公共子串
 * 
 * 算法的基本思想：
 * 当字符匹配的时候，不是简单的给相应元素赋上1，而是赋上其左上角元素的值加一。
 * 我们用两个标记变量来标记矩阵中值最大的元素的位置，在矩阵生成的过程中来判断
 * 当前生成的元素的值是不是最大的，据此来改变标记变量的值，那么到矩阵完成的时
 * 候，最长匹配子串的位置和长度就已经出来了。
 */
int main6(int argc, char** argv) {

    char str1[M], str2[M];
    printf("请输入字符串1:");
    gets(str1);
    printf("请输入字符串2:");
    gets(str2);
    printf("最长子串为:");
    printf("%s\n", LCS(str1, str2));
    return 0;
}

char* LCS(char left[], char right[]) {
    int lenLeft = strlen(left);
    int lenRight = strlen(right);
    //获取左子串的长度,获取右子串的长度
    char*c = (char*)malloc(lenRight);  //注意这里要写成char型,而不是int型,否则输入整型数据时会产生错误。      //矩阵c纪录两串的匹配情况
    char *p;

    int start, end, len, i, j; //start表明最长公共子串的起始点，end表明最长公共子串的终止点
    end = len = 0; //len表示最长公共子串的长度
    for (i = 0; i < lenLeft; i++)//串1从前向后比较
    {
        for (j = lenRight - 1; j >= 0; j--)//串2从后向前比较
        {
            if (left[i] == right[j])//元素相等时
            {
                if (i == 0 || j == 0)
                    c[j] = 1;
                else {
                    c[j] = c[j - 1] + 1;
                }
            } else
                c[j] = 0;
            if (c[j] > len) {
                len = c[j];
                end = j;
            }
        }
    }
    start = end - len + 1;
    p = (char*) malloc(len + 1); //数组p纪录最长公共子串
    for (i = start; i <= end; i++) {
        p[i - start] = right[i];
    }
    p[len] = '\0';
    return p;
}

//程序测试：
//输入
//字符串1：21232523311324
//字符串2：312123223445
//数组c的变化情况为：
// 0 0 1 0 1 0 1 1 0 0 0 0
// 0 1 0 2 0 0 0 0 0 0 0 0
// 0 0 2 0 3 0 1 1 0 0 0 0
// 1 0 0 0 0 4 0 0 2 0 0 0
// 0 0 1 0 1 0 5 1 0 0 0 0
// 0 0 0 0 0 0 0 0 0 0 0 1
// 0 0 1 0 1 0 1 1 0 0 0 0
// 1 0 0 0 0 2 0 0 2 0 0 0
// 1 0 0 0 0 1 0 0 1 0 0 0
// 0 2 0 1 0 0 0 0 0 0 0 0
// 0 1 0 1 0 0 0 0 0 0 0 0
// 1 0 0 0 0 1 0 0 1 0 0 0
// 0 0 1 0 1 0 2 1 0 0 0 0
// 0 0 0 0 0 0 0 0 0 1 1 0
//长：14（串1的长度），宽：12（串2的长度）
//
//最长子串为:21232　　
