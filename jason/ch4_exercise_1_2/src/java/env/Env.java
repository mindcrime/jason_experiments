package env;

import java.rmi.RemoteException;
import java.util.logging.Logger;

import arch.MyAgtArch;
import jason.asSemantics.Circumstance;
import jason.asSyntax.Structure;
import jason.environment.Environment;
import jason.runtime.Settings;
import model.WorldModel;
import model.WorldView;

public class Env extends Environment 
{
    // common literals
    // public static final Literal of  = Literal.parseLiteral("open(fridge)");
	
    private Logger logger = Logger.getLogger( Env.class.getName() );

    WorldModel model; // the model of the grid    
    
    private boolean fail_a3 = false;
    
    
    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) 
    {
        super.init(args);
        
        if( args.length > 0 )
        {
        	logger.info( "init() arg[0] = " + args[0] );
        }
        else
        {
        	logger.info(  "init() invoked!"  );
        }
        
        model = new WorldModel();

        if (args.length == 1 && args[0].equals("gui")) 
        {
            WorldView view  = new WorldView(model);
            model.setView(view);
        }

        if( args.length > 0 )
        {
        	this.fail_a3 = Boolean.parseBoolean( args[0] );
        }
        
        logger.info(  "Set this.fail_a3 to: " + this.fail_a3 );
        
        updatePercepts();
    }

    @Override
    public boolean executeAction(String agName, Structure action) 
    {
        
    	logger.info("executing: " + action );
        	
    	try
    	{
    		
    		Circumstance currentCircumstance = this.getEnvironmentInfraTier().getRuntimeServices().getAgentSnapshot( agName ).getTS().getC();
    		logger.info( "********\ncurrentCircumstance: " + currentCircumstance.toString() + "\n********\n");
    		
    		// logger.info( "pendingActions: " + currentCircumstance.getPendingActions() );
    		// logger.info( "pendingEvents: " + currentCircumstance.getPendingEvents() );
    		// logger.info( "pendingIntentions: " + currentCircumstance.getPendingIntentions() );
    		// Queue<Intention> runningIntentions = currentCircumstance.getRunningIntentions();    	
    		// logger.info(  "current intentions: " + runningIntentions.toString() );
    		
    	}
    	catch( Exception e)
    	{
    		e.printStackTrace();
    	}
    	
        boolean result = true; // for now assume all actions execute succesfully
        
        try
        {
        	// give us just a little pause to simulate actually doing something
        	Thread.sleep(  500 );
        }
        catch( InterruptedException ie )
        {
        	ie.printStackTrace();
        }

        // would implement action here if we were doing anything real
        if( action.getFunctor().equalsIgnoreCase( "disable_cycle_logging" ))
        {
        	
        	try
        	{
        		MyAgtArch agent = (MyAgtArch)this.getEnvironmentInfraTier().getRuntimeServices().getAgentSnapshot( agName ).getTS().getAgArch();
        		agent.disableDetailedCycleLogging();
        	}
        	catch( RemoteException re )
        	{
        		logger.severe( "RemoteException encountered: " + re.getMessage() );
        	}
        	
        }
        else if( action.getFunctor().equalsIgnoreCase( "a3" ))
        {
        	if( this.fail_a3)
        	{
        		result = false;
        	}
        	else
        	{
        		result = true;
        	}
        }
        
        if (result) 
        {
            updatePercepts();
            informAgsEnvironmentChanged();
            try 
            {
                Thread.sleep(100);
            } 
            catch( Exception e )
            {}
        }

    	
        return result; // was the action successful?
    }
    
    /** creates the agents percepts based on the WorldModel */
    void updatePercepts() 
    {
        // clear the percepts of the agents
        clearPercepts("agent1");
        
        /* 
        if( model.something > 0)
        {
            addPercept("agent1", whatever );
            addPercept("agent2", whatever );
        }  
        */      
        
    }
    
}
