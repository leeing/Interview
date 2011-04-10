#!/bin/bash
# 
# File:   newscript.bash
# Author: leeing
#
# Created on Apr 10, 2011, 8:00:50 PM
#


hello_world() {
    echo hello $1
    echo "hello world"
    return 0
}

hello_world leeing
echo $(hello_world)