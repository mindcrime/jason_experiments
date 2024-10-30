

/* A simple AgentSpeak program. */

/* Initial beliefs here */
started(0).


/* the plan section */

// this won't do anything at all, becuase we ignore the (0) part.
+started <- .print("Hello, 0 World!\n" ).

// add a Context to evaluate if the plan should fire or not.
+started(X) : X > 0 
    <- .print("Hello, ", X, " World!\n" ).

+started(X) : X == 0
    <- .print("Nothing happening here!\n" ) ; +started(42).  
    