package modules.testModule;

import modules.testModule.actions.TestAction;
import newtonERP.module.Module;

/**
 * @author r3lemaypa Guillaume cloutierJo
 * 
 */
public class TestModule extends Module
{
    /**
     * constructeur
     * @throws Exception remonte
     */
    public TestModule() throws Exception
    {
	super();
	setDefaultAction(new TestAction());
	setVisibleName("Module test");
	setVisible(false);
    }
}