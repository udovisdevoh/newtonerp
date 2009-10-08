package modules.userRightModule.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.entityDefinitions.Right;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author Gabriel Therrien
 * 
 */
public class DeleteRight extends AbstractAction
{
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters)
    {
	try
	{
	    Vector<String> whereParameter = new Vector<String>();
	    whereParameter.add("Newton_ActionName="
		    + ((Right) entity).getActionName());
	    Orm.delete((Ormizable) entity, whereParameter);
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}
	return entity;
    }

}