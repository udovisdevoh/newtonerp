/**
 * 
 */
package newtonERP.serveur; 
 // TODO: clean up that file

import static org.junit.Assert.assertNotNull;
import newtonERP.common.ListModule;
import newtonERP.serveur.Servlet;

import org.junit.Before;
import org.junit.Test;

/**
 * The Class ServletTest.
 * 
 * @author CloutierJo
 */
public class ServletTest {

	/** The test servlet. */
	Servlet testServlet;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		testServlet = new Servlet();
		ListModule.initAllModule();
	}

	/**
	 * Test handle full adress.
	 */
	@Test
	public void testHandleFullAdress() {
		// HttpServletRequestWrapper requ = new HttpServletRequestWrapper(null);

		assertNotNull(testServlet.urlToAction("/TestModule/TestAction", null));
	}

	/**
	 * Test handle no action.
	 */
	@Test
	public void testHandleNoAction() {
		assertNotNull(testServlet.urlToAction("/TestModule", null));
	}

	/**
	 * Test handle no module.
	 */
	@Test
	public void testHandleNoModule() {
		assertNotNull(testServlet.urlToAction("", null));
	}
}
