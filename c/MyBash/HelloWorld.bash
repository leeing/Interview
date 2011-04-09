#!/bin/bash
# 
# File:   HelloWorld.bash
# Author: leeing
#
# Created on Apr 9, 2011, 6:41:36 PM
#

echo "the files are:"
for file in `ls ~/source/bash/*.txt`
do
    if [ -f $file ]
    then
         echo $file
    fi
done

for file in $(ls ~/source/bash/*.txt)
do
    if [ -f $file ]
    then
         echo $file
    fi
done

echo end here