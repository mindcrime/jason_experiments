started.

oracle_killed.


+started <- .print( "Checking if Oracle is killed" ); .count(oracle_killed, 1); 
    .eval(N, .count(oracle_killed) > 0 );
    .print( "Oracle is killed: ", N );
    -oracle_killed;
    .eval(N2, .count(oracle_killed) > 0 );
    .print( "Oracle is killed: ", N2 );
    .eval( N3, .belief(oracle_killed));
    .print( "Oracle is killed: ", N3 );
    +oracle_killed;
    .eval( N4, .belief(oracle_killed));
    .print( "Oracle is killed: ", N4 ).
