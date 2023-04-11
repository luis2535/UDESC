%{

#include <math.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "Compiler.h"
typedef enum {INT_T, VOID_T} type_enum;
extern int count_label;

void yyerror(const char* s);
%}

%union {
	int ival;
	float fval;
	char cval;
	char *sval;
}

/* Declaração dos tokens... */

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
%token PRINT_TOKEN


%type<ival> INT_TOKEN
%type<sval> ID_TOKEN ADD_TOKEN SUB_TOKEN SMALLER_EQUAL_TOKEN MULT_TOKEN DIFF_TOKEN DIV_TOKEN SMALLER_TOKEN BIGGER_EQUAL_TOKEN BIGGER_TOKEN COMPARE_TOKEN EQUAL_TOKEN var-declaracao tipo-especificador selecao-decl iteracao-decl expressao var simples-expressao relacional soma-expressao soma termo mult fator OPEN_PARENTHESES_TOKEN ativacao
%start programa

%%

programa:	{generateHeader();}	declaracao-lista {generateFooter();} // ADICIONAR GENERATE HEADER
	;

declaracao-lista:	declaracao-lista declaracao
	|		declaracao
	;

declaracao:		var-declaracao 
	|		fun-declaracao
	;

var-declaracao:		tipo-especificador ID_TOKEN SEMICOLON_TOKEN{
	string str($2);
	if($1 == INT_T){
		defineVar(str, INT_T);
	}else if($1 == VOID_T){
		defineVar(str, VOID_T);
	}
}
	|		tipo-especificador ID_TOKEN OPEN_BRACKET_TOKEN INT_TOKEN CLOSE_BRACKET_TOKEN SEMICOLON_TOKEN
	;

tipo-especificador:	INTEGER_TYPE_TOKEN {$$ = INT_T;}
	|		VOID_TYPE_TOKEN {$$ = VOID_T;}
	;

fun-declaracao:		tipo-especificador ID_TOKEN OPEN_PARENTHESES_TOKEN params CLOSE_PARENTHESES_TOKEN composto-decl
	;

params:			param-lista
	|		vazio
	;

param-lista:		param-lista COMMA_TOKEN param
	|		param
	;

param:			tipo-especificador ID_TOKEN
	|		tipo-especificador ID_TOKEN OPEN_BRACKET_TOKEN CLOSE_BRACKET_TOKEN
	;

composto-decl:		OPEN_KEYS_TOKEN local-declaracoes statement-lista CLOSE_KEYS_TOKEN
	;

local-declaracoes:	local-declaracoes var-declaracao
	|
	;

statement-lista:	statement-lista statement
	|
	;

statement:		expressao-decl
	|		composto-decl
	|		selecao-decl
	|		iteracao-decl
	|		retorno-decl
	|		printar
	;

expressao-decl:		expressao SEMICOLON_TOKEN
	|		SEMICOLON_TOKEN
	;

selecao-decl:		IF_TOKEN OPEN_PARENTHESES_TOKEN expressao CLOSE_PARENTHESES_TOKEN {$1 = count_label; onlyLabelForIf($1); count_label+=2;} statement {onlyGoTo($1 + 1); onlyLabel($1);}
	|		IF_TOKEN OPEN_PARENTHESES_TOKEN expressao CLOSE_PARENTHESES_TOKEN {$1 = count_label; onlyLabelForIf($1); count_label+=2;} statement {onlyGoTo($1 + 1); onlyLabel($1);} ELSE_TOKEN statement {onlyLabel($1 + 1);}
	;

iteracao-decl:		WHILE_TOKEN {$1 = count_label; onlyLabel($1); count_label+=2;} OPEN_PARENTHESES_TOKEN expressao CLOSE_PARENTHESES_TOKEN {onlyLabelForIf($1 + 1);} statement {onlyGoTo($1); onlyLabel($1 + 1);}
	;

retorno-decl:		RETURN_TOKEN SEMICOLON_TOKEN
	|		RETURN_TOKEN expressao SEMICOLON_TOKEN
	;

expressao:		var EQUAL_TOKEN expressao {atributeVariable($1);}
	|		simples-expressao
	;

var:			ID_TOKEN { $$ = strdup($1);}
	|		ID_TOKEN OPEN_BRACKET_TOKEN expressao CLOSE_BRACKET_TOKEN
	;

simples-expressao:	soma-expressao relacional soma-expressao { putOpInStack('-'); ifStackInverse($2);}
	|		soma-expressao
	;

relacional:		SMALLER_EQUAL_TOKEN {$$ = strdup($1);}
	|		SMALLER_TOKEN {$$ = strdup($1);}
	|		BIGGER_TOKEN {$$ = strdup($1);}
	|		BIGGER_EQUAL_TOKEN {$$ = strdup($1);}
	|		COMPARE_TOKEN {$$ = strdup($1);}
	|		DIFF_TOKEN {$$ = strdup($1);}
	;

soma-expressao:		soma-expressao soma termo {putOpInStack($2);}
	|		termo
	;

soma:			ADD_TOKEN {$$ = strdup($1);}
	|		SUB_TOKEN {$$ = strdup($1);}
	;

termo:			termo mult fator {putOpInStack($2);}
	|		fator
	;
	
mult:			MULT_TOKEN {$$ = strdup($1);}
	|		DIV_TOKEN {$$ = strdup($1);}
	;

fator:			OPEN_PARENTHESES_TOKEN expressao CLOSE_PARENTHESES_TOKEN
	|		var {loadVariableValue(findLocalizacao($1));}
	|		ativacao
	|		INT_TOKEN {instanciandoValor($1);}
	;

ativacao:		ID_TOKEN OPEN_PARENTHESES_TOKEN args CLOSE_PARENTHESES_TOKEN
	;

args:			arg-lista
	|
	;

arg-lista:		arg-lista COMMA_TOKEN expressao
	|		expressao
	;
vazio:
	;
printar: PRINT_TOKEN OPEN_PARENTHESES_TOKEN {writeCode("getstatic java/lang/System/out Ljava/io/PrintStream;\n");} simples-expressao CLOSE_PARENTHESES_TOKEN {writeCode("invokevirtual java/io/PrintStream/println(I)V\n");} SEMICOLON_TOKEN
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