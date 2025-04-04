import java_cup.runtime.*;

%%

%class Lexer
%unicode
%cup
%line
%column

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

digit = [0-9]
integer = {digit}+
decimal = {integer}("."{integer})?  
whitespace = [ \t\n\r]+

%%

"+"     { return symbol(sym.PLUS); }
"-"     { return symbol(sym.MINUS); }
"*"     { return symbol(sym.TIMES); }
"/"     { return symbol(sym.DIVIDE); }
"("     { return symbol(sym.LPAREN); }
")"     { return symbol(sym.RPAREN); }

// Use {decimal} to support both integers and floating-point numbers
{decimal} { return symbol(sym.NUMBER, Double.valueOf(yytext())); }

{whitespace} { /* ignore whitespace */ }

.       { System.err.println("Illegal character: " + yytext()); }

<<EOF>> { return symbol(sym.EOF); }