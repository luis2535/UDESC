#!/bin/bash
flex trab2.lex
bison -d trab2.y
gcc trab2.tab.c lex.yy.c compiler.c -o trab2 -lm
