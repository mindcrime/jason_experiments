# Section 4.5, Exercise 1

Suppose that an agent has the following plan library:

- @p1 +g(X,Y) : true <- ... .

- @p2 +g(X,Y) : a(Y) & not b(X) <- ... .

- @p3 +g(X,_) : a(Y) & Y > X <- ... .

- @p4 +g(X,Y)[source(self)] : true <- ... .

- @p5 +g(X,Y)[source(self),source(ag1)] : true <- ... .

- @p6[all_unifs] +g(10,Y) : a(Y) <- ... .

and the following beliefs:

- a(10).

- a(5).

- b(20).

If the event < +g(10,5)[source(ag1)] > is selected, which plans are relevant and which are applicable?

---
#Notes

"Applicable" plans are plans such that their **triggering event** can be unified with the **selected event**.



"Relevant" plans are ** applicable plans** such that they are both applicable plans AND their **context is true** given the 
    agent's current beliefs.



---
# Solution

1. Applicable plans:

- p1
- p2
- p3
- p5
- p6

2. Relevant plans:


- p1 (because the context is always true)
- p2 (because a(5) is true and (not b(10)) is true
- NOT p3 (??) (because there is no Y var there to test, so the context should return false?)
- p5 (because the context is always true)
- p6 (because a(5) is true)



# Section 4.5, Exercise 2

Consider the state of the stack of an agent's intention as shown below:

--------------------------------------------------
+!g2   : true   <- a3; a4.
--------------------------------------------------
+!g1   : true   <- !g2.
--------------------------------------------------
+b     : true <- !g1; a1.
--------------------------------------------------

a. What will be the state of the intention after the execution of action a1?

(NOTE: For the purpose of this exercise I did not bother using +b, but just did an initial goal
of !g1. But the effect should be the same as far as what this is asking for, as best
as I can tell)


--------------------------------------------------
+!g2   : true   <- a4.
--------------------------------------------------
+!g1   : true   <- !g2.
--------------------------------------------------
+b     : true <- !g1; a1.
--------------------------------------------------

So pretty much the same, just a3 is popped off the stack and a4 is up next to execute.
This is consistent with what we actually see, which is that a4 executes after a3.

b. If the agent's plan library includes the following plan:

    -!g1 : true <- a5.
    
   what will be the state of the above intention if action a3 fails? 
   
--------------------------------------------------
-!g1   : true   <- a5.
--------------------------------------------------
+!g2   : true   <- a3; a4.
--------------------------------------------------
+!g1   : true   <- !g2.
--------------------------------------------------
+b     : true <- !g1; a1.
--------------------------------------------------



