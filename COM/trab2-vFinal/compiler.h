#include <math.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>



typedef struct Token{
    char conteudo[100];
	char tipo[50];
    int linha;
    int coluna;
	struct Token *proximo;
	struct Token *anterior;
}Token;

void retorno();
void TabelaToken();
void addTabela(char* conteudo, char* tipo, int linha, int coluna);
Token *createTabela(char* conteudo, char* tipo, int linha, int coluna);
Token *createToken(char* conteudo, char* tipo, int linha, int coluna);
void generateHeader();
void generateFooter();
void funcReturn();
void defineVar(char* name, int type);
void atributeVariable(char *id);
void funcSum(char op);
void instanciandoValor(int valor);