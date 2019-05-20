%{
extern int minmax;
extern int yylval; 
extern int sym[];
extern int yyerror(char *s);
#include "y.tab.h"
%}

%%

[a-z]   {
	   yylval = *yytext - 'a';
	   return VARIABLE;
        }

[0-9]+  {  
           yylval = atoi(yytext);
           return INTEGER;
        }

":="    return ASSIGN;
"+"     return PLUS;
"-"     return MINUS;
"*"     return TIMES;
"/"     return DIVIDE;
"%"     return MOD;
"<"     return LESSER;
">"     return GREATER;
"<="    return LESSEREQUAL;
">="    return GREATEREQUAL;
"=="    return EQUAL;
"!="    return NOTEQUAL;
"0x"[0-9A-F]+   {
                   yylval = strtol(yytext, 0, 16);
                   return INTEGER;
                }
"("     return OPEN;
")"     return CLOSE;
"?"     return IFTRUE;
":"     return COLON;
","     return COMMA;
"min"   {
           minmax = 0;
           return MIN;
        }
"max"   {
           minmax = 1;
           return MAX;
        }

"\n"    return NEWLINE;

[ \t]   ;

.       yyerror("Invalid character");

%%

int yywrap() {
 return 1;
}
