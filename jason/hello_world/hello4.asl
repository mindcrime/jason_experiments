

/* A simple AgentSpeak program. */

/* Initial beliefs here */
started(0).


/* the plan section */

// this won't do anything at all, becuase we ignore the (0) part.
+started <- .print("Hello, 0 World!\n" ).

// this triggers because the X variable matches with the 0 in our belief.
+started(X) <- .print("Hello, ", X, " World!\n" ).
