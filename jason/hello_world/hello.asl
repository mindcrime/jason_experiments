/* A simple AgentSpeak program that prints "Hello, World!" to the console. */

/* Initial beliefs here */
started.

/* the plan section */

// the + operator means something like "When you come to believe ..."
// so +x means "When you come to believe x"
// and +started means "When you come to believe started"
// 'started' is an initial belief, so it is always true
// so this plan will always be executed

+started <- .print("Hello, World!\n").
