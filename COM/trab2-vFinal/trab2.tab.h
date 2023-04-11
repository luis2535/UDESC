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
    ID_TOKEN = 258,                /* ID_TOKEN  */
    INT_TOKEN = 259,               /* INT_TOKEN  */
    INT_TYPE_TOKEN = 260,          /* INT_TYPE_TOKEN  */
    VOID_TYPE_TOKEN = 261,         /* VOID_TYPE_TOKEN  */
    OPEN_BRACKET_TOKEN = 262,      /* OPEN_BRACKET_TOKEN  */
    CLOSE_BRACKET_TOKEN = 263,     /* CLOSE_BRACKET_TOKEN  */
    OPEN_KEYS_TOKEN = 264,         /* OPEN_KEYS_TOKEN  */
    CLOSE_KEYS_TOKEN = 265,        /* CLOSE_KEYS_TOKEN  */
    OPEN_PARENTHESES_TOKEN = 266,  /* OPEN_PARENTHESES_TOKEN  */
    CLOSE_PARENTHESES_TOKEN = 267, /* CLOSE_PARENTHESES_TOKEN  */
    SEMICOLON_TOKEN = 268,         /* SEMICOLON_TOKEN  */
    IF_TOKEN = 269,                /* IF_TOKEN  */
    ELSE_TOKEN = 270,              /* ELSE_TOKEN  */
    WHILE_TOKEN = 271,             /* WHILE_TOKEN  */
    RETURN_TOKEN = 272,            /* RETURN_TOKEN  */
    COMMA_TOKEN = 273,             /* COMMA_TOKEN  */
    EQUAL_TOKEN = 274,             /* EQUAL_TOKEN  */
    SMALLER_TOKEN = 275,           /* SMALLER_TOKEN  */
    BIGGER_TOKEN = 276,            /* BIGGER_TOKEN  */
    SMALLER_EQUAL_TOKEN = 277,     /* SMALLER_EQUAL_TOKEN  */
    BIGGER_EQUAL_TOKEN = 278,      /* BIGGER_EQUAL_TOKEN  */
    DIFF_TOKEN = 279,              /* DIFF_TOKEN  */
    COMPARE_TOKEN = 280,           /* COMPARE_TOKEN  */
    ADD_TOKEN = 281,               /* ADD_TOKEN  */
    SUB_TOKEN = 282,               /* SUB_TOKEN  */
    MULT_TOKEN = 283,              /* MULT_TOKEN  */
    DIV_TOKEN = 284                /* DIV_TOKEN  */
  };
  typedef enum yytokentype yytoken_kind_t;
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
union YYSTYPE
{
#line 17 "trab2.y"

	int ival;
	char* sval;
	char cval;

#line 99 "trab2.tab.h"

};
typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;


int yyparse (void);


#endif /* !YY_YY_TRAB2_TAB_H_INCLUDED  */
