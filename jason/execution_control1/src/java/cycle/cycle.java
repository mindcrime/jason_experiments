package cycle;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Literal;
import jason.asSyntax.StringTermImpl;
import jason.asSyntax.Term;

public class cycle extends DefaultInternalAction
{
	@Override
	public Object execute( TransitionSystem ts, Unifier un, Term[] args ) throws Exception
	{
		return un.unifies(args[0], new StringTermImpl(Integer.toString( ts.getAgArch().getCycleNumber())));
	}


}
