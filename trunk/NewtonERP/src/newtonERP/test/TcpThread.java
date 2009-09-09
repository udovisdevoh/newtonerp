package newtonERP.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 
 * @author r3hallejo
 *
 * Test of a TCP thread to allow multiple client connections to the server
 * To be revised this is only for testing purpose this is not final for now it is only 
 * returning a string in uppercase be the method readObjects lets us read barely 
 * anything we want
 */
public class TcpThread extends Thread{
	private Socket socket;
	private ObjectInputStream Sinput;
	private ObjectOutputStream Soutput;
	                
	TcpThread(Socket socket) {
	    this.socket = socket;
	}
	
	/**
	 * Runs the new tcp/ip thread for the newly connected client
	 */
	public void run() {
		/* Creating both Data Stream */
		System.out.println("Thread trying to create Object Input/Output Streams");
		try
		{
			// create output first
			Soutput = new ObjectOutputStream(socket.getOutputStream());
			Soutput.flush();
			Sinput  = new ObjectInputStream(socket.getInputStream());
		}
		catch (IOException e) {
			System.out.println("Exception creating new Input/output Streams: " + e);
			return;
		}
		System.out.println("Thread waiting for a String from the Client");
		// read a String (which is an object)
		try {
			String str = (String) Sinput.readObject();
			str = str.toUpperCase();
			Soutput.writeObject(str);
			Soutput.flush();
		}
		catch (IOException e) {
			System.out.println("Exception reading/writing  Streams: " + e);
			return;				
		}
		// will surely not happen with a String
		catch (ClassNotFoundException o) {				
		}
		finally {
			try {
				Soutput.close();
				Sinput.close();
			}
			catch (Exception e) {					
			}
		}
	}
}
