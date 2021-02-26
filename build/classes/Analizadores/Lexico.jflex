package analizadores;
import java_cup.runtime.*;

%%

%class Lexico
%cupsym sym
%cup 
%public
%unicode
%line 
%column
%ignorecase

%init{

%init}

BLANCOS = [ \t\r\n]+
D = [0-9]+
L = [a-zA-Z_]
Cadena = "\""(.*)"\""
palabra = ({L}+|{D}*)+
lista = (([0-9]{BLANCOS}*",")+{BLANCOS}*|[0-9])+
numero = [0-9]","{BLANCOS}

Reg = (("."{BLANCOS}*|"|"{BLANCOS}*|"*"{BLANCOS}*|"+"{BLANCOS}*|"?"{BLANCOS}*)+{BLANCOS}*(["{"[a-zA-Z_]+"}"]{BLANCOS}*|[\"(.*)\"]{BLANCOS}*)+)+
COMENTARIO = "//"(.*)
//COMENTARIO = [(<!([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\!+>)|(//.*)]

%%

//"|" {return new Symbol(sym.Pipe, yyline, yychar, yytext());}
"%%\n%%" {return new Symbol(sym.Cierre, yyline, yychar, yytext());}
"~" {return new Symbol(sym.Virgulilla, yyline, yychar, yytext());}
"{" {return new Symbol(sym.Llave_a, yyline, yychar, yytext());}
"}" {return new Symbol(sym.Llave_c, yyline, yychar, yytext());}
";" {return new Symbol(sym.P_coma, yyline, yychar, yytext());}
":" {return new Symbol(sym.Dos_p, yyline, yychar, yytext());}
//"?" {return new Symbol(sym.Interrogacion, yyline, yychar, yytext());}
//"*" {return new Symbol(sym.Aterisco, yyline, yychar, yytext());}
"->" {return new Symbol(sym.Flecha, yyline, yychar, yytext());}
//"." {return new Symbol(sym.Punto, yyline, yychar, yytext());}
"," {return new Symbol(sym.Coma, yyline, yychar, yytext());}
"CONJ" {return new Symbol(sym.conj, yyline, yychar, yytext());}
\n          {yycolumn=1;}

{BLANCOS}   {/*Se ignoran*/}
{COMENTARIO}   {/*Se ignoran*/}
{L}    {return new Symbol(sym.L, yycolumn, yyline, yytext());}
{D}   {return new Symbol(sym.D, yycolumn, yyline, yytext());}
{numero}   {return new Symbol(sym.numero, yycolumn, yyline, yytext());}
{palabra} {return new Symbol(sym.palabra,yycolumn,yyline,yytext());}
{lista} {return new Symbol(sym.Lista,yycolumn,yyline,yytext());}
{Cadena}    {return new Symbol(sym.Cadena, yycolumn, yyline, yytext());}
{Reg} {return new Symbol(sym.Reg,yycolumn,yyline,yytext());}

.   {
	    System.err.println("Error lexico: "+yytext()+ " Linea:"+(yyline)+" Columna:"+(yycolumn));
    }