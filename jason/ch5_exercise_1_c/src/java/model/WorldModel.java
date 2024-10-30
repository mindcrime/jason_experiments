package model;

import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Logger;

import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

public class WorldModel extends GridWorldModel
{
    Logger logger = Logger.getLogger( WorldModel.class.getName() );
	
	// constants for the grid objects
    // public static final int FRIDGE = 16;
    // public static final int OWNER  = 32;

    // the grid size
    public static final int GSize = 10;

    Random rand = new SecureRandom();
    
    // keep track of which squares are dirty
    boolean[][] dirty;

	private boolean visitedUpperLeft;
    
    
    public WorldModel() 
    {
        // create a square grid of size GSize^2 with one mobile agent
        super(GSize, GSize, 1);
        
        this.dirty = new boolean[GSize][GSize];
        
        // ag code 0 means the (sole) vacuum cleaner
        int initialX, initialY;
        initialX = rand.nextInt(GSize);
        initialY = rand.nextInt(GSize);
        
        logger.info(  "Initial position for agent 0: (" + initialX + ", " + initialY + ")" );
        
        setAgPos(0, initialX, initialY);
        
        // let a random number of squares be dirty, but maybe not all of them for now?
        int initialNumDirty = rand.nextInt( (GSize*GSize) - GSize );
        
        logger.info( "In init, expected initialNumDirty: " + initialNumDirty );
        
        // now apply dirt to that many squares
        int dirtyAppliedCount = 0;
        int dirtyApplyIterationCount = 0;
        while( dirtyAppliedCount < initialNumDirty )
        {
        	if( dirtyApplyIterationCount++ > 15000 )
        	{
        		throw new RuntimeException("Took too long to apply dirty!");
        	}
        	
        	// pick a random square. If it's already dirty, continue
        	// if not, apply dirt and increment the dirtyAppliedCount
        	
        	int sampleXPos = rand.nextInt(GSize-1);
        	int sampleYPos = rand.nextInt(GSize-1);
        	
        	if( dirty[sampleXPos][sampleYPos] == true )
        	{
        		// this spot is already dirt: NOP
        		continue;
        	}
        	else
        	{
        		dirty[sampleXPos][sampleYPos] = true;
        		dirtyAppliedCount++;
        	}
        }
        
        logger.info(  "Used " + dirtyApplyIterationCount + " iterations to apply dirty" );
        
        int numDirty = getNumDirty();
        
        // double check that this matches out desired dirty count
        logger.info(  "At end of init(), numDirty = " + numDirty );
    }

    public int getNumDirty()
    {
    	int numDirty = 0;
    	
    	for( int x = 0; x < (GSize-1); x++ )
    	{
    		for( int y = 0; y < (GSize-1); y++ )
    		{
    			if( dirty[x][y] == true ) 
    			{
    				numDirty++;
    			}
    		}
    	}
    	
    	return numDirty;
    }

	public boolean moveToUpperLeft()
	{
		setAgPos( 0, 0, 0 );
		this.visitedUpperLeft = true;
		return true;
	}

	public boolean moveRight()
	{
		Location vcPos = this.getAgPos( 0 );
		int maxRight = (GSize - 1);
		
		if( vcPos.x < maxRight )
		{
			this.setAgPos( 0, (vcPos.x + 1 ), vcPos.y );
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean moveLeft()
	{
		Location vcPos = this.getAgPos( 0 );
		
		if( vcPos.x > 0 )
		{
			setAgPos( 0, (vcPos.x - 1), vcPos.y );
			return true;
		}
		else
		{	
			return false;
		}
	}
	
	public boolean moveUp()
	{
		Location vcPos = this.getAgPos( 0 );
		
		if( vcPos.y > 0 )
		{
			setAgPos( 0, vcPos.x, (vcPos.y - 1) );
			return true;
		}
		else
		{
			return false;
		}
	}
    
	public boolean moveDown()
	{
		Location vcPos = this.getAgPos( 0 );
		
		int maxDown = (GSize - 1);
		
		if( vcPos.y < maxDown )
		{
			this.setAgPos( 0, vcPos.x, (vcPos.y + 1) );
			return true;
		}
		else
		{
			return false;
		}		
	}

	public boolean moveFullLeft()
	{
		Location vcPos = this.getAgPos( 0 );
		
		setAgPos( 0, 0, vcPos.y);
		
		return true;
	}
	
	public boolean suckCurrentSquare()
	{
		Location vcPos = this.getAgPos( 0 );
		
		if( ( vcPos.x >= 0 && vcPos.x <= (GSize-1)) && ( vcPos.y >= 0 && vcPos.y <= (GSize-1)) )
		{
			dirty[vcPos.x][vcPos.y] = false;
			return true;
		}
		else
		{
			return false;
		}
	}  
	
	public int getSquaresRightAvailable()
	{
		Location vcPos = getAgPos(0);
    	int x = vcPos.x;
    	
		return (GSize-1) - x;
	}
	
	public int getRowsDownAvailable()
	{
		Location vcPos = getAgPos(0);
    	int y = vcPos.y ;
    	
    	int rowsDownAvailable = ((GSize-1) - y);
    	
    	logger.info(  "in getRowsDownAvailable() - vcPos.y = " + y + ", and (GSize-1) = " + (GSize-1) + ", and rowsDownAvailable = " + rowsDownAvailable );
    	
		return rowsDownAvailable;

	}

	public boolean getVisitedUpperLeft()
	{
		return this.visitedUpperLeft;
	}
	
}
