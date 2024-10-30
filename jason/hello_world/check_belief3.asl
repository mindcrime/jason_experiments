started.


+started <- .print( "Checking if Oracle is killed" ); 
    .date( YY, MMM, DD ); .time( HH1, MM1, SS1 );
    +oracle_killed(YY, MMM, DD, HH1, MM1, SS1);
    .count(oracle_killed(YY, MMM, DD, _, _, _), OK );
    .print( "Oracle is killed: ", OK );
    
    .wait(1250);
    .time( HH2, MM2, SS2 );
    +oracle_killed(YY, MMM, DD, HH2, MM2, SS2);
    .count(oracle_killed(YY, MMM, DD, _, _, _), OK2 );
    .print( "Oracle is killed: ", OK2 );
    
    .wait(1250);
    .time( HH3, MM3, SS3 );
    +oracle_killed(YY, MMM, DD, HH3, MM3, SS3);
    .count(oracle_killed(YY, MMM, DD, _, _, _), OK3 );
    .print( "Oracle is killed: ", OK3 );
    
    .wait(1250);
    -oracle_killed(_, _, _, _, _, _);
    -oracle_killed(_, _, _, _, _, _);
    -oracle_killed(_, _, _, _, _, _);
    .count(oracle_killed(YY, MMM, DD, _, _, _), OK4 );
    .print( "Oracle is killed: ", OK4 ).
    