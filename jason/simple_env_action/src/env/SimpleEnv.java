import java.util.logging.Logger;

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;

public class SimpleEnv extends Environment
{
	Logger logger = Logger.getLogger(SimpleEnv.class.getName());
	
	public static final Literal ko  = Literal.parseLiteral("kill_oracle");
	public static final Literal rm = Literal.parseLiteral( "reboot_matrix" );
	
	@Override
	public void init( String[] args )
	{
		// NOP for now.
		
	}
	
	@Override
	public boolean executeAction( String agName, Structure action )
	{
		// logger.info("["+agName+"] doing: "+action);		
		
		boolean result = false;
		
		if( action.equals(ko) ) 
		{ 
			System.out.println( "Agent [" + agName + "] executing Action: ["+action.getFunctor() + "]");
			result = true;
        } 
		else if(action.equals(rm) ) 
		{
			logger.info( "Agent [" + agName + "] executing Action: ["+action.getFunctor() + "]");
			result = true;
        } 
		else if( action.getFunctor().equals("hack_zion_mainframe") ) 
		{
			logger.info( "Agent [" + agName + "] executing Action: ["+action.getFunctor() + "]");
			result = false;
		}		
		else
		{
			logger.info( "Request for unknown Action: [" + action + "] by Agent: [" + agName + "]" );
		}
		
		return result;
	}
}
