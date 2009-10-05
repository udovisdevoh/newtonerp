package modules.testModule.moduleGetters;

import java.util.Hashtable;

import modules.testModule.entityDefinitions.TestActionableEntity;
import newtonERP.module.actions.ActionableEntity;
import newtonERP.module.moduleGetters.IModuleGetter;

public class TestModuleGetter implements IModuleGetter
{

    @Override
    public ActionableEntity getEntityFromParameters(
	    Hashtable<String, String> parameters)
    {
	System.out.println("TestModuleGetter.getEntityFromParameters()");
	return new TestActionableEntity();
    }

}
