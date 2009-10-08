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
 * Fait Par Gabriel Therrien
 * 
 * @param args
 */
public class EditUser extends AbstractAction
{

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters)
    {
	Vector<String> whereParameter = new Vector<String>();
	whereParameter.add("Newton_Name=" + ((User) entity).getName());
	try
	{
	    Orm.update((Ormizable) entity, whereParameter);
	} catch (OrmException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }
}