#! /bin/bash

psql -d main < sql/migrate.sql
mvn clean install exec:java
