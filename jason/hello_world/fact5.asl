/* A simple AgentSpeak program. */

// this time, instead of an initial belief, we start with an initial goal.
!print_fact(5).

+!print_fact(N) <- !fact(N, F); .print( "Factorial of ", N, " is ", F).
  
  
+!fact(N, F): N == 0 <- F = 1.
/* Can also be written as: */
/*  +!fact(N, 1): N == 0.   */

      
// triggers on the goal 'fact'
+!fact(N, F) : true <- .print( "Calculating."); !fact(N-1, F1); F = F1 * N; .print( "Calculating. N=", N-1, " F=", F, " F1 = ", F1, "\n" ).
