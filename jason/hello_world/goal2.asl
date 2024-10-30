/* A simple AgentSpeak program showing simple use of a test goal. */


!rule_world.
neo_present.

// test goal 'neo_present'.
+?neo_present(N) <- .eval( N, .belief(neo_present)).
                                                     
                                                     // in the body of the plan, add the test goal ?neo_present
+!rule_world <- .print( "Starting to rule the world!\n" ); ?neo_present(N); .print( "Neo is present: ", N, "\n" ).
