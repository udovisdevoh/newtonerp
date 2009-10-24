package modules.userRightModule.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.UserRightModule;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.EntityList;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author r3hallejo, r3lacasgu
 * 
 *         Action class that returns the user list
 */
public class GetUserList extends AbstractAction
{
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters)
    {
	Vector<AbstractOrmEntity> userVectorFromOrm;

	try
	{
	    userVectorFromOrm = Orm.select(new User(), null);

	} catch (OrmException e)
	{
	    userVectorFromOrm = new Vector<AbstractOrmEntity>();
	    e.printStackTrace();
	}

	EntityList userList = new EntityList();

	for (AbstractOrmEntity user : userVectorFromOrm)
	    userList.addEntity((User) (user));

	// TODO: Problème: c'est très bizare de devoir recréer un objet de
	// module pour spécifier quel module sera responsable des actions -
	// Guillaume
	userList.setCurrentModule(new UserRightModule());

	return userList;
    }
}
