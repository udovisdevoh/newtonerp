package newtonERP.test.sslServer;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

public class SSLClient {
	 public
     static
void
     main(String[] arstring) {
 try {
     SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
     SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("localhost", 9999);

     InputStream inputstream = System.in;
     InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
     BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

     OutputStream outputstream = sslsocket.getOutputStream();
     OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
     BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);

     String string = null;
     while ((string = bufferedreader.readLine()) != null) {
         bufferedwriter.write(string + '\n');
         bufferedwriter.flush();
     }
 } catch (Exception exception) {
     exception.printStackTrace();
 }
}

}
