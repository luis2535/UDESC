%{
#include <math.h>
#include <string.h>
#include <stdlib.h>
#include <string.h>
#include "Compiler.h"

extern int count_label;

extern  int yylex();
//extern  int yyparse();
extern  FILE *yyin;

%}

%union {
	int ival;
	float fval;
	char cval;
	char *sval;
}

%token END 0 "end of file"
%token INT_TOKEN FLOAT_TOKEN
%token INTEGER_TYPE_TOKEN FLOAT_TYPE_TOKEN VOID_TYPE_TOKEN BOOL_TYPE_TOKEN STRING_TYPE_TOKEN
%token TRUE_TOKEN FALSE_TOKEN
%token IF_TOKEN ELSE_TOKEN WHILE_TOKEN RETURN_TOKEN
%token ADD_TOKEN SUB_TOKEN
%token MULT_TOKEN DIV_TOKEN
%token BIGGER_TOKEN SMALLER_EQUAL_TOKEN BIGGER_EQUAL_TOKEN SMALLER_TOKEN COMPARE_TOKEN DIFF_TOKEN
%token EQUAL_TOKEN
%token CHAR_TOKEN
%token ID_TOKEN
%token OPEN_BRACKET_TOKEN CLOSE_BRACKET_TOKEN
%token OPEN_PARENTHESES_TOKEN CLOSE_PARENTHESES_TOKEN
%token OPEN_KEYS_TOKEN CLOSE_KEYS_TOKEN
%token COMMA_TOKEN
%token SEMICOLON_TOKEN
%token SYMBOLS_TOKEN

%type<fval> FLOAT_TOKEN
%type<ival>
%type<cval>
%type<sval>

%start programa

%%

programa: declaracao_lista
		;
declaracao_lista: declaracao_lista declaracao
				| declaracao
				;
declaracao: var_declaracao
		  | fun_declaracao
		  ;
var_declaracao: tipo_especificador ID_TOKEN SEMICOLON_TOKEN
			  | tipo_especificador ID_TOKEN OPEN_BRACKET_TOKEN INT_TOKEN CLOSE_BRACKET_TOKEN
			  ;
tipo_especificador: INTEGER_TYPE_TOKEN
				  | VOID_TYPE_TOKEN
				  ;
fun_declaracao: tipo_especificador ID_TOKEN OPEN_PARENTHESES_TOKEN params CLOSE_PARENTHESES_TOKEN composto_decl
			  ;
params: param_lista
	  |
	  ;
param_lista: param_lista COMMA_TOKEN param
		   | param
		   ;
param: tipo_especificador ID_TOKEN
	 | tipo_especificador ID_TOKEN OPEN_BRACKET_TOKEN CLOSE_BRACKET_TOKEN
	 ;
composto_decl: OPEN_KEYS_TOKEN local_declaracoes statement_lista CLOSE_KEYS_TOKEN
			;
local_declaracoes: local_declaracoes var_declaracao
				 | 
				 ;
statement_lista: statement_lista statement
				|
				;
statement: expressao_decl
		 | composto_decl
		 | selecao_decl
		 | iteracao_decl
		 | retorno_decl
		 ;
expressao_decl: expressao SEMICOLON_TOKEN
			  | SEMICOLON_TOKEN
			  ;
selecao_decl: IF_TOKEN OPEN_PARENTHESES_TOKEN expressao CLOSE_PARENTHESES_TOKEN statement
			| IF_TOKEN OPEN_PARENTHESES_TOKEN expressao CLOSE_PARENTHESES_TOKEN statement ELSE_TOKEN statement
			;
iteracao_decl: WHILE_TOKEN expressao statement
			 ;
retorno_decl: RETURN_TOKEN SEMICOLON_TOKEN
			| RETURN_TOKEN expressao
			;
expressao: var EQUAL_TOKEN expressao
		 | simples_expressao
		 ;
var: ID_TOKEN
	| ID_TOKEN OPEN_BRACKET_TOKEN expressao CLOSE_BRACKET_TOKEN
	;
simples_expressao: soma_expressao relacional soma_expressao
				 | soma_expressao
				 ;
relacional: SMALLER_EQUAL_TOKEN
		  | SMALLER_TOKEN
		  | BIGGER_EQUAL_TOKEN
		  | BIGGER_TOKEN
		  | COMPARE_TOKEN
		  | DIFF_TOKEN
		  ;
soma_expressao: soma_expressao soma termo
			  | termo
			  ;
soma: ADD_TOKEN
	| SUB_TOKEN
	;
termo: termo mult fator
	 | fator
	 ;
mult: MULT_TOKEN
	| DIV_TOKEN
	;
fator: OPEN_PARENTHESES_TOKEN expressao CLOSE_PARENTHESES_TOKEN
	 | var
	 | ativacao
	 | INT_TOKEN
	 ;
ativacao: ID OPEN_PARENTHESES_TOKEN args CLOSE_PARENTHESES_TOKEN
		;
args: arg_lista
	|
	;
arg_lista: arg_lista COMMA_TOKEN expressao
		 | expressao
		 ;

%%
