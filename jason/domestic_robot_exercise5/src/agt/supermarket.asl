last_order_id(1). // initial belief

beer_price(30).

started.

+started <- 
    ?beer_price(Bp);
    .print( "Beer price: ", Bp );
    .send(robot, tell, price(beer, Bp)).

// plan to achieve the goal "order" for agent Ag
+!order(Product,Qtd)[source(Ag)] : true
  <- .print( "received order from robot");
     ?last_order_id(N);
     OrderId = N + 1;
     -+last_order_id(OrderId);
     deliver(Product,Qtd);
     .print( "Sending 'delivered' message to robot agent");
     .send(Ag, tell, delivered(Product,Qtd,OrderId)).

