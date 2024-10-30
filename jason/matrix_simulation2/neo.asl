/* Initial beliefs */

started.
started2.

// test goal 'neo_present'.
+?oracle_killed(N) : oracle_killed <- N = true.
+?oracle_killed(N) : (not oracle_killed)   <- N = false.

+started <- .print("Hello, this is Neo!\n"); ?oracle_killed(N); .print( "Is Oracle killed?", N ).

+started2 <- .wait(6000); ?oracle_killed(N); .print( "Oracle killed?", N ).

+oracle_killed <- .print( "Smith has killed the Oracle!" ).

