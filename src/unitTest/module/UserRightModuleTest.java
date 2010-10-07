
package unitTest.module;

/**
 * @author CloutierJo
 */
public class UserRightModuleTest extends TestCase {
  @Before
  public void setUp() {
	ListModule.initAllModule();
	Orm.connect();
  }

  @After
  public void tearDown() {
	// TODO : Complete me
  }

  /**
   * Test method for
   * {@link modules.userRightModule.actions.RightCheck#doAction(newtonERP.module.AbstractEntity, java.util.Hashtable)}
   * .
   */
  @Test
  public void testRightCheck() {
	Hashtable<String, String> rightParam = new Hashtable<String, String>();
	Module module = ListModule.getModule("UserRightModule");
	rightParam.put("name", "admin");
	rightParam.put("module", "UserRightModule");
	rightParam.put("action", "GetUser");
	assertNotNull(module.doAction("RightCheck", rightParam));
  }

}
