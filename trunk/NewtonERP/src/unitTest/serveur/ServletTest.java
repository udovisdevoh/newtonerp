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
	testServlet = new Servlet(8080);
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
     * Test method for
     * {@link newtonERP.serveur.Servlet#handle(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, int)}
     * .
     */
    public void testHandle()
    {
	ListModule.initAllModule();
	assertNotNull("test full adress,no parameter", testServlet.urlToAction(
		"/TestModule/TestAction/TestModuleGetter", null));
    }
}
