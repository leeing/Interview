#!/bin/bash
# 
# File:   HelloWorld.bash
# Author: leeing
#
# Created on Apr 9, 2011, 6:41:36 PM
#

echo "Files of the directory is :"

ls ${pwd}
for file in *
do
    echo $file
    if grep -l a $file
    then
        more $file
    fi
done
echo end here