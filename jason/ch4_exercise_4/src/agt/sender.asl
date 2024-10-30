!send(100). // initial goal

+!send(0) : true <- true. // stop sending
+!send(X) : true <- .send( rec, tell, v1(X)); !send(X-1).
