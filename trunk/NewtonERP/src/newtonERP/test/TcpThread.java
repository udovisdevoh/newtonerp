package newtonERP.test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import newtonERP.logging.Logger;


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
	
	// TODO : Finish the implementation of the logger into this class
	private Logger logger = new Logger();
	                
	TcpThread(Socket socket) {
	    this.socket = socket;
	}
	
	/**
	 * Runs the new tcp/ip thread for the newly connected client
	 */
	public void run() {
		/* Creating both Data Stream */
	    logger.log("Thread trying to create Object Input/Output Streams", Logger.State.INFO);
	    
	    try	{
	        Soutput = new ObjectOutputStream(socket.getOutputStream());
	        Soutput.flush();
	        Sinput  = new ObjectInputStream(socket.getInputStream());
	    }
	    catch (Exception ex) {
	    	logger.log(ex.getMessage(), Logger.State.ERROR);
	    }
	    
	    logger.log("Thread waiting for a String from the Client", Logger.State.INFO);
	    
	    // read a String (which is an object)
	    try {
	    	String str = (String) Sinput.readObject();
	        str = str.toUpperCase();
	        Soutput.writeObject(str);
	        Soutput.flush();
	    }
	    catch (Exception ex) {
	    	logger.log(ex.getMessage(), Logger.State.ERROR);
	    }
	    finally {
	    	try {
	    		Soutput.close();
	            Sinput.close();
	        }
	        catch (Exception ex) {
	        	logger.log(ex.getMessage(), Logger.State.ERROR);
	        }
	    }
	}
}
