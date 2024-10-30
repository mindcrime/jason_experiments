package example;

import java.util.logging.Logger;

import org.apache.commons.lang3.RandomUtils;

// Environment code for project domestic_robot_from_scratch
import jason.asSyntax.Literal;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.Structure;
import jason.environment.Environment;
import jason.environment.grid.Location;

public class Env extends Environment 
{
    // common literals
    public static final Literal of  = Literal.parseLiteral("open(fridge)");
    public static final Literal clf = Literal.parseLiteral("close(fridge)");
    public static final Literal gb  = Literal.parseLiteral("get(beer)");
    public static final Literal hb  = Literal.parseLiteral("hand_in(beer)");
    public static final Literal sb  = Literal.parseLiteral("sip(beer)");
    public static final Literal hob = Literal.parseLiteral("has(owner,beer)");

    public static final Literal af = Literal.parseLiteral("at(robot,fridge)");
    public static final Literal ao = Literal.parseLiteral("at(robot,owner)");
	
	
    private Logger logger = Logger.getLogger("domestic_robot_from_scratch." + Env.class.getName() );

    WorldModel model; // the model of the grid    
    
    
    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) 
    {
        super.init(args);
        
        logger.info( "init() arg[0] = " + args[0] );
        
        model = new WorldModel();

        if (args.length == 1 && args[0].equals("gui")) 
        {
            WorldView view  = new WorldView(model);
            model.setView(view);
        }
        
        updatePercepts();
    }

    @Override
    public boolean executeAction(String agName, Structure action) 
    {
        
    	logger.info("executing: " + action );
        
    	boolean result = false;
    	if( action.equals( of )) // open fridge
    	{
    		// open fridge
    		result = model.openFridge();
    	}
    	else if( action.equals(gb) )
    	{
            result = model.getBeer();
        }
    	else if( action.equals(  clf  ))
    	{
    		result = model.closeFridge();
    	}
    	else if( action.getFunctor().equals("move_towards") )
    	{
    		// move towards what?
    		String l = action.getTerm(0).toString();
            
    		logger.info(  "l = " + l  );
    		
    		Location dest = null;
            if (l.equals("fridge")) 
            {
                dest = model.lFridge;    		
            }
            else if( l.equals( "owner"  ))
            {
            	dest = model.lOwner;
            }
            
            // now that we know our destination
            
            try 
            {
            	logger.fine(  "Moving 1 step towards dest: " + dest.toString() );
                result = model.moveTowards(dest);
            } 
            catch (Exception e) 
            {
            	result = false;
                e.printStackTrace();
            }
    	}
    	else if( action.equals( hb ))
    	{
    		
    		// generate a random number and have this fail about 2/3'rds of the time
    		// so we can see the failure handling method kick in
    		float randomValue = RandomUtils.secure().randomFloat( 1f, 100f );
    		if( randomValue < 66.66666667 )
    		{
    			result = false;
    		}
    		else
    		{
    			// hand owner beer
    			result = model.handInBeer();
    		}
    	}
    	else if( action.equals( sb ))
    	{
    		result = model.sipBeer();
    	}
    	else if ( action.getFunctor().equals( "deliver" ))
    	{
    		// wait 4 seconds to finish "deliver"
            try 
            {
                Thread.sleep(4000);
                result = model.addBeer( (int)((NumberTerm)action.getTerm(1)).solve());
            } 
            catch (Exception e) 
            {
                logger.info("Failed to execute action deliver!"+e);
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

    	
        return result; // the action was executed with success
    }
    
    /** creates the agents percepts based on the HouseModel */
    void updatePercepts() 
    {
        // clear the percepts of the agents
        clearPercepts("robot");
        clearPercepts("owner");

        // get the robot location
        Location lRobot = model.getAgPos(0);

        // add agent location to its percepts
        if (lRobot.equals(model.lFridge)) 
        {
            addPercept("robot", af);
        }
        else if( lRobot.equals( model.lOwner )) 
        {
        	addPercept( "robot", ao );
        }
    
        // add beer "status" to the percepts
        if ( model.fridgeOpen ) 
        {
            addPercept("robot", Literal.parseLiteral("stock(beer,"+model.availableBeers+")"));
        }
        
        if( model.sipCount > 0)
        {
            addPercept("robot", hob);
            addPercept("owner", hob);
        }        
        
    }
    
}