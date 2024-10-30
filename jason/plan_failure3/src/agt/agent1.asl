started.

+started <- .print( "Hello, Agent1!" );
            !do_sub_goal;
            .print( "Done" ).
            
/* here we do *have* a plan for do_sub_goal, but our test goal will fail, causing a failure scenario */
+!do_sub_goal : true <- .print( "Doing sub goal here!" );
                        ?dogs_of_war;
                        .print( "Done with sub-goal!" ).

/* and again, we handle the failure by defining a failure plan like this */
-!do_sub_goal <- .print( "handling failure for do_sub_goal").

