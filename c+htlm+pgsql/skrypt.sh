#!/bin/bash
gcc -o app app.c -I /usr/include/postgresql/ -L/usr/local/pgsql/lib -lpq
./app
