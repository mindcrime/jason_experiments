last_order_id(1). // initial belief

// plan to achieve the goal "order" for agent Ag
+!order(Product,Qtd)[source(Ag)] : true
  <- .print( "Received order from robot" );
     ?last_order_id(N);
     OrderId = N + 1;
     -+last_order_id(OrderId);
     deliver(Product,Qtd);
     .print( "Sending 'delivered' message to robot agent");
     .send(Ag, tell, delivered(Product,Qtd,OrderId)).

