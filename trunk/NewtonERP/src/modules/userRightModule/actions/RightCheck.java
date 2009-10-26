package modules.userRightModule.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.entityDefinitions.Right;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;

/**
 * @author cloutierJo
 * 
 *         Action class that checks the right on an entity
 */
public class RightCheck extends AbstractAction
{
    @Override
    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Vector<String> search = new Vector<String>();
	search.add("name='" + parameters.get("name") + "'");
	User user = (User) Orm.select(new User(), search).get(0);

	for (Right right : user.getGroupsEntity().getRightList())
	{
	    String moduleName = right.getDataString("moduleName");
	    String actionName = right.getDataString("actionName");
	    String entityName = right.getDataString("entityName");

	    if (moduleName.equals(parameters.get("module"))
		    && actionName.equals(parameters.get("action"))
		    && (entityName == null || entityName.equals(parameters
			    .get("entity"))))
		return right;
	}
	return null;

    }
}
