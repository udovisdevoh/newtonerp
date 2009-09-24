package test.servletTest;

import java.net.URL;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Embedded;

public class EmbeddedTomcat
{

    private String path = null;
    private Embedded embedded = null;
    private Host host = null;

    /**
     * Default Constructor
     * 
     */
    public EmbeddedTomcat()
    {

    }

    /**
     * Basic Accessor setting the value of the context path
     * 
     * @param path
     *            - the path
     */
    public void setPath(String path)
    {

	this.path = path;
    }

    /**
     * Basic Accessor returning the value of the context path
     * 
     * @return - the context path
     */
    public String getPath()
    {

	return path;
    }

    /**
     * This method Starts the Tomcat server.
     */
    public void startTomcat() throws Exception
    {

	Engine engine = null;
	// Set the home directory
	System.setProperty("catalina.home", getPath());

	// Create an embedded server
	embedded = new Embedded();
	// print all log statments to standard error
	// embedded.setDebug(0);
	// embedded.setLogger(new SystemOutLogger());

	// Create an engine
	engine = embedded.createEngine();
	engine.setDefaultHost("localhost");

	// Create a default virtual host
	host = embedded.createHost("localhost", "/webapps");
	engine.addChild(host);

	// Create the ROOT context
	Context context = embedded.createContext("", "/ROOT/");
	host.addChild(context);

	// Install the assembled container hierarchy
	embedded.addEngine(engine);

	// Assemble and install a default HTTP connector
	Connector connector = embedded
		.createConnector("localhost", 8080, false);
	embedded.addConnector(connector);
	// Start the embedded server
	embedded.start();
    }

    /**
     * This method Stops the Tomcat server.
     */
    public void stopTomcat() throws Exception
    {
	// Stop the embedded server
	embedded.stop();
    }

    /**
     * Registers a WAR with the container.
     * 
     * @param contextPath
     *            - the context path under which the application will be
     *            registered
     * @param warFile
     *            - the URL of the WAR to be registered.
     */
    public void registerWAR(String contextPath, URL warFile) throws Exception
    {

	if (contextPath == null)
	    throw new Exception("Invalid Path : " + contextPath);
	if (contextPath.equals("/"))
	    contextPath = "";
	if (warFile == null)
	    throw new Exception("Invalid WAR : " + warFile);

	// Deployer deployer = (Deployer) host;
	// Context context = deployer.findDeployedApp(contextPath);

	// if (context != null)
	// throw new Exception("Context " + contextPath + " Already Exists!");
	// deployer.install(contextPath, warFile);
    }

    /**
     * Unregisters a WAR from the web server.
     * 
     * @param contextPath
     *            - the context path to be removed
     */
    public void unregisterWAR(String contextPath) throws Exception
    {

	Context context = host.map(contextPath);
	if (context != null)
	    embedded.removeContext(context);
	else
	    throw new Exception("Context does not exist for named path : "
		    + contextPath);
    }

    public static void main(String args[])
    {

	try
	{

	    EmbeddedTomcat tomcat = new EmbeddedTomcat();
	    tomcat.setPath("src/test/servletTest");
	    tomcat.startTomcat();

	    // URL url = new URL("file:D:/jakarta-tomcat-4.0.1"
	    // + "/webapps/onjava");
	    // tomcat.registerWAR("/onjava", url);

	    // Thread.sleep(1000000);

	    // tomcat.stopTomcat();

	    // System.exit(0);
	} catch (Exception e)
	{

	    e.printStackTrace();
	}
    }
}
