package agent;

import java.util.Queue;
import java.util.logging.Logger;

import arch.MyAgtArch;
import jason.asSemantics.ActionExec;
import jason.asSemantics.Agent;
import jason.asSemantics.Event;
import jason.asSemantics.Intention;

public class CustomAgent extends Agent
{
    private Logger logger = Logger.getLogger( CustomAgent.class.getName() );
	
	@Override
	public Event selectEvent( Queue<Event> events )
	{
		
		logger.info(  "selectEvent invoked" );
		
		return super.selectEvent( events );
	}
	
	@Override
	public ActionExec selectAction( Queue<ActionExec> actions )
	{
		logger.info(  "selectAction invoked" );
		return super.selectAction( actions );
	}
	
	@Override
	public Intention selectIntention( Queue<Intention> intentions )
	{
		logger.info( "selectIntention invoked" );
		return super.selectIntention( intentions );
	}
	
}
