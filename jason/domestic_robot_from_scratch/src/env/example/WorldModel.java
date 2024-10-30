package example;

import java.util.logging.Logger;

import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

public class WorldModel extends GridWorldModel
{
	Logger logger = Logger.getLogger("domestic_robot_from_scratch." + WorldModel.class.getName() );
	
	// constants for the grid objects
    public static final int FRIDGE = 16;
    public static final int OWNER  = 32;

    // the grid size
    public static final int GSize = 7;

    boolean fridgeOpen   = false; // whether the fridge is open
    boolean carryingBeer = false; // whether the robot is carrying beer
    int sipCount        = 0; // how many sip the owner did
    int availableBeers  = 2; // how many beers are available

    Location lFridge = new Location(0,0);
    Location lOwner  = new Location(GSize-1,GSize-1);
    
    public WorldModel() 
    {
        // create a 7x7 grid with one mobile agent
        super(GSize, GSize, 1);
        
        // initial location of robot (column 3, line 3)
        // ag code 0 means the robot
        setAgPos(0, GSize/2, GSize/2);

        // initial location of fridge and owner
        add(FRIDGE, lFridge);
        add(OWNER, lOwner);
        
    }
    
    boolean moveTowards(Location dest) 
    {
        Location r1 = getAgPos(0);
        logger.fine(  "r1 (current agpPos) = " + r1 );
        
        if( r1.x < dest.x )
        {        
        	r1.x++; 
        }
        else if( r1.x > dest.x )
        { 
        	r1.x--; 
        }
        
        if( r1.y < dest.y )
        {        
        	r1.y++; 
        }
        else if( r1.y > dest.y )
        {  
        	r1.y--; 
        }
        
        logger.fine(  "Setting agtPos = " + r1 );
        setAgPos(0, r1); // move the robot in the grid

        // repaint the fridge and owner locations
        if (view != null) 
        {
            view.update(lFridge.x,lFridge.y);
            view.update(lOwner.x,lOwner.y);
        }
        
        return true;
    }

    boolean openFridge() 
    {
        if(!fridgeOpen)
        {
            fridgeOpen = true;
            logger.info( "Opened fridge" );
            return true;
        } 
        else 
        {
            return false;
        }
    }

    boolean getBeer() 
    {
    	logger.info(  "Available beers = " + availableBeers );
    	
        if( fridgeOpen && availableBeers > 0 && !carryingBeer )
        {
            availableBeers--;
            carryingBeer = true;
            
            if (view != null)
            {
                view.update(lFridge.x,lFridge.y);
            }
            logger.info(  "Got beer from fridge. Available beers now: " + availableBeers );   
            return true;
        } 
        else 
        {
            return false;
        }
    }    
    
    boolean closeFridge()
    {
        if (fridgeOpen) 
        {
            fridgeOpen = false;
            logger.info(  "Closed fridge" );
            return true;
        }
        else 
        {
            return false;
        }
    }   
    
    boolean handInBeer() 
    {
        if(carryingBeer)
        {
            sipCount = 10;
            carryingBeer = false;
            if (view != null) 
            {
                view.update(lOwner.x,lOwner.y); 
            }
            
            return true;
        } 
        else 
        {
            return false;
        }
    }

    boolean sipBeer() 
    {
        if (sipCount > 0) 
        {
            sipCount--;
            if (view != null) 
            { 
                view.update(lOwner.x,lOwner.y);
            }
            
            return true;
        } 
        else 
        {
            return false;
        }
    }

    boolean addBeer(int n) 
    {
        availableBeers += n;
        
        if (view != null)
        {
            view.update(lFridge.x,lFridge.y);
        }
        
        return true;
    }    
}