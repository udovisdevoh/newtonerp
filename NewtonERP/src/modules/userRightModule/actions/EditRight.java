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
 * Fait Par Gabriel Therrien
 * 
 * @param args
 */
public class EditRight extends AbstractAction
{

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters)
    {
	Vector<String> g = new Vector<String>();
	g.add("" + ((Right) entity).getPKrightID());
	try
	{
	    Orm.update((Ormizable) entity, g);
	} catch (OrmException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }
}