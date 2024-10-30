import java.util.logging.Logger;

import jason.asSyntax.Literal;
import jason.environment.Environment;

public class SimpleEnv extends Environment
{
	Logger logger = Logger.getLogger(SimpleEnv.class.getName());
	
	public static final Literal ok = Literal.parseLiteral("oracle_killed");
	
	
    @Override
    public void init(String[] args) 
    {
    	Thread thread = new Thread( new Runnable() {
    		@Override
    		public void run()
    		{
    			while( true )
    			{
    				try
    				{
    					Thread.sleep(  6000 );
    				}
    				catch( Exception e )
    				{
    					e.printStackTrace();
    				}
    				
    				System.out.println( "adding percept \"oracle_killed\"" );
    				addPercept( "agent_smith", ok );
    				
    				informAgsEnvironmentChanged();
    				
    				try
    				{
    					Thread.sleep(  500 );
    				}
    				catch( Exception e )
    				{
    					e.printStackTrace();
    				}
    				
    				System.out.println( "removing percept \"oracle_killed\"" );
    				removePercept( "agent_smith", ok );
    				
    			}
    			
    		}
    		
    	} );
    	
    	thread.start();
    	
    }
}
