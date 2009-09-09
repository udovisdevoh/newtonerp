package newtonERP.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author r3hallejo
 *
 * Test server class using TCP/IP protocol. To be revised this is not final
 */
public class Server {
	// the socket used by the server
	private ServerSocket serverSocket;
	// server constructor
	Server(int port) {
			
		/* create socket server and wait for connection requests */
		try 
		{
			serverSocket = new ServerSocket(port);
			System.out.println("Server waiting for client on port " + serverSocket.getLocalPort());
			while(true) 
			{
				Socket socket = serverSocket.accept();  // accept connection
				System.out.println("New client asked for a connection");
				TcpThread t = new TcpThread(socket);    // make a thread of it
				System.out.println("Starting a thread for a new Client");
				t.start();
			}
		}
		catch (IOException e) {
			System.out.println("Exception on new ServerSocket: " + e);
		}
	}		

//	you must "run" server to have the server run as a console application
	public static void main(String[] arg) {
		// start server on port 1500
		new Server(1500);
	}	
}
