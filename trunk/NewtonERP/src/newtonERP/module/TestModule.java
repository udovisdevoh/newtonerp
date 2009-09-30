package newtonERP.module;

import newtonERP.module.actions.TestAction;
import newtonERP.module.moduleGetters.TestModuleGetter;
import newtonERP.orm.testClasses.TestEntity;

/**
 * @author r3lemaypa
 * 
 */
public class TestModule extends Module
{
    public TestModule()
    {
	definitionEntityList.add(new TestEntity());
	actionList.put("L'action qui ne fait rien", new TestAction());
	moduleGetterList.put("Module getter qui ne fait rien",
		new TestModuleGetter());
    }
}