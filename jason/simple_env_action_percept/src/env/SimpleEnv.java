

import java.util.logging.Logger;

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;
import jason.environment.grid.Location;

public class SimpleEnv extends Environment
{
	Logger logger = Logger.getLogger(SimpleEnv.class.getName());
	
	SimpleWorldModel model = new SimpleWorldModel();
	
	public static final Literal ok = Literal.parseLiteral("oracle_killed");
	
	@Override
	public void init( String[] args )
	{
		// nothing to do here
		updatePercepts();
	}
	
	   /** creates the agents percepts based on the HouseModel */
    void updatePercepts() 
    {
        // clear the percepts of the agents
        clearPercepts("agent_smith");

        // add agent location to its percepts
        if( model.isOracleKilled() ) 
        {
            addPercept("agent_smith", ok);
        } 
        else
        {
        	// if no, remove any existing percept to that effect??
        	// no, because we called clearPercepts above, so any lingering percepts
        	// should already have been removed.
        	
        }
    }	
	
    @Override
    public boolean executeAction(String ag, Structure action) 
    {
        logger.info("[" + ag + "] doing: " + action );
    
        boolean result = false;
        
        if( action.getFunctor().equals("kill_oracle") )
        {
        	result = model.killOracle();
        }
        
        if (result) 
        {
            updatePercepts();
            try 
            {
                Thread.sleep(100);
            } 
            catch (Exception e)
            {}
        }
        
        return result;    
    }
}
