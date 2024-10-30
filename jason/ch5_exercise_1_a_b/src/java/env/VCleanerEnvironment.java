package env;

import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Logger;

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;


/* Simple Vacuum Cleaner environment */
public class VCleanerEnvironment extends Environment 
{	
    private Logger logger = Logger.getLogger( VCleanerEnvironment.class.getName() );

    // this world only has two possible positions for the vacuum:
    // dirty[0] is the left location, dirty[1] is the right location
    boolean[] dirty = {true, true };

    Random r = new SecureRandom();    
    
    // the current vacuum cleaner position
    int vcPos = r.nextInt(2); // begins in a random location [0,1]
    
    boolean individualizedPerception = true;
    
    
    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) 
    {
        super.init(args);
        
        logger.info(  "Initial starting pos: " + vcPos );
        
        if( args.length > 0 )
        {
        	this.individualizedPerception = Boolean.parseBoolean( args[0] );
        }
        
        logger.info(  "Using individualizedPerception: " + this.individualizedPerception );
        
        updatePercepts();
        
        // KEEP this line of code in here!!!!
        informAgsEnvironmentChanged();
        
    }

    /* Update the agent's percepts based on the current state of the World Model */
    void updatePercepts() 
    {
    
        // dynamically add dirty
        /* 
    	if( r.nextInt( 100 ) < 20 )
        {
        	dirty[r.nextInt( 2 )] = true;
        }
        */
    	
        // clear the percepts of the agents
    	if( this.individualizedPerception )
    	{
    		clearPercepts("vcleaner");        
    	}
    	else
    	{
    		clearPercepts();
    	}
    	
    	
        int numDirty = get_num_dirty();
        String dirtyTemp = "numdirty(" + numDirty + ")";
    	logger.fine(  "adding " + dirtyTemp + " percept" );
        
    	
    	
    	if( this.individualizedPerception )
    	{
    		addPercept( "vcleaner", Literal.parseLiteral( dirtyTemp ) );
    	}
    	else
    	{
    		addPercept( Literal.parseLiteral( dirtyTemp ) );
    	}
    	
        if( numDirty > 0 )
        {
        	// logger.info(  "Adding percept 'dirty'" );
        	
        	if( this.individualizedPerception )
        	{
        		addPercept( "vcleaner", Literal.parseLiteral( "dirty" ) );
        	}
        	else
        	{
        		addPercept( Literal.parseLiteral( "dirty" ) );	
        	}
        	
        }
        else
        {
        	// logger.info(  "Adding percept 'clean'" );
        	if( this.individualizedPerception )
        	{
        		addPercept( "vcleaner", Literal.parseLiteral( "clean" ) );        	
        	}
        	else
        	{
        		addPercept( Literal.parseLiteral( "clean" ) );	
        	}
        	
        }
        
        if( vcPos == 0)
        {
        	// logger.info(  "adding pos(l) percept" );
        	if( this.individualizedPerception )
        	{
        		addPercept( "vcleaner", Literal.parseLiteral( "pos(l)" ) );        	
        	}
        	else
        	{
        		addPercept( Literal.parseLiteral( "pos(l)" ) );	
        	}
        	
        }
        else if ( vcPos == 1)
        {
        	// logger.info(  "adding pos(r) percept" );
        	if( this.individualizedPerception )
        	{
        		addPercept( "vcleaner", Literal.parseLiteral( "pos(r)" ) );        	
        	}
        	else
        	{
        		addPercept( Literal.parseLiteral( "pos(r)" ) );	
        	}
        	
        }        
        else
        {
        	logger.warning(  "WTF? This should NOT happen!!!!" );
        }
    }    
    
    
    @Override
    public boolean executeAction(String agName, Structure action) 
    {    
    	logger.info("executing: " + action );
        
        boolean result = false;

        /* available Actions are 'left', 'right', and 'suck' */
    	
        String actionFunctor = action.getFunctor();
        VacuumAction va = VacuumAction.parse( actionFunctor );
        
        switch( va )
        {
        	case LEFT:
        		
        		// move left if we're in the right position. If we're already
        		// at the left position, do nothing.
        		
        		if( vcPos == 1 )
        		{
        			vcPos = 0;
        		}
        		
        		result = true;
        		
        		break;
        		
        	case RIGHT:

        		// move right if we're in the left position. If we're already
        		// at the right position, do nothing.
        		if( vcPos == 0 )
        		{
        			vcPos = 1;
        		}
        		
        		result = true;

        		break;
        		
        	case SUCK:
        		
        		// suck dirt from current square
        		dirty[vcPos] = false;
        		
        		result = true;
        		
        		break;
        		
        	case PRINT_DIRTY:
        		
        		print_dirty();
        		result = true;
        		break;
        		
        	default:
        		
        		// NOP
        		break;
        }
        
        
        if (result) 
        {
            updatePercepts();
            informAgsEnvironmentChanged();
            
            try 
            {
                Thread.sleep(200);
            } 
            catch( Exception e )
            {}
        }

        return result; // the action was executed with success
    }

	private int get_num_dirty()
	{
		int numDirty = 0;
		for( boolean posDirty : dirty )
		{
			if( posDirty )
			{
				numDirty++;
			}
		}
	
		return numDirty;
	}
	
	private void print_dirty()
	{
		int numDirty = get_num_dirty();
		logger.info(  "num_dirty: " + numDirty );
	}    
}
