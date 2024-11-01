package xmpp;

import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;

public class XmppConnectionListener implements ConnectionListener
{

	@Override
	public void connecting( XMPPConnection connection )
	{
		// TODO Auto-generated method stub
		ConnectionListener.super.connecting( connection );
	}

	@Override
	public void connected( XMPPConnection connection )
	{
		// TODO Auto-generated method stub
		ConnectionListener.super.connected( connection );
	}

	@Override
	public void authenticated( XMPPConnection connection, boolean resumed )
	{
		// TODO Auto-generated method stub
		ConnectionListener.super.authenticated( connection, resumed );
	}

	@Override
	public void connectionClosed()
	{
		// TODO Auto-generated method stub
		ConnectionListener.super.connectionClosed();
	}

	@Override
	public void connectionClosedOnError( Exception e )
	{
		// TODO Auto-generated method stub
		ConnectionListener.super.connectionClosedOnError( e );
	}
	
}
