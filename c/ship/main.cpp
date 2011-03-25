/* 
 * File:   main.cpp
 * Author: leeing
 *
 * Created on March 24, 2011, 3:51 PM
 */

#include <cstdlib>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

/*
 * 
 */
int main(int argc, char** argv) {
    int total;
    cout<<"input the total weight:"<<endl;
    cin>>total;

    vector<int> weights;

    int weight;
    cout<<"input the weights of the box.ended with 0."<<endl;
    cin>>weight;
    while(weight!=0){
        weights.push_back(weight);
        cin>>weight;
    }

    sort(weights.begin(),weights.end());
//    cout<<"the weights are:"<<endl;
//    for(int i = 0;i<weights.size();i++){
//        cout<<weights[i]<<endl;
//    }
    int remain = total;
    for(int i = 0;i<weights.size()&& remain>=0;i++){
        remain = remain - weights[i];
        if(remain>=0){
            cout<<"loading "<<weights[i]<<endl;
        }
    }
    cout<<"end loading."<<endl;
    
    return 0;
}

