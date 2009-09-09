package newtonERP.test;

import java.net.ServerSocket;
import java.net.Socket;

import newtonERP.logging.Logger;


/**
 * 
 * @author r3hallejo
 *
 * Test server class using TCP/IP protocol. To be revised this is not final
 */
public class Server {
	// the socket used by the server
	private ServerSocket serverSocket;
	private Logger logger = new Logger();

	// server constructor
	Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
			logger.log("Server waiting for client on port " + serverSocket.getLocalPort(), Logger.State.INFO);
			Socket socket = serverSocket.accept();  // accept connection
			logger.log("New client asked for a connection", Logger.State.INFO);
		}
		catch (Exception ex) {
			logger.log(ex.getMessage(), Logger.State.ERROR);
		}
	}
}
