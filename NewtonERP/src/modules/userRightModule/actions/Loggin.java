package modules.userRightModule.actions;

import java.util.Hashtable;

import modules.home.Home;
import modules.home.actions.GetHome;
import modules.userRightModule.UserRightModule;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.Authentication;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.ForwardEntity;
import newtonERP.module.field.Field;
import newtonERP.serveur.Servlet;

/**
 * Action class that creates all the rights for every module
 * 
 * @author cloutierJo
 */
public class Loggin extends AbstractAction
{
    /**
     * constructeur
     */
    public Loggin()
    {
	super(new User()); // ne travaille pas avec une entity
    }

    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {

	if (parameters.containsKey("submit"))
	{
	    for (Field field : entity.getFields().getFields())
	    {
		field.setOperator("="); // TODO: on peu tu mettre l'operateur
		// par defaut a = ???
	    }
	    if (((AbstractOrmEntity) entity).get((AbstractOrmEntity) entity)
		    .size() > 0)
	    {
		Authentication.setCurrentUserName(entity.getDataString("name"));
		return new ForwardEntity(Servlet.makeLink(new Home(),
			new GetHome()));
	    }
	}

	User retEntity = new User();
	retEntity.setCurrentAction(this);
	retEntity.setCurrentModule(new UserRightModule());

	return retEntity;
    }
}
