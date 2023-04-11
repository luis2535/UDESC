flex trab2.lex
bison -d trab2.y
gcc trab2.tab.c lex.yy.c Compiler.c -o trab2 -lm
type teste.txt | .\trab2.exe
java -jar C:\Users\bruni\Downloads\jasmin-2.4\jasmin.jar output.j
java test