# Chapter 4, Exercise 4

Consider a multi-agent system composed of two agents. One agent, called 'sender', sends
100 messages to another, called 'rec' (the receiver). The 'rec' agent simply sums the
number of received messages and, after receiving the last message, prints out how many
messages it received.

The sender AgentSpeak code is: 

---
!send(100). // initial goal

+!send(0) : true <- true. // stop sending
+!send(X) : true <- .send( rec, tel, v1(X)); !send(X-1).

The receiver code is:

---
sum(0).
+v1(1) : sum(S) <- .print( S+1). // the last tell from the sender
+v1(X) : sum(S) <- NS = S + 1; -+sum(NS).

When we execute these two agents, the rec output is usually no less than 10,
but it is never 100, the expected value. Try to fix this bug in the rec agent.

