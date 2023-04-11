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
    int localizacao;
	struct Token *proximo;
	struct Token *anterior;
}Token;

void retorno();
void TabelaToken();
void inserirToken(Token **tabela, char* conteudo, char* tipo, int linha, int coluna);
void generateHeader();
void generateFooter();
void defineVar(char* name, int type);
void onlyLabel(int label);
void onlyLabelForIf(int label);
void onlyGoTo(int label);
void putOpInStack(char op);
void loadVariableValue(int localizacao);
void ifStackInverse(char *op);
void writeCode(char *code);
void atributeVariable(char *id);
int findLocalizacao(char *string);
void setLocalizacao(char *string, int local);
void addTabela(char* conteudo, char* tipo, int linha, int coluna);
Token *createTabela(char* conteudo, char* tipo, int linha, int coluna);
Token *createToken(char* conteudo, char* tipo, int linha, int coluna);
void instanciandoValor(int valor);