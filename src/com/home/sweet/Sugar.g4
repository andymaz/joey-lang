grammar Sugar;

start : POLICY name COLON sentences;

sentences
         : BEGIN sentence+ END
         | EMPTY
         ;

sentence : subject ASSIGN BEGIN single+ END ;

single   : polarity modality verbs objects ;

polarity : POSITIVE | NEGATIVE ;

modality : AUTHORISATION | OBLIGATION ;

subject : WORD ;
verbs    : words ;
objects  : words ;

name    : WORD;

words
    : WORD                                  #single_word
    | LBRACKET WORD (COMMA WORD)+ RBRACKET  #compound_word
    ;

// Tokens
COMMA           : ',' ;
BEGIN           : 'begin' ;
END             : 'end' ;
POLICY          : 'policy' ;
EMPTY           : 'empty' ;
ASSIGN          : '<==' ;
POSITIVE        : '+ve' ;
NEGATIVE        : '-ve' ;
AUTHORISATION   : 'authorised' ;
OBLIGATION      : 'obligated' ;
LBRACKET        : '{' ;
RBRACKET        : '}' ;
COLON           : ':' ;

// Identifiers
WORD : [a-zA-Z_][a-zA-Z0-9_]* ;

// Whitespace and comments
WS : [ \t\r\n]+ -> skip ;
COMMENT : '/*' .*? '*/' -> skip ;
LINE_COMMENT : '//' ~[\r\n]* -> skip ;
