#!/bin/bash
# 
# File:   Counter.bash
# Author: leeing
#
# Created on Apr 9, 2011, 8:10:55 PM
#

foo=1
while [ "$foo" -le 20 ];do
    echo "here we go"
    foo=$(($foo+1))
done