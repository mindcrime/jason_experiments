/* A simple AgentSpeak program showing simple use of a (sub) achievement goal. */


!rule_world.

// test goal 'neo_present'.
+!kill_neo <- .wait(3000); .print( "Killing Neo!" ).
                                                     // in the body of the plan, add the achievement goal !kill_neo

+!rule_world <- .print( "Starting to rule the world!\n" ); !!kill_neo; .print( "This is our time" ).
