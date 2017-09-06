package ua.vasylenko.JettyWebSocket;

import java.io.IOException;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import ua.vasylenko.worker.UserWorker;

/**
 * Класс кастомного соккета.
 * @Created by Тёма on 07.08.2017.
 * @version 1.0
 */
@WebSocket
public class MySocket {
    private Session session;
     
    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Connect: " + session.getRemoteAddress().getAddress());
        try {
            this.session = session;
            session.getRemote().sendString("Got your connect message");
        } catch (IOException e) {
            e.printStackTrace();
        }
           
    }
     
    @OnWebSocketMessage
    public void onText(String message) {
        System.out.println("Client query: " + message);
        
        
        UserWorker userWorker = new UserWorker(); 
        System.out.println("Result: " + userWorker.getUserById(Integer.parseInt(message)).getEmail());
        
        try {
            this.session.getRemote().sendString("Result: " + userWorker.getUserById(Integer.parseInt(message)).getEmail());
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*User user = new Messages();
		message.setConnection(connection.toString());
		message.setMessage(data);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(message);
		tx.commit();*/
    }
     
    
    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason);      
    }
    
    @OnWebSocketError
    public void onError(Throwable error){
    	error.printStackTrace();
    }
}
