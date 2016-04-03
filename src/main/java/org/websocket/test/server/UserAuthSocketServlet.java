package org.websocket.test.server;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns="/auth")
public class UserAuthSocketServlet extends WebSocketServlet{

	@Override
	public void configure(WebSocketServletFactory factory) {
		
	      factory.register(UserAuthWebSocket.class);
		
	}

}
