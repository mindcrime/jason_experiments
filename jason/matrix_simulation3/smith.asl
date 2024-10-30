/* Initial beliefs */

started.
started2.

+started <- .print("Hello, this is Smith!\n"); .wait(4000); .send( neo, tell, oracle_killed ).
+started2 <- .wait( 7000 ); .send( neo, tell, msg("You're next!") ).