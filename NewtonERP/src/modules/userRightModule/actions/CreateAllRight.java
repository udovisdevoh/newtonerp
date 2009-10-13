package modules.userRightModule.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.entityDefinitions.Right;
import newtonERP.ListModule;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.Module;
import newtonERP.module.ModuleException;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author cloutierJo
 * 
 * 	Action class that creates all the rights for every module
 */
public class CreateAllRight extends AbstractAction
{
    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters)
    {
	Module module = null;
	for (String moduleName : ListModule.getAllModules().keySet())
	{
	    try
	    {
		module = ListModule.getModule(moduleName);
		// on verifie si des droits du module on deja ete cree en DB
		// sinon
		// on appel le initdb du module
		Vector<String> search = new Vector<String>();
		search.add("ModuleName='" + moduleName + "'");
		int numRight = 0;
		try
		{
		    numRight = Orm.select(new Right(), search).size();
		} catch (OrmException e)
		{
		    e.printStackTrace();
		}
		if (numRight == 0)
		{
		    module.initDB();
		}

		for (String actionName : module.getActionList().keySet())
		{
		    try
		    {
			// cree le right
			Right right = new Right();
			right.setActionName(actionName);
			right.setModuleName(getClass().getSimpleName());
			Orm.insert(right);
		    } catch (OrmException e)
		    {
			e.printStackTrace();
		    }

		}
	    } catch (ModuleException e)
	    {
		e.printStackTrace();
	    }
	}
	return null;
    }
}
