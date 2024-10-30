package action;


import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;

// import java.text.SimpleDateFormat;
// import java.util.Date;


public class exit extends DefaultInternalAction
{

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception
    {
        System.exit(0);

        return null;
    }    
    
}
