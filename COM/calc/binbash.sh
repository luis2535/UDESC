#!/bin/bash
bison -d calc.y
flex calc.lex
gcc calc.tab.c lex.yy.c -o calc -lm