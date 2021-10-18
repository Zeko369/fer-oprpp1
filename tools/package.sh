#!/bin/sh
file_name="$1-$JMBAG"
rm $file_name.zip

mkdir $file_name
cp -r $1/src $1/pom.xml $file_name

zip -r $file_name.zip $file_name
rm -rf $file_name
