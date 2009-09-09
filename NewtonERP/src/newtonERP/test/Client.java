package newtonERP.test;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import newtonERP.logging.Logger;


/**
 * 
 * @author r3hallejo
 *
 * Test client for TCP/IP protocol test in Java. To be revised this is not final
 * 
 * For now it is only sending a string and the server is returning in uppercase, thats all
 * We will have to adapt the client and the server eventually to pass Objects instead of a 
 * string. The method used is writeObject so we can send barely anything we want
 */
public class Client {
	private ObjectInputStream input;	// to read the socket
	private ObjectOutputStream output;	// to write on the socket
	private Socket socket;
	private Logger logger = new Logger();

	// Constructor connection receiving a socket number
	Client(int port) {
		try {
			socket = new Socket("localhost", port);
		}
		catch(Exception ex) {
			logger.log("Error connectiong to server : " + ex.getMessage(), Logger.State.ERROR);
		}
		
		logger.log("Connection accepted " +
				socket.getInetAddress() + ":" +
				socket.getPort(), Logger.State.INFO);
		
		/* Creating both Data Stream */
		try
		{
			InputStream is = socket.getInputStream();
			input  = new ObjectInputStream(is);
			
			OutputStream os = socket.getOutputStream();
			output = new ObjectOutputStream(os);
		}
		catch (Exception ex) {
			logger.log(ex.getMessage(), Logger.State.ERROR);
		}
	}
	
	// For testing. Will eventually be removed
	public static void main(String[] arg) {
		new Client(1500);
	}
}
