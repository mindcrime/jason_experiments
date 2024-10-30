started.

+started <- .print( "Hello, Agent1!" );
            !do_sub_goal;
            .print( "Done" ).
            
/* but we don't *have* a plan for do_sub_goal, so this will cause a failure! */


/* Ironically though, we do have a failure plan to handle the failure case */
/* Note though that failure can be triggered by other reasons, not just "no plan exists" */

-!do_sub_goal <- .print( "handling failure for do_sub_goal").

