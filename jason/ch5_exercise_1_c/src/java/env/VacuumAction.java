package env;

public enum VacuumAction 
{
	GOTO_UPPER_LEFT,
	FULL_LEFT,
	LEFT,
	RIGHT,
	UP,
	DOWN,
	SUCK,
	PRINT_DIRTY,
	UNKNOWN;
	
	public static VacuumAction parse( String input )
	{
		if( input.equalsIgnoreCase( "goto_upper_left" ))
		{
			return GOTO_UPPER_LEFT;
		}
		else if( input.equalsIgnoreCase( "full_left" ))
		{
			return FULL_LEFT;
		}
		else if( input.equalsIgnoreCase( "left" ))
		{
			return LEFT;
		}
		else if( input.equalsIgnoreCase( "right" ))
		{
			return RIGHT;
		}
		else if( input.equalsIgnoreCase( "up" ))
		{
			return UP;
		}
		else if( input.equalsIgnoreCase( "down" ))
		{
			return DOWN;
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
