package agent;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import example.Env;
import jason.RevisionFailedException;
import jason.asSemantics.Agent;
import jason.asSemantics.Intention;
import jason.asSyntax.Literal;

public class MyAgent extends Agent
{
	private Logger logger = Logger.getLogger( MyAgent.class.getName() );
	
	@Override
	public int buf( Collection<Literal> percepts )
	{
		
		return super.buf( percepts );
	}
	
	@Override
	public List<Literal>[] brf( Literal beliefToAdd, Literal beliefToDel, Intention i ) throws RevisionFailedException
	{
		int cycleNumber = this.ts.getAgArch().getCycleNumber();
		long currentTimeMillis = System.currentTimeMillis();
		logger.info( "[" + cycleNumber + "] [" + currentTimeMillis + "] " + this.ts.getAg().toString() );
			
		if( cycleNumber == 0 )
		{
			try
			{
				Thread.sleep( 14000 );
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}
		
		return super.brf( beliefToAdd, beliefToDel, i );
	}
}
