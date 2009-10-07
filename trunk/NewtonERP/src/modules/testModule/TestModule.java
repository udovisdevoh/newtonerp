package modules.testModule;

import modules.testModule.actions.TestAction;
import modules.testModule.entityDefinitions.TestEntity;
import newtonERP.module.Module;

/**
 * @author r3lemaypa Guillaume cloutierJo
 * 
 */
public class TestModule extends Module
{
    /**
     * constructeur
     */
    public TestModule()
    {
	super();

	addDefinitinEntity(new TestEntity());
	// TODO: on doit revoir notre interface Ientity, elle est probablement
	// néscéssaire
	// addDefinitinEntity(new TestActionableEntity());

	addAction(new TestAction(), true);
	// (new TestEntity()).getEntityFromHashTable(null);

    }
}