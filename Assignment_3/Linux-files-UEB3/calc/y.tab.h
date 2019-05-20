/* A Bison parser, made by GNU Bison 3.0.4.  */

/* Bison interface for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2015 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

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

#ifndef YY_YY_Y_TAB_H_INCLUDED
# define YY_YY_Y_TAB_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    VARIABLE = 258,
    ASSIGN = 259,
    INTEGER = 260,
    NEWLINE = 261,
    COMMA = 262,
    MIN = 263,
    MAX = 264,
    IFTRUE = 265,
    COLON = 266,
    LESSER = 267,
    GREATER = 268,
    LESSEREQUAL = 269,
    GREATEREQUAL = 270,
    EQUAL = 271,
    NOTEQUAL = 272,
    PLUS = 273,
    MINUS = 274,
    TIMES = 275,
    DIVIDE = 276,
    MOD = 277,
    OPEN = 278,
    CLOSE = 279,
    UMINUS = 280,
    UPLUS = 281,
    HEX = 282
  };
#endif
/* Tokens.  */
#define VARIABLE 258
#define ASSIGN 259
#define INTEGER 260
#define NEWLINE 261
#define COMMA 262
#define MIN 263
#define MAX 264
#define IFTRUE 265
#define COLON 266
#define LESSER 267
#define GREATER 268
#define LESSEREQUAL 269
#define GREATEREQUAL 270
#define EQUAL 271
#define NOTEQUAL 272
#define PLUS 273
#define MINUS 274
#define TIMES 275
#define DIVIDE 276
#define MOD 277
#define OPEN 278
#define CLOSE 279
#define UMINUS 280
#define UPLUS 281
#define HEX 282

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_Y_TAB_H_INCLUDED  */
