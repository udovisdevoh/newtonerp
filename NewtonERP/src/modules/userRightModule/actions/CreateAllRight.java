package modules.userRightModule.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.entityDefinitions.Right;
import newtonERP.common.ListModule;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.Module;
import newtonERP.orm.Orm;

/**
 * Action class that creates all the rights for every module
 * @author cloutierJo
 */
public class CreateAllRight extends AbstractAction
{
    /**
     * constructeur
     */
    public CreateAllRight()
    {
	super(null); // ne travaille pas avec une entity
    }

    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Module module = null;
	Vector<String> allModule;
	allModule = new Vector<String>(ListModule.getAllModules().keySet());
	allModule.remove("UserRightModule");
	allModule.remove("TaskModule");
	// on s'assure d'avoir créé le userRightModule en premier et le task
	// module en dernier
	allModule.add(0, "UserRightModule");
	allModule.add("TaskModule");
	for (String moduleName : allModule)
	{
	    module = ListModule.getModule(moduleName);
	    // on verifie si des droits du module on deja ete cree en DB
	    // sinon on appel le initdb du module
	    Vector<String> search = new Vector<String>();
	    search.add("ModuleName='" + moduleName + "'");
	    int numRight = 0;

	    numRight = Orm.select(new Right(), search).size();

	    if (numRight == 0)
	    {
		// pour le action réguliere
		for (String actionName : module.getActionList().keySet())
		{
		    // cree le right
		    Right right = new Right();
		    right.setData("actionName", actionName);
		    right.setData("moduleName", moduleName);
		    Orm.insert(right);
		}

		// pour les BaseAction
		for (String entityName : module.getEntityDefinitionList()
			.keySet())
		{
		    // cree le right
		    String actionNames[] = { "Get", "New", "Edit", "Delete",
			    "GetList" };
		    for (String actionName : actionNames)
		    {
			// cree le right
			Right right = new Right();
			right.setData("actionName", actionName);
			right.setData("entityName", entityName);
			right.setData("moduleName", moduleName);
			Orm.insert(right);
		    }
		}
		module.initDB();
	    }
	}

	return null;
    }
}
