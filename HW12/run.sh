#! /bin/bash

psql -d main < ../HW09/sql/migrate.sql
mvn clean install exec:java
