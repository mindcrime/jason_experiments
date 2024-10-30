
sum(0).

+v1(1) : sum(S) <- .print( S+1). // the last tell from the sender

@v[atomic] +v1(X) : sum(S) <- NS = S + 1; /* .print( "NS: ", NS, " X:", X ); */ -+sum(NS).
