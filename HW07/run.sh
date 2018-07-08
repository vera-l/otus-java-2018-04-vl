#! /bin/bash

cd ../HW06 && mvn clean install && cd ../HW07
mvn clean compile exec:java test
