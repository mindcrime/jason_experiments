package xmpp;

import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jxmpp.jid.EntityBareJid;

import env.Env;
import model.WorldModel;

public class ChatMessageListener implements IncomingChatMessageListener
{
	Env env;
	WorldModel model;
	
	public ChatMessageListener( Env env )
	{
		this.env = env;
		this.model = env.getModel();
	}
	
	@Override
	public void newIncomingMessage( EntityBareJid from, Message message, Chat chat )
	{
		// add the newly received message to the model
		// model.addMessage( from, message.getBody() );
		XmppMessage newMessage = new XmppMessage( from, message, chat );
	
		model.addMessage( newMessage );
		
		env.notifyNewMessage();
	}
}