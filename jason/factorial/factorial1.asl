fact( 0, 1).

+fact(X,Y) : X < 5 <- +fact(X+1, (X+1)*Y).

+fact(X,Y) : X == 5 <- .print( "fact 5 == ", Y ).

