started.

+started <- .print( "Hello, Agent1!" );
            !do_sub_goal;
            .print( "Done" ).
            
/* here we do *have* a plan for do_sub_goal, but our environment action fails, so this will cause a failure! */
+!do_sub_goal : true <- .print( "Doing sub goal here!" ); 
                        walk_the_dog;
                        .print( "Done with do_sub_goal" ).

/* and again, we handle the failure by defining a failure plan like this */
-!do_sub_goal <- .print( "handling failure for do_sub_goal").
