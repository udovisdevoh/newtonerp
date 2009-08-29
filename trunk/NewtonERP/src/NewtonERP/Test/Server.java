package NewtonERP.Test;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	// the socket used by the server
	private ServerSocket serverSocket;
	private Log logger = new Log();
	
	// server constructor
	Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
			logger.log("Server waiting for client on port " + serverSocket.getLocalPort(), Log.State.INFO);
			while (true) {
				Socket socket = serverSocket.accept();  // accept connection
				logger.log("New client asked for a connection", Log.State.INFO);
				TcpThread t = new TcpThread(socket);    // make a thread of it
				logger.log("Starting a thread for a new Client", Log.State.INFO);
				t.start();
			}
		}
		catch (Exception ex) {
			logger.log(ex.getMessage(), Log.State.ERROR);
		}
	}
	
	public static void main(String[] args) {
		new Server(1500);
	}
}
