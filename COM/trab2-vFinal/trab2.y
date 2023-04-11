%{

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "compiler.h"
typedef enum {INT_T, VOID_T} type_enum;

extern int yylex();
extern int yyparse();
extern FILE* yyin;


void yyerror(const char* s);
%}

%union {
	int ival;
	char* sval;
	char cval;
}

/* Declaração dos tokens... */

%token<sval> ID_TOKEN
%token<ival> INT_TOKEN
%token<ival> INT_TYPE_TOKEN VOID_TYPE_TOKEN
%token OPEN_BRACKET_TOKEN CLOSE_BRACKET_TOKEN
%token OPEN_KEYS_TOKEN CLOSE_KEYS_TOKEN
%token OPEN_PARENTHESES_TOKEN CLOSE_PARENTHESES_TOKEN
%token SEMICOLON_TOKEN
%token IF_TOKEN ELSE_TOKEN WHILE_TOKEN RETURN_TOKEN
%token COMMA_TOKEN
%token EQUAL_TOKEN
%token<sval> SMALLER_TOKEN BIGGER_TOKEN SMALLER_EQUAL_TOKEN BIGGER_EQUAL_TOKEN DIFF_TOKEN COMPARE_TOKEN
%token<cval> ADD_TOKEN SUB_TOKEN
%token<cval> MULT_TOKEN DIV_TOKEN

%type <sval> relacional var
%type <cval> soma
%type <cval> mult
%type <ival> tipo-especificador var-declaracao 

%start programa

%%
programa: {generateHeader();} declaracao-lista {generateFooter();}
;

declaracao-lista: declaracao-lista declaracao
	| 			  declaracao
	;

declaracao: var-declaracao
	|		fun-declaracao
	;

// DECLARACAO
var-declaracao:	tipo-especificador ID_TOKEN SEMICOLON_TOKEN{
		if($1 == INT_T){
			defineVar($2, INT_T);
		}else if($1 == VOID_T){
			defineVar($2, VOID_T);
		}
	}
	| 			tipo-especificador ID_TOKEN OPEN_BRACKET_TOKEN INT_TOKEN CLOSE_BRACKET_TOKEN SEMICOLON_TOKEN
	;

tipo-especificador:	INT_TYPE_TOKEN {$$ = INT_T;}
	|				VOID_TYPE_TOKEN {$$ = VOID_T;}
	;

fun-declaracao:	tipo-especificador ID_TOKEN OPEN_PARENTHESES_TOKEN params CLOSE_PARENTHESES_TOKEN composto-decl
	;

params:			param-lista
	|			
	;

param-lista: param-lista COMMA_TOKEN param
	|		 param
	;

param: tipo-especificador ID_TOKEN
	|  tipo-especificador ID_TOKEN OPEN_BRACKET_TOKEN CLOSE_BRACKET_TOKEN
	;

composto-decl: OPEN_KEYS_TOKEN local-declaracoes statement-lista CLOSE_KEYS_TOKEN
	;

local-declaracoes: local-declaracoes var-declaracao
	|
	;

statement-lista: statement-lista statement
	|
	;

statement: expressao-decl
	|	   composto-decl
	|	   selecao-decl
	|	   iteracao-decl
	|	   retorno-decl
	;

expressao-decl: expressao SEMICOLON_TOKEN
	|			SEMICOLON_TOKEN
	;

// IF
selecao-decl: IF_TOKEN OPEN_PARENTHESES_TOKEN expressao CLOSE_PARENTHESES_TOKEN statement
	|		  IF_TOKEN OPEN_PARENTHESES_TOKEN expressao CLOSE_PARENTHESES_TOKEN statement ELSE_TOKEN statement
	;

// WHILE
iteracao-decl: WHILE_TOKEN OPEN_PARENTHESES_TOKEN expressao CLOSE_PARENTHESES_TOKEN statement
	;

// ✓
// retorno-decl: RETURN_TOKEN SEMICOLON_TOKEN
// 	|		  RETURN_TOKEN expressao SEMICOLON_TOKEN
// 	;
retorno-decl: RETURN_TOKEN expressao SEMICOLON_TOKEN {funcReturn();}
	;

// EXPRESSAO
expressao: var EQUAL_TOKEN expressao {atributeVariable($1);}
	|	   simples-expressao
	;

// VAR
var: ID_TOKEN
	|ID_TOKEN OPEN_BRACKET_TOKEN expressao CLOSE_BRACKET_TOKEN
	;

// SIMPLES
simples-expressao: soma-expressao relacional soma-expressao
	|			   soma-expressao
	;

relacional: SMALLER_TOKEN {$$ = "<";}
	|		BIGGER_TOKEN {$$ = ">";}
	|		SMALLER_EQUAL_TOKEN {$$ = "<=";}
	|		BIGGER_EQUAL_TOKEN {$$ = ">=";}
	|		DIFF_TOKEN {$$ = "!=";}
	|		COMPARE_TOKEN {$$ = "==";}
	;

soma-expressao:	soma-expressao soma termo{funcSum($2);}
	|			termo
	;

soma: ADD_TOKEN {$$ = '+';}
	| SUB_TOKEN {$$ = '-';}
	;

termo: termo mult fator {funcSum($2);}
	|  fator
	;
	
mult: MULT_TOKEN {$$ = '*';}
	| DIV_TOKEN {$$ = '/';}
	;

fator: OPEN_PARENTHESES_TOKEN expressao CLOSE_PARENTHESES_TOKEN
	|	var
	|	ativacao
	|	INT_TOKEN {instanciandoValor((int)$1);}
	;

ativacao: ID_TOKEN OPEN_PARENTHESES_TOKEN args CLOSE_PARENTHESES_TOKEN
	;

args: arg-lista
	|
	;

arg-lista: arg-lista COMMA_TOKEN expressao
	|	   expressao
	;

%%

int main() {
	yyin = stdin;

	do {
		yyparse();
	} while(!feof(yyin));

	return 0;
}

void yyerror(const char* s) {
	fprintf(stderr, "Erro de análise (sintática): %s\n", s);
	exit(1);
}