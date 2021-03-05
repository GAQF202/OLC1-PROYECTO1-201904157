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
Cadena = \"(.*)\"
//Cadenita = \"[A-Z]|[a-z]|[0-9]\"
palabra = ({L}+|{D}*)+
lista = (([0-9]{BLANCOS}*",")+{BLANCOS}*|[0-9])+
lista_letras = (({L}{BLANCOS}*",")+{BLANCOS}*|{L})+
numero = [0-9]","{BLANCOS}
//Operador = ("."|"|"|"*"|"+"|"?")


ExpReg = "{"([a-zA-Z_]|[0-9]+)+"}"


//ExpReg = (("."{BLANCOS}*|"|"{BLANCOS}*|"*"{BLANCOS}*|"+"{BLANCOS}*|"?"{BLANCOS}*)+{BLANCOS}*)+
//Conjunto = 
COMENTARIO = "//"(.*)
COMENTARIO_MULTILINEA = "<!""!"*([^!>]|[^!]">"|"!"[^>])*"!"*"!>"
//COMENTARIO = [(<!([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\!+>)|(//.*)]

%%

"|" {return new Symbol(sym.Pipe, yyline, yychar, yytext());}
"%" {return new Symbol(sym.Cierre, yyline, yychar, yytext());}
"~" {return new Symbol(sym.Virgulilla, yyline, yychar, yytext());}
"{" {return new Symbol(sym.Llave_a, yyline, yychar, yytext());}
"}" {return new Symbol(sym.Llave_c, yyline, yychar, yytext());}
";" {return new Symbol(sym.P_coma, yyline, yychar, yytext());}
":" {return new Symbol(sym.Dos_p, yyline, yychar, yytext());}
"?" {return new Symbol(sym.Interrogacion, yyline, yychar, yytext());}
"*" {return new Symbol(sym.Asterisco, yyline, yychar, yytext());}
"+" {return new Symbol(sym.Mas, yyline, yychar, yytext());}
"-" {return new Symbol(sym.Menos, yyline, yychar, yytext());}
">" {return new Symbol(sym.Mayor, yyline, yychar, yytext());}
"." {return new Symbol(sym.Punto, yyline, yychar, yytext());}
"," {return new Symbol(sym.Coma, yyline, yychar, yytext());}
"CONJ" {return new Symbol(sym.conj, yyline, yychar, yytext());}
\n          {yycolumn=1;}

{BLANCOS}   {/*Se ignoran*/}
{COMENTARIO}   {/*Se ignoran*/}
{COMENTARIO_MULTILINEA}   {/*Se ignoran*/}
{L}    {return new Symbol(sym.L, yycolumn, yyline, yytext());}
{D}   {return new Symbol(sym.D, yycolumn, yyline, yytext());}
{numero}   {return new Symbol(sym.numero, yycolumn, yyline, yytext());}
{palabra} {return new Symbol(sym.palabra,yycolumn,yyline,yytext());}
{lista} {return new Symbol(sym.Lista,yycolumn,yyline,yytext());}
{lista_letras} {return new Symbol(sym.Lista_letras,yycolumn,yyline,yytext());}
{Cadena}    {return new Symbol(sym.Cadena, yycolumn, yyline, yytext());}
//{Cadenita}    {return new Symbol(sym.Cadenita, yycolumn, yyline, yytext());}
//{Reg} {return new Symbol(sym.Reg,yycolumn,yyline,yytext());}
//{Operador} {return new Symbol(sym.Operador,yycolumn,yyline,yytext());}
{ExpReg} {return new Symbol(sym.ExpReg,yycolumn,yyline,yytext());}

.   {
	    System.err.println("Error lexico: "+yytext()+ " Linea:"+(yyline)+" Columna:"+(yycolumn));
    }