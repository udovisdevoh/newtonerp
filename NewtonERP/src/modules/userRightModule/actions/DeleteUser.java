package modules.userRightModule.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;

/**
 * 
 * @author Gabriel Therrien? Reviewer and committer : r3hallejo
 *
 *	Action class used to delete a user
 */
public class DeleteUser extends AbstractAction
{
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters)
    {
	try
	{
	    Vector<String> whereParameter = new Vector<String>();
	    whereParameter.add("Name=" + ((User) entity).getName());
	    Orm.delete((Ormizable) entity, whereParameter);
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}
	return entity;
    }

}
