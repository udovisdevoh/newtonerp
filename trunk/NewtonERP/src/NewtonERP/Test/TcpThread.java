package NewtonERP.Test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import NewtonERP.Logging.Log;

/**
 * 
 * @author r3hallejo
 *
 * Test of a TCP thread to allow multiple client connections to the server
 * To be revised this is only for testing purpose this is not final
 */
public class TcpThread extends Thread{
	private Socket socket;
	private ObjectInputStream Sinput;
	private ObjectOutputStream Soutput;
	
	// TODO : Finish the implementation of the logger into this class
	private Log logger = new Log();
	                
	TcpThread(Socket socket) {
	    this.socket = socket;
	}
	
	public void run() {
		
		/* Creating both Data Stream */
	    System.out.println("Thread trying to create Object Input/Output Streams");
	    try	{
	        Soutput = new ObjectOutputStream(socket.getOutputStream());
	        Soutput.flush();
	        Sinput  = new ObjectInputStream(socket.getInputStream());
	    }
	    catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	    System.out.println("Thread waiting for a String from the Client");
	    
	    // read a String (which is an object)
	    try {
	    	String str = (String) Sinput.readObject();
	        str = str.toUpperCase();
	        Soutput.writeObject(str);
	        Soutput.flush();
	    }
	    catch (Exception ex) {
	    	ex.printStackTrace();
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
