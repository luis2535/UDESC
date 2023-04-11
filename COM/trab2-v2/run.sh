#!/bin/bash
flex trab2.l
bison -d trab2.y
gcc trab2.tab.c lex.yy.c compiler.c -o trab2 -lm
./trab2 < teste.txt
