package env;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.ReconnectionManager;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.util.TLSUtils;
import org.jxmpp.jid.impl.DomainpartJid;

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;
import model.WorldModel;
import xmpp.ChatMessageListener;
import xmpp.XmppConnectionListener;
import xmpp.XmppMessage;

public class Env extends Environment 
{
    // common literals
    // public static final Literal of  = Literal.parseLiteral("open(fridge)");
	
    private Logger logger = Logger.getLogger( Env.class.getName() );

    WorldModel model; // the model of the grid    
    
    AbstractXMPPConnection connection = null;
    
	String xmppUsername;
	
	String xmppPassword;
	
	String xmppResource;
	
	String xmppServiceName;
	
	String xmppHost;

    
    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) 
    {
        super.init(args);
        
        this.model = new WorldModel();
        
        xmppUsername = 		args[0];
        xmppPassword = 		args[1];
        xmppResource = 		args[2];
        xmppServiceName = 	args[3];
        xmppHost = 			args[4];
        
        try
        {
	        XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
			configBuilder.setUsernameAndPassword( xmppUsername, xmppPassword ); 
			configBuilder.setResource(xmppResource);
			configBuilder.setXmppDomain( xmppServiceName );
			configBuilder.setHost( xmppHost );
        
			// accept all certificate - just for testing  
			TLSUtils.acceptAllCertificates(configBuilder);
			
			connection = new XMPPTCPConnection(configBuilder.build());
        
			ConnectionListener connectionListener = new XmppConnectionListener();
			
			// make sure auto reconnect is turned on.
			ReconnectionManager.getInstanceFor(connection).enableAutomaticReconnection();
			
			BlockingQueue<String> inputMessageQueue = new LinkedBlockingQueue<String>();
			
			logger.info( "Connecting to XMPP server" );
			
			connection.addConnectionListener( connectionListener );
			
			// Connect to the )server
			connection.connect();
	
			// Log into the server
			connection.login();
			logger.info( "Logged in to XMPP server" );
			
			ChatManager chatManager = ChatManager.getInstanceFor(connection);			
			
			ChatMessageListener messageListener = new ChatMessageListener(this);
			
			chatManager.addIncomingListener( messageListener );
			
			
			
			
        }
		catch( Exception e )
		{
			logger.severe( "Error configuring XMPP connection: " + e.getMessage() );
		}
        
                
        updatePercepts();
        informAgsEnvironmentChanged();
    }

    @Override
    public boolean executeAction(String agName, Structure action) 
    {
        
    	logger.info("executing: " + action );
        
        boolean result = false;

        // TODO: implement Action and capture result
    	
    	
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
        clearPercepts();

        // get messages from the model
        // and add percepts for each message
        
        Queue<XmppMessage> messages = model.getMessages();
		messages.forEach( message -> {
			addPercept( Literal.parseLiteral( "message('" + message.getFrom()
					+ "', '" + message.getMessage().getBody() + "')" ) );
		} );
        
    }
    
    public WorldModel getModel()
	{
		return this.model;
	}
    
	public void notifyNewMessage()
	{
		updatePercepts();
		informAgsEnvironmentChanged();
	}
	
}
