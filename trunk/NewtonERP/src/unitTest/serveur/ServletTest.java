/**
 * 
 */
package unitTest.serveur;

import junit.framework.TestCase;
import newtonERP.ListModule;
import newtonERP.serveur.Servlet;

/**
 * @author djo
 * 
 */
public class ServletTest extends TestCase
{
    Servlet testServlet;

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
	super.setUp();
	testServlet = new Servlet();
	ListModule.initAllModule();
    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
	super.tearDown();
    }

    /**
     * 
     */
    public void testHandleFullAdress()
    {
	assertNotNull(testServlet.urlToAction("/TestModule/TestAction", null));
    }

    /**
     * 
     */
    public void testHandleNoAction()
    {
	assertNotNull(testServlet.urlToAction("/TestModule", null));
    }

    /**
     * 
     */
    public void testHandleNoModule()
    {
	assertNotNull(testServlet.urlToAction("", null));
    }
}
