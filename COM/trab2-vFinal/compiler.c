#include "compiler.h"

int num_lin = 0;
int num_col = 0;
int cont_tokens = 0;
int count_label = 0;
Token *tabela = NULL;
FILE *f;
typedef enum {INT_T, VOID_T} type_enum;
int varaiblesNum = 3; 


void retorno(){
    printf("%d", cont_tokens);
}

void TabelaToken(){
Token *aux;
	aux = (Token *)malloc(sizeof(Token));
	aux = tabela;

	FILE *filePointer;
    filePointer = fopen("Tabela_simbolos.txt", "w+");
	fprintf(filePointer,"---------------------------------------------------------------------------------\n");
	fprintf(filePointer,"                               Tabela de Simbolos                              \n");
	fprintf(filePointer,"---------------------------------------------------------------------------------\n");
	fprintf(filePointer, "\t\tToken\t\t|\t\tTipo\t\t|\t\tLinha\t\t|\t\tColuna\t\t|\n");
	for(int i = 0; i < cont_tokens; i++){
		fprintf(filePointer, "\t%12s\t|\t%12s\t|\t%12i\t|\t%12i\t\n", aux->conteudo, aux->tipo, aux->linha, aux->coluna);
		aux = aux->proximo;
	}
	fprintf(filePointer,"---------------------------------------------------------------------------------\n");
	

}
void addTabela(char* conteudo, char* tipo, int linha, int coluna){
	if(tabela == NULL)
		tabela = createTabela(conteudo,tipo,linha,coluna);
	else{
		Token *aux = tabela;
		while(aux->proximo != NULL){
			aux = aux->proximo;
		}
		Token *novoToken;
		novoToken = createToken(conteudo, tipo, linha, coluna);
		aux->proximo = novoToken;
		novoToken->anterior = aux;
	}
	num_col += strlen(conteudo);
}

Token *createTabela(char* conteudo, char* tipo, int linha, int coluna){
	cont_tokens++;
	Token *novaTabela;
	novaTabela = (Token *) malloc(sizeof(Token));
	strcpy(novaTabela->conteudo,conteudo);
	strcpy(novaTabela->tipo,tipo);
	novaTabela->linha = linha;
	novaTabela->coluna = coluna;
	novaTabela->proximo = NULL;
	novaTabela->anterior = NULL;
	
}

Token *createToken(char* conteudo, char* tipo, int linha, int coluna){
	cont_tokens++;
	Token *novoToken = (Token*)malloc(sizeof(Token));

	strcpy(novoToken->conteudo,conteudo);
	strcpy(novoToken->tipo,tipo);
	novoToken->linha = linha;
	novoToken->coluna = coluna;
	novoToken->proximo = NULL;
	

}

void generateHeader(){
	f = fopen("output.j", "w+");
	fprintf(f, ".source teste.txt\n.class public test\n.super java/lang/Object\n");
	fprintf(f, ".method public <init>()V\n");
	fprintf(f, "aload_0\n");
	fprintf(f, "invokenonvirtual java/lang/Object/<init>()V\n");
	fprintf(f, "return\n");
	fprintf(f, ".end method\n\n");
	fprintf(f, ".method public static main([Ljava/lang/String;)V\n");
	fprintf(f, ".limit locals 100\n");
	fprintf(f, ".limit stack 100\n");
}
void generateFooter(){
	fprintf(f, "return\n");
	fprintf(f, ".end method");
}

void funcReturn(){
	fprintf(f, "ireturn\n");
}

void defineVar(char *name, int type)
{
	Token *aux;
	{
		if(type == INT_T)
		{
			fprintf(f, "iconst_0\nistore %i\n", varaiblesNum);
		}
		else if ( type == VOID_T)
		{
			fprintf(f, "vconst_0\nvstore %i\n", varaiblesNum);
		}
	}
	varaiblesNum++;
}

void atributeVariable(char *id){
	fprintf(f,"istore %i\n", varaiblesNum);
}

void funcSum(char op){
	if(op == '+') fprintf(f, "iadd\n");
	else if(op == '-') fprintf(f, "isub\n");
	else if(op == '*') fprintf(f, "imul\n");
	else fprintf(f, "idiv\n");
}

void instanciandoValor(int valor){
	fprintf(f, "ldc %d\n", valor);
}