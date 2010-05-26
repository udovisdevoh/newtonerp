/**
 * 
 */
package unitTest;

import junit.framework.TestCase;
import modules.testModule.TestModule;
import newtonERP.common.ListModule;

/**
 * @author CloutierJo
 * 
 */
public class ListModuleTest extends TestCase
{

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
	super.setUp();
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
     * Test method for {@link newtonERP.common.ListModule#addModule(java.lang.String)}.
     */
    public void testAddModule()
    {
	ListModule.addModule("TestModule");
	assertEquals(1, ListModule.getAllModules().size());
    }

    /**
     * Test method for
     * {@link newtonERP.common.ListModule#removeModule(java.lang.String)}.
     */
    public void testRemoveModule()
    {
	ListModule.addModule("TestModule");
	ListModule.removeModule("TestModule");
	assertEquals(0, ListModule.getAllModules().size());
    }

    /**
     * Test method for {@link newtonERP.common.ListModule#getAllModules()}.
     */
    public void testGetAllModules()
    {
	assertNotNull(ListModule.getAllModules());
    }

    /**
     * Test method for {@link newtonERP.common.ListModule#getModule(java.lang.String)}.
     */
    public void testGetModule()
    {
	ListModule.addModule("TestModule");
	try
	{
	    assertTrue(ListModule.getModule("TestModule") instanceof TestModule);
	} catch (Exception e)
	{
	    e.printStackTrace();
	    fail("exception");
	}

    }

    /**
     * Test method for {@link newtonERP.common.ListModule#initAllModule()}.
     */
    public void testInitAllModule()
    {
	ListModule.initAllModule();
	assertTrue(ListModule.getAllModules().size() > 0);
    }

}
