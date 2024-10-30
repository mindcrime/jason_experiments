package example;

import java.util.Arrays;
import java.util.logging.Logger;

import jason.control.ExecutionControl;
import jason.control.ExecutionControlInfraTier;

public class MyExecutionControl extends ExecutionControl
{
	private Logger logger = Logger.getLogger("[execution_control] " + MyExecutionControl.class.getName() );
	
	int cycleTimeout;
	
	@Override
	public void init( String[] args )
	{
		logger.info(  "init: args -> " + Arrays.toString( args ) );
		this.cycleTimeout = Integer.parseInt( args[0] );
		
		super.init( args );
	}
	
	@Override
	public int getCycleNumber()
	{
		int cycleNumber = super.getCycleNumber();
		
		// logger.info( "cycleNumber: " + cycleNumber );
		
		return cycleNumber;
	}

	@Override
	public ExecutionControlInfraTier getExecutionControlInfraTier()
	{
		
		ExecutionControlInfraTier tier = super.getExecutionControlInfraTier();
		
		// logger.info(  "ExecutionControlInfraTier: " + tier );
		
		return tier;
	}

	@Override
	public void receiveFinishedCycle( String agName, boolean breakpoint, int cycle )
	{
		// logger.info( agName );
		
		if( agName.equals( "agent2" ))
		{
			logger.info(  "receiveFinishedCycle: [agName]: " + agName + ", [breakpoint]: " + breakpoint + ", [cycle]: " + cycle  );
		}
		super.receiveFinishedCycle( agName, breakpoint, cycle );
	}

	@Override
	protected void startNewCycle()
	{
		// logger.info(  "startNewCycle" );
		super.startNewCycle();
	}
	
	@Override
	protected void allAgsFinished()
	{
		// logger.info(  "allAgsFinished" );
		super.allAgsFinished();
	}
	
	@Override
	protected int getCycleTimeout()
	{
		// int cycleTimeout = super.getCycleTimeout();
		
		// logger.info(  "cycleTimeout: " + cycleTimeout );
		
		return this.cycleTimeout;
	}
}


