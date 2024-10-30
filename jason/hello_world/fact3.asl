/* A simple AgentSpeak program. */

// this time, instead of an initial belief, we start with an initial goal.
!print_fact(5).

// and now our trigger event is "we acquired the goal print_fact" not "we acquired SOME BELIEF."
// but this is still just a plan, where we do normal "plan stuff".
+!print_fact(X) : true <- .print("Fact: ", X); 
      !fact. // asserts a goal
      
// triggers on the goal 'fact'
+!fact <- .print("Goal acquired!").
