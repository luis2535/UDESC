/* Linguagem: Pascal-like */

/* ========================================================================== */
/* Abaixo, indicado pelos limitadores "%{" e "%}", as includes necessárias... */
/* ========================================================================== */
%option noyywrap
%{
/* Para as funções atoi() e atof() */
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "compiler.h"
#include "trab2.tab.h"

extern int num_col;
extern int num_lin;

%}

/* ========================================================================== */
/* ===========================  Sessão DEFINIÇÔES  ========================== */
/* ========================================================================== */

NUM [0-9]
ID	([a-z_]*[A-Z_]*)([a-z0-9_]*[A-Z0-9_]*)
SOMA "+"|"-"
MULT  "*"|"/"
RELACIONAL "<"|">"|"<="|">="|"!="|"=="
TIPO "int"|"void"
KEYWORD "if"|"else"|"while"|"return"
ATRIBUICAO "="
COLCHETES "["|"]"
PARENTESES "("|")"
CHAVE "{"|"}"
VIRGULA ","
PONTO-E-VIRGULA ";"
PRINTF "printf"


%%

{NUM}+ { 
	addTabela(yytext, "number-int", num_lin, num_col);
	return INT_TOKEN;
}
{RELACIONAL} {
	addTabela(yytext, "operador-rel", num_lin, num_col);
    if(strcmp(yytext, "<") == 0) return SMALLER_TOKEN;
    else if(strcmp(yytext, ">") == 0) return BIGGER_TOKEN;
    else if(strcmp(yytext, "<=") == 0) return SMALLER_EQUAL_TOKEN;
    else if(strcmp(yytext, ">=") == 0) return BIGGER_EQUAL_TOKEN;
    else if(strcmp(yytext, "!=") == 0) return DIFF_TOKEN;
    else if(strcmp(yytext, "==") == 0) return COMPARE_TOKEN;
}
{SOMA} {
	addTabela(yytext, "operador-soma", num_lin, num_col);
    if(strcmp(yytext, "+") == 0) return ADD_TOKEN;
    else if(strcmp(yytext, "-") == 0) return SUB_TOKEN;
}
{COLCHETES} {
	addTabela(yytext, "colchetes", num_lin, num_col);
	if(strcmp(yytext, "[") == 0) return OPEN_BRACKET_TOKEN;
    else if(strcmp(yytext, "]") == 0) return CLOSE_BRACKET_TOKEN;
}
{CHAVE} {
	addTabela(yytext, "chaves", num_lin, num_col);
    if(strcmp(yytext, "{") == 0) return OPEN_KEYS_TOKEN;
    else if(strcmp(yytext, "}") == 0) return CLOSE_KEYS_TOKEN;
}
{PONTO-E-VIRGULA} {
	addTabela(yytext, "ponto-virgula", num_lin, num_col);
    if(strcmp(yytext, ";") == 0) return SEMICOLON_TOKEN;	
}
{KEYWORD} {
	addTabela(yytext, "keyword", num_lin, num_col);
    if(strcmp(yytext, "if") == 0) return IF_TOKEN;
    else if(strcmp(yytext, "else") == 0) return ELSE_TOKEN;
    else if(strcmp(yytext, "while") == 0) return WHILE_TOKEN;
    else if(strcmp(yytext, "return") == 0) return RETURN_TOKEN;
}
{PARENTESES} {
	addTabela(yytext, "parenteses", num_lin, num_col);
    if(strcmp(yytext, "(") == 0) return OPEN_PARENTHESES_TOKEN;
    else if(strcmp(yytext, ")") == 0) return CLOSE_PARENTHESES_TOKEN;
}
{TIPO} {
	addTabela(yytext, "tipo", num_lin, num_col);
    if(strcmp(yytext, "int") == 0) return INT_TYPE_TOKEN;
    else if(strcmp(yytext, "void") == 0) return VOID_TYPE_TOKEN;
}
{MULT} {
	addTabela(yytext, "operador-mult", num_lin, num_col);
    if(strcmp(yytext, "*") == 0) return MULT_TOKEN;
    else if(strcmp(yytext, "/") == 0) return DIV_TOKEN;
}
{VIRGULA} {
	addTabela(yytext, "virgula", num_lin, num_col);
	if(strcmp(yytext, ",") == 0) return COMMA_TOKEN;
}
{ATRIBUICAO} {
	addTabela(yytext, "operador-atrib", num_lin, num_col);
    if(strcmp(yytext, "=") == 0) return EQUAL_TOKEN;
}
{ID} {
	addTabela(yytext, "identificador", num_lin, num_col);
    return ID_TOKEN;
}

[ \t] {
	num_col++;
}
\n {
	num_lin++;
	num_col = 0;
}
. {
	addTabela(yytext, "nao-reconhecido", num_lin, num_col);
}

%%

/*int main( argc, argv )
int argc;
char **argv;
{
	++argv, --argc;
	if ( argc > 0 )
		yyin = fopen( argv[0], "r" );
	else
		yyin = stdin;

	yylex();

    TabelaToken();

	return 0;
}*/