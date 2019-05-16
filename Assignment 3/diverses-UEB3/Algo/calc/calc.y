%{
#include <stdio.h>
int sym[26];
int yylex();
int yyerror(char *s);
%}

%token VARIABLE ASSIGN INTEGER NEWLINE
%left PLUS MINUS
%left TIMES
%left DIV
%left MOD
%left SMALLER
%left GREATER
%left SMALLEREQ
%left GREATEREQ
%left NOTEQ
%left EQUAL
%left OBR
%left CBR

%%

program: program statement
       | 
       ;

statement: expr NEWLINE 
	     { printf("%d\n", $1); }
         | VARIABLE ASSIGN expr NEWLINE
             { sym[$1] = $3; }
         ;

expr: INTEGER            { $$ = $1; }
      | VARIABLE         { $$ = sym[$1]; }
      | expr PLUS expr   { $$ = $1 + $3; }
	  | expr MINUS expr  { $$ = $1 - $3; }
      | expr TIMES expr  { $$ = $1 * $3; }
	  | expr DIV expr  	 { $$ = $1 / $3; }
	  | expr SMALLER expr  	 { $$ = $1 < $3; }
	  | expr GREATER expr  	 { $$ = $1 > $3; }
	  | expr SMALLEREQ expr  	 { $$ = $1 <= $3; }
	  | expr GREATEREQ expr  	 { $$ = $1 >= $3; }
	  | expr NOTEQ expr  	 { $$ = $1 != $3; }
	  | expr EQUAL expr  	 { $$ = $1 == $3; }
	  | OBR expr CBR	 { $$ = ($2); }
      ;

%%

int yyerror(char *s) {
  fprintf(stderr, "%s\n", s);
  return 0;
}

int main() {
  yyparse();
  return 0;
}
