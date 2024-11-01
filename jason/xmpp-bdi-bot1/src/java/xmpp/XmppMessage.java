package xmpp;

import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.packet.Message;
import org.jxmpp.jid.EntityBareJid;

public class XmppMessage
{
	private EntityBareJid from;
	private Message message;
	private Chat chat;
	
	
	
	public XmppMessage( EntityBareJid from, Message message, Chat chat )
	{
		this.from = from;
		this.message = message;
		this.chat = chat;
	}
	
	public EntityBareJid getFrom()
	{
		return from;
	}
	
	public void setFrom( EntityBareJid from )
	{
		this.from = from;
	}
	
	public Message getMessage()
	{
		return message;
	}
	
	public void setMessage( Message message )
	{
		this.message = message;
	}
	
	public Chat getChat()
	{
		return chat;
	}
	
	public void setChat( Chat chat )
	{
		this.chat = chat;
	}
	
}
