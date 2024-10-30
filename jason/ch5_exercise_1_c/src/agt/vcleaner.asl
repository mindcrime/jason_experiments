
/* 
    OK, how is this going to work? Easiest thing would seem to be to always start
    by moving to position (0,0) and then move back and forth through the squares
    going left to right, then down and left, and repeat until finished.

    How to express that in AgentSpeak? 
    
    go_upper_left external action to begin
    
    go_right until we can't go right any more?
    then go down and left
    
    and repeat.  Stop if num_dirty is 0 or we're in the
    last square (if we're in the last square, once we've
    executed 'suck' then num_dirty should be 0 by definition)
    
    // as we move, have the environment add percepts for how
    // many squares to the right are available and how many 
    // rows down are available? And key our plan selection off
    // of that? Using recursion? 

*/


@c1[atomic] +!cleanroom : not visited_upper_left <- .print( "Starting to clean room!" ); goto_upper_left; !cleanroom.
@c2[atomic] +!cleanroom : numdirty(X) & X > 0 & (rows_down_available & squares_right_available) <- .print( "going right"); suck; right; !cleanroom.
@c3[atomic] +!cleanroom : numdirty(X) & X > 0 & (rows_down_available & not squares_right_available) <- .print( "going down and left"); down; full_left; !cleanroom.
@c4[atomic] +!cleanroom : numdirty(X) & X > 0 & (not rows_down_available & squares_right_available) <- .print( "going right"); suck; right; !cleanroom.
@c5[atomic] +!cleanroom : numdirty(X) & X == 0  <- .succeed_goal(cleanroom).

// +!exit : true <- action.exit.
+!exit : true <- .stopMAS.

+numdirty(X) : (X == 0) <- .print( "All squares are clean!" ); !exit.
+numdirty(X) : (X >= 1) <- !cleanroom.
