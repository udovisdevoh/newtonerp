package modules.testModule;

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

    }

    @Override
    public String getVisibleName()
    {
	return "Module test";
    }
}