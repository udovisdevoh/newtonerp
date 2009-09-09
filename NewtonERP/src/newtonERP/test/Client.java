package newtonERP.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
	ObjectInputStream Sinput;		// to read the socker
	ObjectOutputStream Soutput;	// towrite on the socket
	Socket socket;

	// Constructor connection receiving a socket number
	Client(int port) {
		// we use "localhost" as host name, the server is on the same machine
		// but you can put the "real" server name or IP address
		try {
			socket = new Socket("localhost", port);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Connection accepted " +
				socket.getInetAddress() + ":" +
				socket.getPort());

		/* Creating both Data Stream */
		try
		{
			Sinput  = new ObjectInputStream(socket.getInputStream());
			Soutput = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		// now that I have my connection
		String test = "aBcDeFgHiJkLmNoPqRsTuVwXyZ";
		// send the string to the server
		System.out.println("Client sending \"" + test + "\" to serveur");
		try {
			Soutput.writeObject(test);
			Soutput.flush();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		// read back the answer from the server
		String response;
		try {
			response = (String) Sinput.readObject();
			System.out.println("Read back from server: " + response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try{
			Sinput.close();
			Soutput.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}  

	public static void main(String[] arg) {
		new Client(1500);
	}
}
