package modules.userRightModule.actions;

import java.util.Hashtable;

import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author Gabriel Therrien
 * 
 * 	Action class to create a new right
 */
public class NewRight extends AbstractAction
{
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters)
    {
	try
	{
	    Orm.insert((Ormizable) entity);
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}
	return entity;
    }

}