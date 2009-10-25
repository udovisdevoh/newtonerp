package modules.userRightModule.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.UserRightModule;
import modules.userRightModule.entityDefinitions.Right;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.EntityList;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;

public class GetRightList extends AbstractAction
{

    @Override
    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Vector<AbstractOrmEntity> rightVectorFromOrm;

	try
	{
	    rightVectorFromOrm = Orm.select(new Right(), null);

	} catch (OrmException e)
	{
	    rightVectorFromOrm = new Vector<AbstractOrmEntity>();
	    e.printStackTrace();
	}

	EntityList rightList = new EntityList(new Right());

	for (AbstractOrmEntity right : rightVectorFromOrm)
	    rightList.addEntity((right));

	// TODO: Problème: c'est très bizare de devoir recréer un objet de
	// module pour spécifier quel module sera responsable des actions -
	// Guillaume
	rightList.setCurrentModule(new UserRightModule());

	return rightList;
    }

}
