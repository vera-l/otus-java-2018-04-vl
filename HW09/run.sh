#! /bin/bash

psql -d main < sql/migrate.sql
mvn clean compile exec:java test
