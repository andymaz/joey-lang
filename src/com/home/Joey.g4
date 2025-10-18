grammar Joey;

start : sentence ;

sentence : BEGIN single+ END ;

single : polarity modality subjects verbs objects ;

polarity : POSITIVE | NEGATIVE ;

modality : AUTHORISATION | OBLIGATION ;

subjects : words ;
verbs    : words ;
objects  : words ;

words
    : WORD                              // single word
    | LBRACKET WORD (COMMA WORD)* RBRACKET // comma-separated list
    ;

// Tokens
COMMA           : ',' ;
BEGIN           : 'begin' ;
END             : 'end' ;
POSITIVE        : '+ve' ;
NEGATIVE        : '-ve' ;
AUTHORISATION   : 'authorised' ;
OBLIGATION      : 'obligated' ;
LBRACKET        : '{' ;
RBRACKET        : '}' ;

// Identifiers
WORD : [a-zA-Z_][a-zA-Z0-9_]* ;

// Whitespace and comments
WS : [ \t\r\n]+ -> skip ;
COMMENT : '/*' .*? '*/' -> skip ;
LINE_COMMENT : '//' ~[\r\n]* -> skip ;
