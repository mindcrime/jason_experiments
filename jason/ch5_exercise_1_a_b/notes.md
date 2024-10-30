# Chapter 5, Exercise 1

a. Write the AgentSpeak code for the agent controlling the vacuum cleaner

b. Change the given environment to add perception only for the robot, rather than
   all agents; i.e., change it from 'global' to 'individualized' perception.
   
c. Increase the environment model to a 10x10 grid using the same design pattern
   as the Domestic Robot System.
   
d. Implement the AgentSpeak code for the agent in the 10x10 environment and
   try more instances of the vacuum cleaner agents in the system.
   
   
---

a. DONE in basic form, as of commit #90353cf5bedf3ab40b0d03e5ee081ecf6285341c

b.   To prove this point we'll add a second agent that would otherwise trigger of some
     of the same percepts if we weren't using individualized perception. Then we'll toggle
     the individualization on/off to see the new agent either start or stop receiving those
     percepts and reacting to them.

    DONE as of commit #ab7e1b51f8ea2d02239fb2f5a0a0d116dfc62c89

     
c.   Will probably need to fork this into a new project for this part

d.   Same - probably will need that second project, if not a third project still

