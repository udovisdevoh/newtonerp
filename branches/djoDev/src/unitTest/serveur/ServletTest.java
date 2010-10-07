/**
 * 
 */
package unitTest.serveur;

import junit.framework.TestCase;
import newtonERP.common.ListModule;
import newtonERP.serveur.Servlet;

/**
 * @author CloutierJo
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
    protected void setUp()
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
    protected void tearDown()
    {
	super.tearDown();
    }

    /**
     * 
     */
    public void testHandleFullAdress()
    {
	// HttpServletRequestWrapper requ = new HttpServletRequestWrapper(null);

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
