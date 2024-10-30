package env;

public enum VacuumAction 
{
	LEFT,
	RIGHT,
	SUCK,
	PRINT_DIRTY,
	UNKNOWN;
	
	public static VacuumAction parse( String input )
	{
		if( input.equalsIgnoreCase( "left" ))
		{
			return LEFT;
		}
		else if( input.equalsIgnoreCase( "right" ))
		{
			return RIGHT;
		}
		else if( input.equalsIgnoreCase( "suck" ))
		{
			return SUCK;
		}
		else if( input.equalsIgnoreCase(  "print_dirty" ))
		{
			return PRINT_DIRTY;
		}
		else
		{
			return UNKNOWN;
		}
	}
}
