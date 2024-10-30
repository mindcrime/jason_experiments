

/* A simple AgentSpeak program that prints "Hello, World!" to the console. */

/* Initial beliefs here */
started.
agentspeakisawesome.    // a goal, but is not associated with any plan, so will cause no action
jasonisthebest.         // a goal, but is not associated with any plan, so will cause no action



/* the plan section */

// the + operator means something like "When you come to believe ..."
// so +x means "When you come to believe x"
// and +started means "When you come to believe started"
// 'started' is an initial belief, so it is always true
// so this plan will always be executed

// use a semicolon ';' to separate multiple actions in the plan
+started <- .print("Hello, World!\n"); .print( "This is BOB\n" ).
