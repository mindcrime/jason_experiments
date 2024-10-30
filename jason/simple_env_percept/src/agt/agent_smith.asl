started.

+started <- .print( "Hello, Mr. Anderson.").

+oracle_killed <- .time( HH, MM, SS ); .print( "The Oracle is dead, at: ", HH, ":", MM, ":", SS).

// IF you don't do the informAgsEnvironmentChanged() in the Environment class
// then it appears that you need one of these two calls to get reasonable behavior
// but note that just having the call isn't enough. There seems to be a race condition
// or something, as the timing in the wait() will determine if the behavior is "correct" 
// or not. In this example, wait(50) gives you the sporadic/random re-triggering
// while wait(500) gives you an even "every 6'ish seconds"
//   .wait(500); .abolish(oracle_killed). // -oracle_killed[source(percept)]. // 
