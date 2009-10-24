package modules.userRightModule.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.entityDefinitions.Right;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author cloutierJo
 * 
 *         Action class that checks the right on an entity
 */
public class RightCheck extends AbstractAction
{
    @Override
    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters)
    {
	try
	{
	    Vector<String> search = new Vector<String>();
	    search.add("name='" + parameters.get("name") + "'");
	    User user = (User) Orm.select(new User(), search).get(0);
	    System.out.println(user);
	    for (Right right : user.getGroupsEntity().getRightList())
	    {
		if (right.getData("moduleName")
			.equals(parameters.get("module"))
			&& right.getData("actionName").equals(
				parameters.get("action"))
			&& (right.getData("entityName").equals(
				parameters.get("entity")) || !parameters
				.containsKey("entity")))
		    return right;
	    }

	} catch (OrmException e)
	{
	    e.printStackTrace();// FIXME: Es-tu sur que ça doit être catché?
				// -Guillaume
	}
	return null;
    }
}
