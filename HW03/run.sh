#! /bin/bash

path=ru/otus/HW03

clear

rm $path/*.class
javac $path/*.java
java $path/Main
