package modules.testModule;

import newtonERP.module.Module;

/**
 * @author r3lemaypa
 * 
 */
public class TestModule extends Module
{
    public TestModule()
    {
	definitionEntityList.add(new TestEntity());
	actionList.put("TestAction", new TestAction());
	moduleGetterList.put("TestModuleGetter", new TestModuleGetter());
    }
}