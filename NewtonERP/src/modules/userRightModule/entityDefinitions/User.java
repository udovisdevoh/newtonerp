package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.UserRightModule;
import modules.userRightModule.actions.GetUserList;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.ForwardEntity;
import newtonERP.module.Module;
import newtonERP.module.exception.EntityException;
import newtonERP.module.exception.InvalidOperatorException;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.serveur.Servlet;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * @author r3hallejo cloutierJo
 * 
 *         Entity defenition class representing a user
 */
public class User extends AbstractOrmEntity implements PromptViewable
{
    private String buttonCaption;
    private AbstractAction submitAction;
    private Module submitModule;
    private static Groups groupDefinition = new Groups();

    public Fields initFields()
    {
	Vector<Field> fieldsData = new Vector<Field>();// Renamé en fieldsData
	// pour enlever confusion
	fieldsData.add(new FieldInt("Numéro de user", getPrimaryKeyName()));
	fieldsData.add(new FieldString("Nom", "name"));
	fieldsData.add(new FieldString("Mot de passe", "password"));
	fieldsData.add(new FieldInt("Numéro de groupe", "groupsID"));
	return new Fields(fieldsData);
    }

    /**
     * permet d'obtenir directement l'entity groups lie a cet user
     * 
     * @return le group lie
     */
    public Groups getGroupsEntity()
    {
	Vector<String> search = new Vector<String>();
	search.add(groupDefinition.getPrimaryKeyName() + "="
		+ getFields().getField("groupsID"));

	try
	{
	    return (Groups) Orm.select(new Groups(), search).get(0);
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}
	return null;

    }

    @Override
    public String getButtonCaption()
    {
	return buttonCaption;
    }

    @Override
    public String getPromptMessage()
    {
	return "Profil de l'utilisateur";
    }

    @Override
    public AbstractAction getSubmitAction()
    {
	return submitAction;
    }

    /**
     * Sets the submit action
     * 
     * @param submitAction the submit action
     */
    public void setSubmitAction(AbstractAction submitAction)
    {
	this.submitAction = submitAction;
    }

    /**
     * Sets the submit module
     * 
     * @param submitModule the submit module
     */
    public void setSubmitModule(Module submitModule)
    {
	this.submitModule = submitModule;
    }

    @Override
    public Module getSubmitModule() throws EntityException
    {
	if (submitModule == null)
	    throw new EntityException(
		    "Vous devez setter le module préalablement avec setSubmitModule()");

	return submitModule;
    }

    public void setCurrentModule(Module module)
    {
	submitModule = module;
    }

    public AbstractEntity newUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	User user = new User();
	user.setData("name", "sans-nom");
	user.setData("groupsID", 1);
	user.setData("password", "abc123");
	user.setSubmitModule(new UserRightModule());
	// user.setSubmitAction(new BaseAction("Edit", this));

	((AbstractOrmEntity) user).newE();

	return new ForwardEntity(Servlet.makeLink(new UserRightModule(),
		new GetUserList()));
    }

    public AbstractEntity deleteUI(Hashtable<String, String> parameters)
	    throws InvalidOperatorException
    {
	((AbstractOrmEntity) (this)).delete(getPrimaryKeyName() + "='"
		+ getDataString(getPrimaryKeyName()) + "'");

	return new ForwardEntity(Servlet.makeLink(new UserRightModule(),
		new GetUserList()));
    }

    public AbstractEntity editUI(Hashtable<String, String> parameters)
	    throws InvalidOperatorException
    {
	if (parameters.containsKey("submit"))
	    edit(getPrimaryKeyName() + "='"
		    + getDataString(getPrimaryKeyName()) + "'");

	for (Field field : getFields())
	    field.setOperator("=");

	// On utilise l'entité courrante comme entité de recherche
	User retUser = (User) get(this).get(0); // on discarte les autre
	// entity
	// s'il y a lieu

	retUser.setSubmitAction(new BaseAction("Edit", this));
	retUser.setSubmitModule(new UserRightModule());

	return retUser;
    }

    public AbstractEntity getUI(Hashtable<String, String> parameters)
	    throws InvalidOperatorException
    {
	/*
	 * Vector<AbstractOrmEntity> entities = new Vector<AbstractOrmEntity>();
	 * 
	 * User user = new User();
	 * user.getFields().getField("name").setOperator("=");
	 * user.getFields().getField("name").setData(getDataString("name"));
	 * 
	 * entities.add(user);
	 * 
	 * User retUser = (User) get(entities).get(0);
	 */

	for (Field field : getFields())
	    field.setOperator("=");

	// On utilise l'entité courrante comme entité de recherche
	User retUser = (User) get(this).get(0); // on discarte les autre
	// entity
	// s'il y a lieu

	// User retUser = (User) get("name='" + getDataString("name") + "'")
	// .get(0); // on discarte les autre entity s'il y a lieu
	retUser.setSubmitAction(new BaseAction("Get", this));

	retUser.setSubmitModule(new UserRightModule());

	return retUser;
    }

}