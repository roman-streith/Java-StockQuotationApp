%{
extern int ext;
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

"0x"[0-9A-F]+   {
                   yylval = strtol(yytext, 0, 16);
                   return INTEGER;
                }

"min"   {
           ext = 0;
           return EXT;
        }

"max"   {
           ext = 1;
           return EXT;
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
"("     return OPEN;
")"     return CLOSE;
"?"     return IFTRUE;
":"     return COLON;
","     return COMMA;
"\n"    return NEWLINE;

[ \t]   ;

.       yyerror("Invalid character");

%%

int yywrap() {
 return 1;
}
