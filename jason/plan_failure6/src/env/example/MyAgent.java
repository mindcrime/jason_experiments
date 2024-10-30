package example;

import java.util.List;
import java.util.logging.Logger;

import jason.asSemantics.Agent;
import jason.asSemantics.Option;

public class MyAgent extends Agent
{
	private Logger logger = Logger.getLogger("jason_agent_blank." + MyAgent.class.getName() );
	
	
	@Override
	public boolean hasCustomSelectOption()
	{
		return true;
	}
	
	
	@Override
	public Option selectOption( List<Option> options )
	{
		logger.warning( "*************\n\n\nIn our SelectOption method!\n\n\n****************************");
		
		logger.warning( "Options: " + options );
		
		return super.selectOption( options );
	}
}
