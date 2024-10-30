// !g2.


!g1.

+!g2 : true <- a3; a4; disable_cycle_logging.
+!g1 : true <- !g2.
+b : true <- !g1; a1.

-!g1 : true <- a5.
