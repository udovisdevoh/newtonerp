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
     * @throws Exception remonte
     */
    @Before
    public void setUp() throws Exception
    {
	ListModule.initAllModule();
	Orm.connect();
    }

    /**
     * @throws Exception remonte
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
     * @throws Exception remonte
     */
    @Test
    public void testRightCheck() throws Exception
    {
	Hashtable<String, String> rightParam = new Hashtable<String, String>();
	Module module = ListModule.getModule("UserRightModule");
	rightParam.put("name", "admin");
	rightParam.put("module", "UserRightModule");
	rightParam.put("action", "GetUser");
	assertNotNull(module.doAction("RightCheck", rightParam));
    }

}
