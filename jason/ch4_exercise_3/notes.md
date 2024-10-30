In the Domestic Robot example, described in Section 3.4, the robot has
three plans to achieve the goal !has(owner,beer) (identified by the labels
@h1, @h2, and @h3). If any of those plans fail, or none is applicable, the robot 
will not achieve the goal and the owner will remain waiting for the beer forever.

To avoid this problem, add a failure handling goal for the goal !has(owner, beer)
that sends a message to the owner, informing him/her about the robot being unable
to achieve the goal.

