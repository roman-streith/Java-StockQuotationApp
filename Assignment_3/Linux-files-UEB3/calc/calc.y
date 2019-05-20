%{
#include <stdio.h>
int minmax;
int sym[26];
int yylex();
int yyerror(char *s);
%}

%token VARIABLE ASSIGN INTEGER NEWLINE
%left COMMA
%nonassoc MIN MAX
%left IFTRUE COLON
%left LESSER GREATER LESSEREQUAL GREATEREQUAL EQUAL NOTEQUAL
%left PLUS MINUS
%left TIMES DIVIDE MOD
%nonassoc OPEN CLOSE UMINUS UPLUS 
%nonassoc HEX

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
      | expr DIVIDE expr { $$ = $1 / $3; }
      | expr MOD expr    { $$ = $1 % $3; }
      | expr LESSER expr { $$ = ($1 < $3); }
      | expr GREATER expr { $$ = ($1 > $3); }
      | expr LESSEREQUAL expr { $$ = ($1 <= $3); }
      | expr GREATEREQUAL expr { $$ = ($1 >= $3); }
      | expr EQUAL expr { $$ = ($1 == $3); }
      | expr NOTEQUAL expr { $$ = ($1 != $3); }
      | MINUS expr %prec UMINUS { $$ = -$2; }
      | PLUS expr %prec UPLUS { $$ = $2; }
      | OPEN expr CLOSE { $$ = $2; }
      | expr IFTRUE expr COLON expr { $$ = ($1 ? $3 : $5); }
      | MIN expr { $$ = $2; }
      | MAX expr { $$ = $2; }
      | expr COMMA expr { if (minmax) {$$ = ($1 > $3) ? $1 : $3;} else {$$ = ($1 < $3) ? $1 : $3;} }
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
