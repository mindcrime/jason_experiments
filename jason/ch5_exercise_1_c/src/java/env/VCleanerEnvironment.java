package env;

import java.util.logging.Logger;

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;
import jason.environment.grid.Location;
import model.WorldModel;


/* Simple Vacuum Cleaner environment */
public class VCleanerEnvironment extends Environment 
{	
    private Logger logger = Logger.getLogger( VCleanerEnvironment.class.getName() );

    boolean individualizedPerception = true;
    
    WorldModel model; // the model of the grid
    
    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) 
    {
        super.init(args);
        
        this.model = new WorldModel(); 
        
        
        Location initialLocation = model.getAgPos( 0 );
        logger.info(  "Initial starting pos: " + initialLocation.toString() );
        
        
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
    	logger.info(  "adding " + dirtyTemp + " percept" );
        Literal lDirty = Literal.parseLiteral( dirtyTemp );
    	if( this.individualizedPerception)
    	{
    		addPercept( "vcleaner", lDirty );
    	}
    	else
    	{
    		addPercept( lDirty );
    	}
    	
    	
    	
    	Location location = this.model.getAgPos( 0 );
    	logger.info(  "Current location: " + location.toString() );
    	
    	boolean visitedUpperLeft = this.model.getVisitedUpperLeft();
    	if( visitedUpperLeft )
    	{
    		Literal lVisitedUpperLeft = Literal.parseLiteral( "visited_upper_left" );
        	if( this.individualizedPerception)
        	{
        		addPercept( "vcleaner", lVisitedUpperLeft );
        	}
        	else
        	{
        		addPercept( lVisitedUpperLeft );
        	}
    	}
    	
    	int squaresRightAvailable = this.model.getSquaresRightAvailable();
    	logger.info(  "squaresRightAvailable: " + squaresRightAvailable );
    	
    	if( squaresRightAvailable > 0 )
    	{
    		Literal lSquaresRightAvailable = Literal.parseLiteral( "squares_right_available" );
        	if( this.individualizedPerception)
        	{
        		addPercept( "vcleaner", lSquaresRightAvailable );
        	}
        	else
        	{
        		addPercept( lSquaresRightAvailable );
        	}	
    	}
    	
    	int rowsDownAvailable = this.model.getRowsDownAvailable();
    	logger.info(  "rowsDownAvailable: " + rowsDownAvailable );
    	
    	if( rowsDownAvailable > 0 )
    	{
    		Literal lRowsDownAvailable = Literal.parseLiteral( "rows_down_available" );
        	if( this.individualizedPerception)
        	{
        		addPercept( "vcleaner", lRowsDownAvailable );
        	}
        	else
        	{
        		addPercept( lRowsDownAvailable );
        	}	
    		
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
        	case GOTO_UPPER_LEFT:
        		
        		result = this.model.moveToUpperLeft();
        		
        		break;
        		
        	case RIGHT:
        		
        		result = this.model.moveRight();

        		break;
        		
        	case SUCK:
        		
        		// suck dirt from current square
        		result = this.model.suckCurrentSquare();
        		
        		break;
        	
        	case DOWN:
        		
        		// go down a row
        		result = this.model.moveDown();
        		
        		break;
        	
        	case UP:
        		
        		// go up a row
        		// not currently used, but we may need this later?
        		
        		result = this.model.moveUp();
        		
        		break;
        		
        	case LEFT:
        		
        		// go left one square
        		// not currently used, but we may need this later?
        		
        		result = this.model.moveLeft();
        		
        	case FULL_LEFT:
        		
        		// jump all the way to the left edge of the current row
        		result = this.model.moveFullLeft();
        		
        		break;
        		
        	case PRINT_DIRTY:
        		
        		print_dirty();
        		result = true;
        		break;
        		
        	default:
        		
        		// NOP
        		logger.warning(  "unknown action requested" );
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
		int numDirty = this.model.getNumDirty();
		
		return numDirty;
	}
	
	private void print_dirty()
	{
		int numDirty = get_num_dirty();
		logger.info(  "num_dirty: " + numDirty );
	}    
}
