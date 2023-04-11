/* Linguagem: Pascal-like */

/* ========================================================================== */
/* Abaixo, indicado pelos limitadores "%{" e "%}", as includes necessárias... */
/* ========================================================================== */

%{
/* Para as funções atoi() e atof() */
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int num_lin = 0;
int num_col = 0;
int cont_tokens = 0;

typedef struct Token{
    char conteudo[100];
	char tipo[50];
    int linha;
    int coluna;
	struct Token *proximo;
	struct Token *anterior;
}Token;

Token *tabela;

void inserirToken(Token **tabela, char* conteudo, char* tipo, int linha, int coluna){
	cont_tokens++;
	Token *aux = (Token*)malloc(sizeof(Token));
	Token *novoToken = (Token*)malloc(sizeof(Token));

	strcpy(novoToken->conteudo,conteudo);
	strcpy(novoToken->tipo,tipo);
	novoToken->linha = linha;
	novoToken->coluna = coluna;
	novoToken->proximo = NULL;

	if(*tabela == NULL){
		*tabela = novoToken;
		novoToken->anterior = NULL;
	}
	else{
		aux = *tabela;
		while(aux->proximo){
			aux = aux->proximo;
		}
		aux->proximo = novoToken;
		novoToken->anterior = aux;
	}
	
}

%}

/* ========================================================================== */
/* ===========================  Sessão DEFINIÇÔES  ========================== */
/* ========================================================================== */

NUM [0-9]
ID	([a-z_]*[A-Z_]*)([a-z0-9_]*[A-Z0-9_]*)
VAZIO " "
TIPO-ESPECIFICADOR "int"|"void"|"float"|"bool"|"string"
VALOR-BOOL "false"|"true"
KEYWORD "if"|"else"|"while"|"return"
SOMA "+"|"-"
MULT  "*"|"/"
RELACIONAL "<"|">"|"<="|">="|"!="|"=="
ATRIBUICAO "="
PRINTF "printf"
STRING ["]([a-z]*[ ]*[A-Z]*)*["]
COLCHETES "["|"]"
PARENTESES "("|")"
CHAVE "{"|"}"
VIRGULA ","
PONTO-E-VIRGULA ";"
SIMBOLOS "!"|"@"|"#"|"$"|"%"|"&"|"."|":"

%%

{NUM}+ { 
	//printf( "Um valor inteiro: %s (%d)\n", yytext, atoi( yytext ) );
	inserirToken(&tabela, yytext, "number-int", num_lin, num_col);
	num_col += strlen(yytext); 
	}

{NUM}+.{NUM}+ { 
	//printf( "Um valor flutuante: %s (%f)\n", yytext, atof( yytext ) );
	inserirToken(&tabela, yytext, "number-float", num_lin, num_col);
	num_col += strlen(yytext); 
	}

{TIPO-ESPECIFICADOR} {
	//printf( "Um tipo especificador: %s\n", yytext);
	inserirToken(&tabela, yytext, "tipo", num_lin, num_col);
	num_col += strlen(yytext);
}
{VALOR-BOOL} {
	//printf( "Um tipo valor booleano: %s\n", yytext);
	inserirToken(&tabela, yytext, "valor-bool", num_lin, num_col);
	num_col += strlen(yytext);
}
{KEYWORD} {
	//printf( "Uma palavra-chave: %s\n", yytext);
	inserirToken(&tabela, yytext, "keyword", num_lin, num_col);
	num_col += strlen(yytext);
}
{SOMA} {
	//printf( "Um operador de soma: %s\n", yytext );
	inserirToken(&tabela, yytext, "operador-soma", num_lin, num_col);
	num_col += strlen(yytext);
}
{MULT} {
	//printf( "Um operador de multiplicação: %s\n", yytext );
	inserirToken(&tabela, yytext, "operador-mult", num_lin, num_col);
	num_col += strlen(yytext);
} 
{RELACIONAL} {
	//printf( "Um operador relacional: %s\n", yytext );
	inserirToken(&tabela, yytext, "operador-rel", num_lin, num_col);
	num_col += strlen(yytext);
}
{ATRIBUICAO} {
	//printf( "Um operador de atribuição: %s\n", yytext );
	inserirToken(&tabela, yytext, "operador-atrib", num_lin, num_col);
	num_col += strlen(yytext);
}
{PRINTF} {
	//printf( "Um função para imprimir na tela: %s\n", yytext );
	inserirToken(&tabela, yytext, "//printf", num_lin, num_col);
	num_col += strlen(yytext);
}
{STRING} {
	//printf( "Uma string: %s\n", yytext );
	inserirToken(&tabela, yytext, "string", num_lin, num_col);
	num_col += strlen(yytext);
}
{ID} {
	//printf( "Um identificador: %s\n", yytext );
	inserirToken(&tabela, yytext, "identificador", num_lin, num_col);
	num_col += strlen(yytext);
}
{COLCHETES} {
	//printf( "Um colchete: %s\n", yytext );
	inserirToken(&tabela, yytext, "colchetes", num_lin, num_col);
	num_col += strlen(yytext);
}
{PARENTESES} {
	//printf( "Um parenteses: %s\n", yytext );
	inserirToken(&tabela, yytext, "parenteses", num_lin, num_col);
	num_col += strlen(yytext);
}
{CHAVE} {
	//printf( "Uma chave: %s\n", yytext );
	inserirToken(&tabela, yytext, "chaves", num_lin, num_col);
	num_col += strlen(yytext);
}
{VIRGULA} {
	//printf( "Uma virgula: %s\n", yytext );
	inserirToken(&tabela, yytext, "virgula", num_lin, num_col);
	num_col += strlen(yytext);
}
{PONTO-E-VIRGULA} {
	//printf( "Um ponto e virgula delimitador: %s\n", yytext );
	inserirToken(&tabela, yytext, "ponto-virgula", num_lin, num_col);
	num_col += strlen(yytext);
}
{SIMBOLOS} {
	//printf( "Um simbolo: %s\n", yytext );
	inserirToken(&tabela, yytext, "simbolo", num_lin, num_col);
	num_col += strlen(yytext);
}

[ \t] {
	num_col++;
}

\n {
	num_lin++;
	num_col = 0;
}



.  {
	//printf( "Caracter não reconhecido: %s\n", yytext );
	inserirToken(&tabela, yytext, "nao-reconhecido", num_lin, num_col);
	num_col += strlen(yytext);
}

%%

int main( argc, argv )
int argc;
char **argv;
{
	++argv, --argc;
	if ( argc > 0 )
		yyin = fopen( argv[0], "r" );
	else
		yyin = stdin;

	yylex();

	// Imprimir tabela Token

	Token *aux;
	aux = (Token *)malloc(sizeof(Token));
	aux = tabela;

	FILE *filePointer;
    filePointer = fopen("Tabela_simbolos.txt", "w+");
	fprintf(filePointer,"---------------------------------------------------------------------------------\n");
	fprintf(filePointer,"                               Tabela de Simbolos                              \n");
	fprintf(filePointer,"---------------------------------------------------------------------------------\n");
	fprintf(filePointer, "\t\tToken\t\t|\t\tTipo\t\t|\t\tLinha\t\t|\t\tColuna\t\t\n");
	for(int i = 0; i < cont_tokens; i++){
		fprintf(filePointer, "\t%12s\t|\t%12s\t|\t%12i\t|\t%12i\t\n", aux->conteudo, aux->tipo, aux->linha, aux->coluna);
		aux = aux->proximo;
	}
	fprintf(filePointer,"---------------------------------------------------------------------------------\n");
	

	return 0;
}