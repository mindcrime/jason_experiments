package model;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import xmpp.XmppMessage;

public class WorldModel
{
    Logger logger = Logger.getLogger( WorldModel.class.getName() );
	
    Queue<XmppMessage> messages = new LinkedBlockingQueue<XmppMessage>();
    
    
    public WorldModel() 
    {   
    }

	public synchronized boolean addMessage( XmppMessage message )
	{
		logger.info( "WorldModel.addMessage: " + message );
		return messages.add( message );
	}
	
	public synchronized Queue<XmppMessage> getMessages()
	{
		Queue<XmppMessage> messagesCopy = new LinkedBlockingQueue<XmppMessage>(this.messages);
		
		this.messages.clear();
		
		return messagesCopy;
	}
	
}
