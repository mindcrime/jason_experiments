/* A simple AgentSpeak program that uses rules and prints "Hello World" to the console. */

/* Initial beliefs here */
started.


/* rules */
time_to_start :- started.

/* the plan section */


+started : time_to_start <- .time(H, M, S ); 
                            .my_name(N);
                            .print("Hello, World!\n", "My name: ", N, "\nGOT TIME: ", H, ":", M, ":", S ).
