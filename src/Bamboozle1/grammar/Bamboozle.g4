grammar Bamboozle;


instruction
: toDo ';' instruction
| toDo ';'
| EOF                           // implicitly defined terminal
;

toDo
: declaration
| assign
| print
| repeat
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


repeat
: 'repeat' info '<' info '{' instruction '}'
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
