// Agent bob in project true_test

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

// +!start : true <- true; .print( "At fridge" ).

+!start : true <- .print( "At fridge" ); true.
