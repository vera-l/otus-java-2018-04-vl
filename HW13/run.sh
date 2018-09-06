#! /bin/bash

JETTY_HOME=$HOME/dev/jetty

psql -d main < ../HW09/sql/migrate.sql
mvn clean package
cp ./target/HW13.war $JETTY_HOME/webapps/root.war

$JETTY_HOME/bin/jetty.sh run &

open 'http://localhost:8080'
