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
 */
public class RightCheck extends AbstractAction
{
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters)
    {
	try
	{
	    Vector<String> search = new Vector<String>();
	    search.add("name=" + parameters.get("name"));
	    User user = (User) Orm.select(new User(), search).get(0);
	    for (Right right : user.getGroupsEntity().getRightList())
	    {
		if (right.getModuleName().equals(parameters.get("module"))
			&& right.getActionName().equals(
				parameters.get("action")))
		    return right;
	    }

	} catch (OrmException e)
	{
	    e.printStackTrace();
	}
	return null;
    }
}
