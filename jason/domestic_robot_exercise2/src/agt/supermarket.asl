last_order_id(1). // initial belief
beer_stock(7). // initial belief

// plan to achieve the goal "order" for agent Ag
+!order(Product,Qtd)[source(Ag)] : beer_stock(B) & (B) >= 5
  <- ?last_order_id(N);
     ?beer_stock(B);
     .print( "Last Order ID: ", N, " Beer Stock: ", B );
     OrderId = N + 1;
     BeerStock = B - 5;
     -+last_order_id(OrderId);
     -+beer_stock(BeerStock);
     deliver(Product,Qtd);
     .print( "Sending 'delivered' message to robot agent");
     .send(Ag, tell, delivered(Product,Qtd,OrderId)).

+!order(Product,Qtd)[source(Ag)] : beer_stock(B) & B < 5
  <- .send( Ag, tell, no_more_beer).
  