package ua.vasylenko.JettyServer;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

import ua.vasylenko.JettyWebSocket.SocketHandler;

/**
 * Класс запуска сервера Jetty.
 * @Created by Тёма on 07.08.2017.
 * @version 1.0
 */
public class JettyStarter {
	
	public static void main(String[] args) {
		// Создаем сервер и задаем порт.
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(7070);
        server.addConnector(connector);
  
        // add first handler.
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{ "index.html" });
        resource_handler.setResourceBase(".");
  
        HandlerList handlers = new HandlerList();
        // first element  is webSocket handler, second element is first handler.
        handlers.setHandlers(new Handler[] {new SocketHandler()});
          
        server.setHandler(handlers);
  
        try {
            server.start();
            server.join();
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
	}
}
