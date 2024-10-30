package agent;

import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import javax.management.Attribute;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import jason.asSemantics.ActionExec;
import jason.asSemantics.Agent;
import jason.asSemantics.Event;
import jason.asSemantics.Intention;
import jason.asSemantics.Message;
import jason.asSemantics.Option;
import jason.runtime.Settings;
import mbean.AgentStats;
import mbean.AgentStatsMBean;

public class JmxAgent extends Agent
{
	private Logger logger = Logger.getLogger( JmxAgent.class.getName() );

	private boolean jmxDebug = false;
	
	@Override
	public void initAg()
	{
		logger.info( "init() method invoked!" );
		
		Settings settings = this.ts.getSettings();
		
		StringBuilder sb = new StringBuilder();
		Map<String,Object> userParameters = settings.getUserParameters();
		userParameters.forEach( (key, value) -> {
			sb.append( "" + key + ": " + value + "\n\n" );
		} );
		
		logger.info(  "settings: " + sb.toString() );
		
		// logger.info( "jmxDebug class: " + userParameters.get( "jmxDebug" ).getClass().getName() );
		
		this.jmxDebug = Boolean.parseBoolean( userParameters.get( "jmxDebug" ).toString() );
		
		super.initAg();
	}
	
	
	@Override
	public Event selectEvent( Queue<Event> events )
	{

		// Iterator eventIter = events.iterator();
		StringBuilder sb = new StringBuilder();
		for( Event event : events )
		{
			sb.append(  "********\n" + event.toString() + "\n********\n" );
		}
		
		logger.info(  "selectEvent called. Stack of Events is currently: \n" + sb.toString() );
		
		return super.selectEvent( events );
	}
	
	
	@Override
	public ActionExec selectAction( Queue<ActionExec> actions )
	{
		
		StringBuilder sb = new StringBuilder();
		
		for( ActionExec action : actions )
		{
			sb.append( "********\n" + action.toString() + "\n********\n" );
		}
		
		logger.info(  "selectAction called. Actions Queue is currently: \n" + sb.toString() );
		
		return super.selectAction( actions );
	}
	
	@Override
	public Option selectOption( List<Option> options )
	{
		StringBuilder sb = new StringBuilder();
		
		for( Option option : options )
		{
			sb.append(  "********\n" + option.toString() + "\n********\n" );
		}
		
		logger.info(  "selectOption called. Options List is currently: \n" + sb.toString() );
		
		return super.selectOption( options );
	}
	
	@Override
	public Message selectMessage( Queue<Message> messages )
	{
		StringBuilder sb = new StringBuilder();
		
		for( Message msg : messages )
		{
			sb.append( "********\n" + msg.toString() + "\n********\n" );
		}
		
		logger.info(  "selectMessage called. Message Queue is currently: \n" + sb.toString() );
		
		return super.selectMessage( messages );
	}
	
	
	@Override
	public Intention selectIntention( Queue<Intention> intentions )
	{
		Queue<Intention> intentionsCopy = new LinkedBlockingQueue<Intention>(intentions);
		
		Intention selectedIntention = super.selectIntention( intentions );
		
		try
		{
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

			ObjectName agentStatsMBeanName = new ObjectName("org.fogbeam.example.jason.jmx:type=AgentStats");
		
			MBeanInfo agentStatsMBeanInfo = null;
			try
			{
				agentStatsMBeanInfo = mbs.getMBeanInfo( agentStatsMBeanName );
			}
			catch( InstanceNotFoundException infe )
			{
				// infe.printStackTrace();
				logger.info(  "Existing MBean instance not found!" );
			}
			
			AgentStatsMBean agentStatsMBean = null;

			int cycleNumber = this.ts.getAgArch().getCycleNumber();
			
			StringBuilder sb = new StringBuilder();
			intentionsCopy.forEach( i -> { sb.append( i.toString() + "\n\n" ); } );
	
			
			if( agentStatsMBeanInfo == null )
			{
				agentStatsMBean = new AgentStats();
				agentStatsMBean.setCurrentCycle( cycleNumber );
				agentStatsMBean.setIntentions(sb.toString());
				agentStatsMBean.setSelectedIntention( selectedIntention.getId() + " " + selectedIntention.toString() );
				mbs.registerMBean(agentStatsMBean, agentStatsMBeanName);
			}
			else
			{
				
				MBeanAttributeInfo[] attrs = agentStatsMBeanInfo.getAttributes();
				
				logger.info( "Attributes: " );
				for( MBeanAttributeInfo attrInfo : attrs )
				{
					Object attrExisting = mbs.getAttribute( agentStatsMBeanName, attrInfo.getName() );
					
					// logger.info( "[existing] -> [attribute] " + attrInfo.getName() +  " [type] " + attrInfo.getType() + " [value] " + attrExisting.toString() );
					
				}
				
				Attribute cycleNumAttrNew = new Attribute("CurrentCycle", cycleNumber);
				
				logger.info( "[updated] -> [attribute] " + cycleNumAttrNew.getName() + " [value] " + cycleNumAttrNew.getValue());				

				mbs.setAttribute( agentStatsMBeanName, cycleNumAttrNew );
			
				Attribute intentionsNew = new Attribute( "Intentions", sb.toString());
				mbs.setAttribute( agentStatsMBeanName, intentionsNew );
				
				
			}
		
			if( jmxDebug )
			{
				
				boolean proceed = false;
				while( !proceed )
				{
					// block here until proceed is true
					try
					{
						Thread.sleep( 1500 );
					}
					catch( InterruptedException ie )
					{}	
					
					proceed = (boolean)mbs.getAttribute( agentStatsMBeanName, "Proceed" );
					mbs.setAttribute( agentStatsMBeanName, new Attribute( "Proceed", false) );
				}
				
				logger.info(  "moving on..." );
			}
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		return selectedIntention;
	}
}
