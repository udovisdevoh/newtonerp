package NewtonERP.Test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import NewtonERP.Logging.Log;

public class TcpThread extends Thread{
	// the socket where to listen/talk
	private Socket socket;
	private ObjectInputStream Sinput;
	private ObjectOutputStream Soutput;
	private Log logger = new Log();
	                
	TcpThread(Socket socket) {
	    this.socket = socket;
	}
	
	public void run() {
		/* Creating both Data Stream */
	    System.out.println("Thread trying to create Object Input/Output Streams");
	    try	{
	    	// create output first
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
