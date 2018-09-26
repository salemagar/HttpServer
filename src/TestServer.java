import java.io.IOException;
import java.io.OutputStream;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.net.InetAddress;

public class TestServer {

    public static void main(String[] args) throws Exception { 
    	
    	int portnum = 9000;
    	
    	/*Creating a HttpServer object with the portnum(9000) and a backlog of zero */
        HttpServer server = HttpServer.create(new InetSocketAddress(portnum),0);
        
        /*creates a mapping between the url path of the application with the httphandler*/ 
        server.createContext("/", new Handler());
        
        server.setExecutor(null); // creates a default executor
    	server.start(); //starts the server
    	
    	/*Gets local host address and stores only IP address in a String type variable*/
    	InetAddress iAddress = InetAddress.getLocalHost();
        String currentIp = iAddress.getHostAddress();
    	
        /*Prints server IP address, port number and a message on the server side*/
    	System.out.println("Current IP address : " +currentIp);
        System.out.println("Current Port Number : " +portnum);
        System.out.println("The server has started successfully!");
    }
    
    
    	/*creates a static handler class that can be directly accessed from the main function */
    public static class Handler implements HttpHandler {
    	
        public void handle(HttpExchange h) throws IOException { //throws IOException if any error occurs while handling input
            String message = "The server is running without any problem"; //Message to be shown to client
            h.sendResponseHeaders(200, message.length());//response code and the length of message to be sent
            
            /*writes in the output stream, converts the message into array of bytes and closes the stream.*/
            OutputStream out = h.getResponseBody();
            out.write(message.getBytes());
            out.close();
        }
    }

}