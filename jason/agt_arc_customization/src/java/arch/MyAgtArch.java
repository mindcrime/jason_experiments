package arch;

import java.util.logging.Logger;

import example.Env;
import jason.architecture.AgArch;
import jason.asSemantics.ActionExec;

public class MyAgtArch extends AgArch
{
    private Logger logger = Logger.getLogger( MyAgtArch.class.getName() );
	
	@Override
	public void act( ActionExec action )
	{
		logger.info(  "action: " + action.toString() );
		
		super.act( action );
	}
	
	@Override
	public void reasoningCycleStarting()
	{
		logger.info(  "Reasoning Cycle Starting!" );
		super.reasoningCycleStarting();
	}
	
	@Override
	public void reasoningCycleFinished()
	{
		logger.info(  "Reasoning Cycle Finished!" );
		super.reasoningCycleFinished();
	}
	
	@Override
	public void actionExecuted( ActionExec act )
	{
		logger.info(  "Action executed!\n" + act );
		super.actionExecuted( act );
	}
	
	
	
}
