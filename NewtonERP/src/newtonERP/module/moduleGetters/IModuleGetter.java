package newtonERP.module.moduleGetters;

import java.util.Hashtable;

import newtonERP.module.actions.ActionableEntity;

public interface IModuleGetter
{
    public ActionableEntity getEntityFromParameters(
	    Hashtable<String, String> parameters);
}
