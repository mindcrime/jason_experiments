// Agent owner in project domestic_robot_from_scratch

/* Initial beliefs and rules */

/* Initial goals */

!get(beer). 

/* Plans */

+!get(beer) : true 
    <- .send( robot, achieve, has(owner, beer) ).

+has(owner, beer) <- .print( "I have a beer!" ); !drink(beer).

-has(owner, beer) <- .print( "I'm out of beer!!"); !get(beer). // get another beer

+!drink(beer) : has(owner,beer) 
    <- sip(beer);
       !drink(beer).
       
+!drink(beer) : not has(owner,beer)
    <- true.
    
+msg(M)[source(Ag)] : true
    <- .print( "Message from ", Ag, ": ", M );
    -msg(M).
    
+!ask_beer : true <- !get(beer). 
