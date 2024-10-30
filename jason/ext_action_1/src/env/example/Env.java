
package example;


// Environment code for project ext_action_1
import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

import java.util.logging.*;


public class Env extends Environment
{
	private Logger logger = Logger.getLogger( "ext_action_1." + Env.class.getName() );
	
	public static final Literal rebootMatrix  = Literal.parseLiteral("reboot(matrix)");
	
	
	/** Called before the MAS execution with the args informed in .mas2j */
	@Override
	public void init( String[] args )
	{
		super.init( args );
		// updatePercepts();
	}

	@Override
	public boolean executeAction( String agName, Structure action )
	{
		logger.info( "executing: " + action.toString() );
		
		boolean actionResult = false;
		
		if( action.equals( rebootMatrix ) )
		{
			actionResult = rebootMatrixAction();
		}
		else
		{
			logger.info("Not implemented yet");
		}
		
		if( actionResult )
		{ 
			// you may improve this condition
			informAgsEnvironmentChanged();
		}
		
		return actionResult;
	}

	// Implementing the rebootMatrix action
	public boolean rebootMatrixAction()
	{
		logger.info( "Rebooting matrix..." );
		return true;
	}
	
	
	/** Called before the end of MAS execution */
	@Override
	public void stop()
	{
		super.stop();
	}
}
