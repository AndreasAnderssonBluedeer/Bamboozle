grammar Bamboozle;

// Remove this header if using the default IntelliJ project layout
@header {
package bamboozle2.grammar;
}

instruction
: toDo ';' instruction
| EOF                           // implicitly defined terminal
;

toDo
: declaration
| assign
| print
;

declaration
: 'nbr' ID
;

assign
: ID '->' expression
;

print
: 'log' expression
;

expression
: info
| expression '+' info
;

info
: ID
| INT
;

ID:	('a'..'z')+ ;
INT:	('0'..'9')+ ;
WS:	[ \n\t\r]+ -> skip ;
