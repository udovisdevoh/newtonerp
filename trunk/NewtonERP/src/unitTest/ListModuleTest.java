/**
 * 
 */
package unitTest;

import junit.framework.TestCase;
import modules.testModule.TestModule;
import newtonERP.ListModule;

/**
 * @author djo
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
     * Test method for {@link newtonERP.ListModule#addModule(java.lang.String)}.
     */
    public void testAddModule()
    {
	ListModule.addModule("TestModule");
	assertEquals("add TestModule", 1, ListModule.getAllModules().size());
    }

    /**
     * Test method for
     * {@link newtonERP.ListModule#removeModule(java.lang.String)}.
     */
    public void testRemoveModule()
    {
	ListModule.addModule("TestModule");
	ListModule.removeModule("TestModule");
	assertEquals("remove TestModule", 0, ListModule.getAllModules().size());
    }

    /**
     * Test method for {@link newtonERP.ListModule#getAllModules()}.
     */
    public void testGetAllModules()
    {
	assertNotNull("getallModule", ListModule.getAllModules());
    }

    /**
     * Test method for {@link newtonERP.ListModule#getModule(java.lang.String)}.
     */
    public void testGetModule()
    {
	ListModule.addModule("TestModule");
	try
	{
	    assertTrue("get TestModule",
		    ListModule.getModule("TestModule") instanceof TestModule);
	} catch (Exception e)
	{
	    e.printStackTrace();
	    fail("exception");
	}

    }

    /**
     * Test method for {@link newtonERP.ListModule#initAllModule()}.
     */
    public void testInitAllModule()
    {
	ListModule.initAllModule();
	assertTrue("initallModule", ListModule.getAllModules().size() > 0);
    }

}
