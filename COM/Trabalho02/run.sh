#!/bin/bash
flex trab2.lex
bison -d trab2.y
gcc trab2.tab.c lex.yy.c Compiler.c -o trab2 -lm
type teste.txt | .\trab2.exe