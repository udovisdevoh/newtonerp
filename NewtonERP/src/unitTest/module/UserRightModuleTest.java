package unitTest.module;

/**
 * 
 */

import java.util.Hashtable;

import junit.framework.TestCase;
import newtonERP.ListModule;
import newtonERP.module.Module;
import newtonERP.module.ModuleException;
import newtonERP.orm.Orm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author djo
 * 
 */
public class UserRightModuleTest extends TestCase
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
	ListModule.initAllModule();
	Orm.connect();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {
	// TODO : Complete me
    }

    /**
     * Test method for
     * {@link modules.userRightModule.actions.RightCheck#doAction(newtonERP.module.AbstractEntity, java.util.Hashtable)}
     * .
     * 
     * @throws ModuleException
     */
    @Test
    public void testRightCheck() throws ModuleException
    {
	Hashtable<String, String> rightParam = new Hashtable<String, String>();
	Module module = ListModule.getModule("UserRightModule");
	rightParam.put("name", "admin");
	rightParam.put("module", "UserRightModule");
	rightParam.put("action", "GetUser");
	assertNotNull(module.doAction("RightCheck", rightParam));
    }

}
