package modules.userRightModule.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.UserRightModule;
import modules.userRightModule.entityDefinitions.Groups;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.EntityList;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;

public class GetGroupsList extends AbstractAction
{

    @Override
    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Vector<AbstractOrmEntity> groupsVectorFromOrm;

	try
	{
	    groupsVectorFromOrm = Orm.select(new Groups(), null);

	} catch (OrmException e)
	{
	    groupsVectorFromOrm = new Vector<AbstractOrmEntity>();
	    e.printStackTrace();
	}

	EntityList groupsList = new EntityList(new Groups());

	for (AbstractOrmEntity right : groupsVectorFromOrm)
	    groupsList.addEntity((right));

	// TODO: Problème: c'est très bizare de devoir recréer un objet de
	// module pour spécifier quel module sera responsable des actions -
	// Guillaume
	groupsList.setCurrentModule(new UserRightModule());

	return groupsList;
    }

}
