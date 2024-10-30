started.

@p1[atomic] +started <- .print( "Hello, Agent1!" );
            !do_sub_goal;
            .print( "Done" ).
            
/* here we do *have* a plan for do_sub_goal, but the context check fails, so this will cause a failure! */
@p2[breakpoint] +!do_sub_goal : false <- .print( "Doing sub goal here!" ).

/* and again, we handle the failure by defining a failure plan like this */
-!do_sub_goal <- .print( "handling failure for do_sub_goal").

