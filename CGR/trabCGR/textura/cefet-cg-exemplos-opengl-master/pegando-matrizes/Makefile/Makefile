CC=gcc
OPCOES_COMPILADOR=-I. -lglut -lGLU -lGLEW -lGL
TODOS_ARQUIVOS_PONTOH =
TODOS_ARQUIVOS_OBJ = main.o imprime-matriz.o

%.o: ../%.c $(TODOS_ARQUIVOS_PONTOH)
	$(CC) -o $@ -c $< $(OPCOES_COMPILADOR)

all: $(TODOS_ARQUIVOS_OBJ)
	gcc -o main $^ $(OPCOES_COMPILADOR)

run: all
	./main

clean:
	rm *.o main
