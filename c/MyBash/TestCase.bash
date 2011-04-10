#!/bin/bash
# 
# File:   TestCase.bash
# Author: leeing
#
# Created on Apr 10, 2011, 7:47:11 PM
#


read -p "Input a number:" num

case "$num" in
    2) echo "it's 2 or 3";;
    4) echo "it's 4 or 5";;
    *) echo "others";;
esac

echo "the script name is $0"