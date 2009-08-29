package NewtonERP.Test;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import NewtonERP.Logging.Log;

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
	private Log logger = new Log();

	// Constructor connection receiving a socket number
	Client(int port) {
		try {
			socket = new Socket("localhost", port);
		}
		catch(Exception ex) {
			logger.log("Error connectiong to server : " + ex.getMessage(), Log.State.ERROR);
		}
		
		logger.log("Connection accepted " +
				socket.getInetAddress() + ":" +
				socket.getPort(), Log.State.INFO);
		
		/* Creating both Data Stream */
		try
		{
			InputStream is = socket.getInputStream();
			input  = new ObjectInputStream(is);
			
			OutputStream os = socket.getOutputStream();
			output = new ObjectOutputStream(os);
		}
		catch (Exception ex) {
			logger.log(ex.getMessage(), Log.State.ERROR);
		}
		// now that I have my connection
		String test = "aBcDeFgHiJkLmNoPqRsTuVwXyZ";
		logger.log("Client sending \"" + test + "\" to serveur", Log.State.INFO);
		
		// send the string to the server
		try {
			output.writeObject(test);
			output.flush();
		}
		catch(Exception ex) {
			logger.log(ex.getMessage(), Log.State.ERROR);
		}
		// read back the answer from the server
		String response;
		try {
			response = (String) input.readObject();
			logger.log("Read back from server: " + response, Log.State.INFO);
		}
		catch(Exception ex) {
			logger.log(ex.getMessage(), Log.State.ERROR);
		}
		
		try{
			input.close();
			output.close();
		}
		catch(Exception ex) {
			logger.log(ex.getMessage(), Log.State.ERROR);
		}
	}  

	// For testing. Will eventually be removed
	public static void main(String[] arg) {
		new Client(1500);
	}
}
