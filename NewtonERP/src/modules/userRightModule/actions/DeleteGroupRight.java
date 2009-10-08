package modules.userRightModule.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.entityDefinitions.GroupRight;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author Gabriel Therrien
 * 
 */
public class DeleteGroupRight extends AbstractAction
{
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters)
    {
	try
	{
	    Vector<String> whereParameter = new Vector<String>();
	    whereParameter.add("Newton_RightID="
		    + ((GroupRight) entity).getRightID());
	    Orm.delete((Ormizable) entity, whereParameter);
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}
	return entity;
    }

}
