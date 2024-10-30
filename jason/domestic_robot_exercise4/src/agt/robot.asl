// Agent robot in project domestic_robot_from_scratch

/* Initial beliefs and rules */

available(beer,fridge).
limit( beer, 10 ).

too_much(B) :- 
    .date( YY, MM, DD ) & 
    .count( consumed( YY, MM, DD, _, _, _, B), QtdB) & 
    limit( B, Limit ) & QtdB >= Limit.

/* Initial goals */



/* Plans */

@h1
+!has(owner,beer) : available(beer,fridge) & not too_much(beer) // if there is beer available and the owner is not over the daily limit
    <- .print( "Owner has requested a beer!"); 
        !at(robot,fridge); // achievement goal for the robot to be at the fridge
        open(fridge);
        get(beer);
        close(fridge);
        !at(robot,owner);
        hand_in(beer);
        ?has(owner,beer);
        .print( "Got my owner a beer. Rad!");
        // remember that another beer has been consumed
        .date(YY,MM,DD); .time(HH,NN,SS);
        +consumed(YY,MM,DD,HH,NN,SS,beer);
        .count(consumed( YY, MM, DD, _, _, _, beer), Beers);
        .print( "I have given my owner ", Beers, " beers" ).

@h2
+!has(owner,beer) : not available(beer,fridge) <- .print( "Ordering more beer from supermarket"); .send( supermarket, achieve, order(beer,5));
    !at(robot,fridge). // go to fridge and wait there


@h3
+!has(owner,beer) : too_much(beer) & limit( beer, L ) 
        <- .concat( "The Department of Health does not allow me ",
        "to give you more than ", L, 
        " beers a day. I am very sorry about that!", M );
        .send( owner, tell, msg(M)).


/* It appears to be the case that it doesn't matter what order @m1 and @m2 are physically
   positioned in, in the file = in the case where both have a meaningful context as here */
   
/*

@m2                                     
+!at(robot,P) : not at(robot,P)      // at(X,Y) is a belief/percept from the Environment
            <- move_towards(P);      // we're not at the target desination P yet, so we need to move there
               !at(robot,P).         // this goal adds itself recursively until we arrive at the destination


@m1
+!at(robot,P) : at(robot,P)     // at(X,Y) is a belief/percept from the Environment
    <- .print( "At ", P). // if we have an achievement goal to move the robot to P
                                     // then if the robot is at P, the goal has succeeded
*/                                     
           
/* But what about the case where @m2 doesn't have a context as such and would always execute? 
    
    Here, the more general case will execute first if it appears first, so switching the order
    breaks the system. But as long as the more specific plan trigger/context appears first, it
    will execute first when appropriate and then execution will only "fall through" to the more
    general one if the first one doesn't match.

*/

@m1
+!at(robot,P) : at(robot,P)     // at(X,Y) is a belief/percept from the Environment
    <- .print( "At ", P). // if we have an achievement goal to move the robot to P
                                     // then if the robot is at P, the goal has succeeded




@m2                                     
+!at(robot,P) : true      // at(X,Y) is a belief/percept from the Environment
            <- move_towards(P);      // we're not at the target desination P yet, so we need to move there
               !at(robot,P).         // this goal adds itself recursively until we arrive at the destination
            


   
               
               
               
               
               
               
@a1            
+stock(beer,0) : available(beer,fridge) 
    <- .print( "beer stock is down to 0" ); -available(beer,fridge).               

// we got a delivery from the supermarket agent
@a2
+delivered(beer, Qtd, OrderId)[source(supermarket)] : true
    <- +available(beer,fridge);
       !has(owner,beer).

   
@a3            // when the fridge is opened and beer is there, add the belief available(beer,fridge)
+stock(beer,N) : N > 0 & not available(beer,fridge) 
    <- .print( "beer stock is now: ", N ); +available(beer,fridge).
