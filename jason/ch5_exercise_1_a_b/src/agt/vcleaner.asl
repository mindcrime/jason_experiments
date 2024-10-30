!printdirty.

+!printdirty : true <- print_dirty.

// we're in the left position
@c1[atomic] +!cleanroom : ( pos(l) & dirty ) <- .print( "Cleaning room, starting from LEFT!" ); suck; right; print_dirty.

// we're in the right position
@c2[atomic] +!cleanroom : ( pos(r) & dirty ) <- .print( "Cleaning room, starting from RIGHT!" ); suck; left; print_dirty.

+!cleanroom : not dirty <- .print( "NOP - this should never happen!" ).

+!exit : true <- action.exit.

+numdirty(X) : (X == 0) <- .print( "All squares are clean!" ). // !exit.
+numdirty(X) : (X >= 1) <- !cleanroom.
