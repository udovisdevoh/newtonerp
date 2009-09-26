package test.servletTest;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Embedded;

/**
 * http://www.blogjava.net/jarod
 * 
 * @author jarod
 */
public class EmbdedTomCat
{

    // private static final Logger logger = LoggerFactory
    // .getLogger(TomcatServer.class);

    public static void main(String[] args)
    {
	try
	{
	    new EmbdedTomCat();
	} catch (Throwable t)
	{
	    t.printStackTrace();
	    // logger.error("", t);
	}
    }

    private Embedded tomcat;

    private String catalinaHome;

    private String projectHome;

    public EmbdedTomCat()
    {
	initConf();

	tomcat = new Embedded();
	tomcat.setCatalinaHome(catalinaHome);
	Engine engine = tomcat.createEngine();
	Host host = tomcat.createHost("localhost", projectHome);
	host.addChild(tomcat.createContext("", ""));
	Context context = tomcat.createContext("/webapp1", catalinaHome
		+ "webapps");
	context.setReloadable(true);
	host.addChild(context);
	engine.addChild(host);
	engine.setDefaultHost("localhost");
	tomcat.addEngine(engine);
	tomcat.addConnector(tomcat.createConnector("localhost", 47098, false));
	registerShutdownHook();
	try
	{
	    tomcat.start();
	    Thread.sleep(Long.MAX_VALUE);
	} catch (Exception e)
	{
	    throw new RuntimeException(e);
	}
    }

    private void initConf()
    {
	Properties properties = new Properties();
	/*
	 * try { properties.load(embded2.class
	 * .getResourceAsStream("/tomcat-conf.properties")); catalinaHome =
	 * properties.getProperty("catalina.home");
	 * 
	 * File f = new File("."); projectHome = f.getAbsolutePath(); } catch
	 * (IOException e) { throw new RuntimeException(e); }
	 */
	catalinaHome = "src/test/servletTest/";
	File f = new File(".");
	try
	{
	    projectHome = f.getCanonicalPath();
	} catch (IOException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	System.out.println(projectHome);

    }

    private void registerShutdownHook()
    {
	Runtime.getRuntime().addShutdownHook(new Thread()
	{
	    @Override
	    public void run()
	    {
		try
		{
		    tomcat.stop();
		} catch (LifecycleException e)
		{
		    throw new RuntimeException(e);
		}
	    }
	});
    }
}
