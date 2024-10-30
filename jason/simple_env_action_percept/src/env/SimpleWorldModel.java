
/* a simple model of the world for a simple Jason program */
public class SimpleWorldModel
{
	private boolean oracleKilled = false;
	
	public boolean isOracleKilled()
	{
		return oracleKilled;
	}
	
	public boolean killOracle()
	{
		oracleKilled = true;
		
		return true;
	}
	
	
}
