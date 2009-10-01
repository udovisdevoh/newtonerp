package modules.testModule;

import java.util.Hashtable;

import newtonERP.module.actions.ActionableEntity;
import newtonERP.module.moduleGetters.IModuleGetter;

public class TestModuleGetter implements IModuleGetter
{

    @Override
    public ActionableEntity getEntityFromParameters(
	    Hashtable<String, String> parameters)
    {
	return new TestActionableEntity();
    }

}
