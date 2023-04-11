/* A Bison parser, made by GNU Bison 3.8.2.  */

/* Bison interface for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2015, 2018-2021 Free Software Foundation,
   Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <https://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* DO NOT RELY ON FEATURES THAT ARE NOT DOCUMENTED in the manual,
   especially those whose name start with YY_ or yy_.  They are
   private implementation details that can be changed or removed.  */

#ifndef YY_YY_TRAB2_TAB_H_INCLUDED
# define YY_YY_TRAB2_TAB_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Token kinds.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    YYEMPTY = -2,
    YYEOF = 0,                     /* "end of file"  */
    YYerror = 256,                 /* error  */
    YYUNDEF = 257,                 /* "invalid token"  */
    INT_TOKEN = 258,               /* INT_TOKEN  */
    FLOAT_TOKEN = 259,             /* FLOAT_TOKEN  */
    INTEGER_TYPE_TOKEN = 260,      /* INTEGER_TYPE_TOKEN  */
    FLOAT_TYPE_TOKEN = 261,        /* FLOAT_TYPE_TOKEN  */
    VOID_TYPE_TOKEN = 262,         /* VOID_TYPE_TOKEN  */
    BOOL_TYPE_TOKEN = 263,         /* BOOL_TYPE_TOKEN  */
    STRING_TYPE_TOKEN = 264,       /* STRING_TYPE_TOKEN  */
    TRUE_TOKEN = 265,              /* TRUE_TOKEN  */
    FALSE_TOKEN = 266,             /* FALSE_TOKEN  */
    IF_TOKEN = 267,                /* IF_TOKEN  */
    ELSE_TOKEN = 268,              /* ELSE_TOKEN  */
    WHILE_TOKEN = 269,             /* WHILE_TOKEN  */
    RETURN_TOKEN = 270,            /* RETURN_TOKEN  */
    ADD_TOKEN = 271,               /* ADD_TOKEN  */
    SUB_TOKEN = 272,               /* SUB_TOKEN  */
    MULT_TOKEN = 273,              /* MULT_TOKEN  */
    DIV_TOKEN = 274,               /* DIV_TOKEN  */
    BIGGER_TOKEN = 275,            /* BIGGER_TOKEN  */
    SMALLER_EQUAL_TOKEN = 276,     /* SMALLER_EQUAL_TOKEN  */
    BIGGER_EQUAL_TOKEN = 277,      /* BIGGER_EQUAL_TOKEN  */
    SMALLER_TOKEN = 278,           /* SMALLER_TOKEN  */
    COMPARE_TOKEN = 279,           /* COMPARE_TOKEN  */
    DIFF_TOKEN = 280,              /* DIFF_TOKEN  */
    EQUAL_TOKEN = 281,             /* EQUAL_TOKEN  */
    CHAR_TOKEN = 282,              /* CHAR_TOKEN  */
    ID_TOKEN = 283,                /* ID_TOKEN  */
    OPEN_BRACKET_TOKEN = 284,      /* OPEN_BRACKET_TOKEN  */
    CLOSE_BRACKET_TOKEN = 285,     /* CLOSE_BRACKET_TOKEN  */
    OPEN_PARENTHESES_TOKEN = 286,  /* OPEN_PARENTHESES_TOKEN  */
    CLOSE_PARENTHESES_TOKEN = 287, /* CLOSE_PARENTHESES_TOKEN  */
    OPEN_KEYS_TOKEN = 288,         /* OPEN_KEYS_TOKEN  */
    CLOSE_KEYS_TOKEN = 289,        /* CLOSE_KEYS_TOKEN  */
    COMMA_TOKEN = 290,             /* COMMA_TOKEN  */
    SEMICOLON_TOKEN = 291,         /* SEMICOLON_TOKEN  */
    SYMBOLS_TOKEN = 292,           /* SYMBOLS_TOKEN  */
    PRINT_TOKEN = 293              /* PRINT_TOKEN  */
  };
  typedef enum yytokentype yytoken_kind_t;
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
union YYSTYPE
{
#line 14 "trab2.y"

	int ival;
	float fval;
	char cval;
	char *sval;

#line 109 "trab2.tab.h"

};
typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;


int yyparse (void);


#endif /* !YY_YY_TRAB2_TAB_H_INCLUDED  */
