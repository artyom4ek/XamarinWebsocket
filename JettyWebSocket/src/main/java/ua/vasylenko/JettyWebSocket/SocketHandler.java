package ua.vasylenko.JettyWebSocket;

import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * Класс SocketHandler.
 * @Created by Тёма on 07.08.2017.
 * @version 1.0
 */
public class SocketHandler extends WebSocketHandler {
 
    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.register(MySocket.class);
    }  
}
