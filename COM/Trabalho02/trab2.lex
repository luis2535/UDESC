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
#include "Compiler.h"
#include "trab2.tab.h"

extern int num_col;
extern int num_lin;

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
	addTabela(yytext, "number-int", num_lin, num_col);
	//inserirToken(&tabela, yytext, "number-int", num_lin, num_col);
    return INT_TOKEN;
	}

{NUM}+.{NUM}+ { 
	//printf( "Um valor flutuante: %s (%f)\n", yytext, atof( yytext ) );
	addTabela(yytext, "number-float", num_lin, num_col);
	//inserirToken(&tabela, yytext, "number-float", num_lin, num_col);
    return FLOAT_TOKEN;
	}

{TIPO-ESPECIFICADOR} {
	//printf( "Um tipo especificador: %s\n", yytext);
	addTabela(yytext, "tipo", num_lin, num_col);
	//inserirToken(&tabela, yytext, "tipo", num_lin, num_col);
    if(strcmp(yytext, "int") == 0) return INTEGER_TYPE_TOKEN;
    else if(strcmp(yytext, "void") == 0) return VOID_TYPE_TOKEN;
    else if(strcmp(yytext, "float") == 0) return FLOAT_TYPE_TOKEN;
    else if(strcmp(yytext, "bool") == 0) return BOOL_TYPE_TOKEN;
    else if(strcmp(yytext, "string") == 0) return STRING_TYPE_TOKEN;
}
{VALOR-BOOL} {
	//printf( "Um tipo valor booleano: %s\n", yytext);
	addTabela(yytext, "valor-bool", num_lin, num_col);
	//inserirToken(&tabela, yytext, "valor-bool", num_lin, num_col);
    if(strcmp(yytext, "true") == 0) return TRUE_TOKEN;
    else if(strcmp(yytext, "false") == 0) return FALSE_TOKEN;
}
{KEYWORD} {
	//printf( "Uma palavra-chave: %s\n", yytext);
	addTabela(yytext, "keyword", num_lin, num_col);
	//inserirToken(&tabela, yytext, "keyword", num_lin, num_col);
    if(strcmp(yytext, "if") == 0) return IF_TOKEN;
    else if(strcmp(yytext, "else") == 0) return ELSE_TOKEN;
    else if(strcmp(yytext, "while") == 0) return WHILE_TOKEN;
    else if(strcmp(yytext, "return") == 0) return RETURN_TOKEN;
	
}
{SOMA} {
	//printf( "Um operador de soma: %s\n", yytext );
	addTabela(yytext, "operador-soma", num_lin, num_col);
	//inserirToken(&tabela, yytext, "operador-soma", num_lin, num_col);
    if(strcmp(yytext, "+") == 0) return ADD_TOKEN;
    else if(strcmp(yytext, "-") == 0) return SUB_TOKEN;
}
{MULT} {
	//printf( "Um operador de multiplicação: %s\n", yytext );
	addTabela(yytext, "operador-mult", num_lin, num_col);
	//inserirToken(&tabela, yytext, "operador-mult", num_lin, num_col);
    if(strcmp(yytext, "*") == 0) return MULT_TOKEN;
    else if(strcmp(yytext, "/") == 0) return DIV_TOKEN;
} 
{RELACIONAL} {
	//printf( "Um operador relacional: %s\n", yytext );
	addTabela(yytext, "operador-rel", num_lin, num_col);
	//inserirToken(&tabela, yytext, "operador-rel", num_lin, num_col);
    if(strcmp(yytext, "<") == 0) return SMALLER_TOKEN;
    else if(strcmp(yytext, ">") == 0) return BIGGER_TOKEN;
    else if(strcmp(yytext, "<=") == 0) return SMALLER_EQUAL_TOKEN;
    else if(strcmp(yytext, ">=") == 0) return BIGGER_EQUAL_TOKEN;
    else if(strcmp(yytext, "!=") == 0) return COMPARE_TOKEN;
    else if(strcmp(yytext, "==") == 0) return DIFF_TOKEN;
 
}
{ATRIBUICAO} {
	//printf( "Um operador de atribuição: %s\n", yytext );
	addTabela(yytext, "operador-atrib", num_lin, num_col);
	//inserirToken(&tabela, yytext, "operador-atrib", num_lin, num_col);
    if(strcmp(yytext, "=") == 0) return EQUAL_TOKEN;

}
{PRINTF} {
	//printf( "Um função para imprimir na tela: %s\n", yytext );
	addTabela(yytext, "printf", num_lin, num_col);
	//inserirToken(&tabela, yytext, "//printf", num_lin, num_col);
    if(strcmp(yytext, "print") == 0) return PRINT_TOKEN;
}
{STRING} {
	//printf( "Uma string: %s\n", yytext );
	addTabela(yytext, "string", num_lin, num_col);
	//inserirToken(&tabela, yytext, "string", num_lin, num_col);
    return CHAR_TOKEN;

}
{ID} {
	//printf( "Um identificador: %s\n", yytext );
	addTabela(yytext, "identificador", num_lin, num_col);
	//inserirToken(&tabela, yytext, "identificador", num_lin, num_col);
    return ID_TOKEN;

}
{COLCHETES} {
	//printf( "Um colchete: %s\n", yytext );
	addTabela(yytext, "colchetes", num_lin, num_col);
	//inserirToken(&tabela, yytext, "colchetes", num_lin, num_col);
	if(strcmp(yytext, "[") == 0) return OPEN_BRACKET_TOKEN;
    else if(strcmp(yytext, "]") == 0) return CLOSE_BRACKET_TOKEN;

}
{PARENTESES} {
	//printf( "Um parenteses: %s\n", yytext );
	addTabela(yytext, "parenteses", num_lin, num_col);
	//inserirToken(&tabela, yytext, "parenteses", num_lin, num_col);
    if(strcmp(yytext, "(") == 0) return OPEN_PARENTHESES_TOKEN;
    else if(strcmp(yytext, ")") == 0) return CLOSE_PARENTHESES_TOKEN;

}
{CHAVE} {
	//printf( "Uma chave: %s\n", yytext );
	addTabela(yytext, "chaves", num_lin, num_col);
	//inserirToken(&tabela, yytext, "chaves", num_lin, num_col);
    if(strcmp(yytext, "{") == 0) return OPEN_KEYS_TOKEN;
    else if(strcmp(yytext, "}") == 0) return CLOSE_KEYS_TOKEN;

}
{VIRGULA} {
	//printf( "Uma virgula: %s\n", yytext );
	addTabela(yytext, "virgula", num_lin, num_col);
	//inserirToken(&tabela, yytext, "virgula", num_lin, num_col);
	if(strcmp(yytext, ",") == 0) return COMMA_TOKEN;
}
{PONTO-E-VIRGULA} {
	//printf( "Um ponto e virgula delimitador: %s\n", yytext );
	addTabela(yytext, "ponto-virgula", num_lin, num_col);
	//inserirToken(&tabela, yytext, "ponto-virgula", num_lin, num_col);
    if(strcmp(yytext, ";") == 0) return SEMICOLON_TOKEN;
	
}
{SIMBOLOS} {
	//printf( "Um simbolo: %s\n", yytext );
	addTabela(yytext, "simbolo", num_lin, num_col);
	//inserirToken(&tabela, yytext, "simbolo", num_lin, num_col);
	return SYMBOLS_TOKEN;
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
	addTabela(yytext, "nao-reconhecido", num_lin, num_col);
	//inserirToken(&tabela, yytext, "nao-reconhecido", num_lin, num_col);
	
}

%%

int main(){
	TabelaToken();
}
