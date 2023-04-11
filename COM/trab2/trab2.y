%{

#include <stdio.h>
#include <stdlib.h>

extern int yylex();
extern int yyparse();
extern FILE* yyin;

void yyerror(const char* s);
%}

%union {
	int ival;
	float fval;
}

/* Declaração dos tokens... */

%token ID_TOKEN
%token INT_TOKEN

%start programa

%%
programa: numero
        | identificador
;
identificador: ID_TOKEN
            ;
numero: INT_TOKEN
    ;

// programa:		declaracao-lista
// 	;

// declaracao-lista:	declaracao-lista declaracao
// 	|		declaracao
// 	;

// declaracao:		var-declaracao
// 	|		fun-declaracao
// 	;

// var-declaracao:		tipo-especificador ID_TOKEN ";" 
// 	|		tipo-especificador ID_TOKEN "[" INT_TOKEN "]" ";"
// 	;

// tipo-especificador:	"int"
// 	|		"void"
// 	;

// fun-declaracao:		tipo-especificador ID_TOKEN "(" params ")" composto-decl
// 	;

// params:			param-lista
// 	|		"void"
// 	;

// param-lista:		param-lista "," param
// 	|		param
// 	;

// param:			tipo-especificador ID_TOKEN
// 	|		tipo-especificador ID_TOKEN "[" "]"
// 	;

// composto-decl:		"{" local-declaracoes statement-lista "}"
// 	;

// local-declaracoes:	local-declaracoes var-declaracao
// 	|
// 	;

// statement-lista:	statement-lista statement
// 	|
// 	;

// statement:		expressao-decl
// 	|		composto-decl
// 	|		selecao-decl
// 	|		iteracao-decl
// 	|		retorno-decl
// 	;

// expressao-decl:		expressao ";"
// 	|		";"
// 	;

// selecao-decl:		"if" "(" expressao ")" statement
// 	|		"if" "(" expressao ")" statement "else" statement
// 	;

// iteracao-decl:		"while" "(" expressao ")" statement
// 	;

// retorno-decl:		"return" ";"
// 	|		"return" expressao ";"
// 	;

// expressao:		var "=" expressao
// 	|		simples-expressao
// 	;

// var:			ID_TOKEN
// 	|		ID_TOKEN "[" expressao "]"
// 	;

// simples-expressao:	soma-expressao relacional soma-expressao
// 	|		soma-expressao
// 	;

// relacional:		"<="
// 	|		"<"
// 	|		">"
// 	|		">="
// 	|		"=="
// 	|		"!="
// 	;

// soma-expressao:		soma-expressao soma termo
// 	|		termo
// 	;

// soma:			"+"
// 	|		"-"
// 	;

// termo:			termo mult fator
// 	|		fator
// 	;
	
// mult:			"*"
// 	|		"/"
// 	;

// fator:			"(" expressao ")"
// 	|		var
// 	|		ativacao
// 	|		INT_TOKEN
// 	;

// ativacao:		ID_TOKEN "(" args ")"
// 	;

// args:			arg-lista
// 	|
// 	;

// arg-lista:		arg-lista "," expressao
// 	|		expressao
// 	;

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