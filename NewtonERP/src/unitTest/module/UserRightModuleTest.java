package unitTest.module;

/**
 * 
 */

import java.util.Hashtable;

import junit.framework.TestCase;
import newtonERP.common.ListModule;
import newtonERP.module.Module;
import newtonERP.orm.Orm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author CloutierJo
 * 
 */
public class UserRightModuleTest extends TestCase
{

    /**
     */
    @Before
    public void setUp()
    {
	ListModule.initAllModule();
	Orm.connect();
    }

    /**
     */
    @After
    public void tearDown()
    {
	// TODO : Complete me
    }

    /**
     * Test method for
     * {@link modules.userRightModule.actions.RightCheck#doAction(newtonERP.module.AbstractEntity, java.util.Hashtable)}
     * .
     * 
     */
    @Test
    public void testRightCheck()
    {
	Hashtable<String, String> rightParam = new Hashtable<String, String>();
	Module module = ListModule.getModule("UserRightModule");
	rightParam.put("name", "admin");
	rightParam.put("module", "UserRightModule");
	rightParam.put("action", "GetUser");
	assertNotNull(module.doAction("RightCheck", rightParam));
    }

}
