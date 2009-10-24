package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.UserRightModule;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;
import newtonERP.module.exception.EntityException;
import newtonERP.module.exception.InvalidOperatorException;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;
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

    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("Numéro de user", "PKuserID"));
	fields.add(new FieldString("Nom", "name"));
	fields.add(new FieldString("Mot de passe", "password"));
	fields.add(new FieldInt("Numéro de groupe", "groupID"));
	return new Fields(fields);
    }

    /**
     * permet d'obtenir directement l'entity groups lie a cet user
     * 
     * @return le group lie
     */
    public Groups getGroupsEntity()
    {
	Vector<String> search = new Vector<String>();
	search.add("PKgroupID=" + getFields().getField("groupID"));

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
    public String getCurrentUserName()
    {
	return "admin";
    }

    @Override
    public Hashtable<String, String> getInputList() throws OrmException
    {
	return getOrmizableData();
    }

    @Override
    public String getPromptMessage()
    {
	return "Profil des utilisateurs";
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
    {
	// TODO Auto-generated method stub
	return null;
    }

    public AbstractEntity deleteUI(Hashtable<String, String> parameters)
    {
	// TODO Auto-generated method stub
	return null;
    }

    public AbstractEntity editUI(Hashtable<String, String> parameters)
	    throws InvalidOperatorException
    {
	if (parameters.containsKey("submit"))
	{
	    edit("PKuserID='" + getDataString("PKuserID") + "'");
	}

	// User retUser = (User) get("name='" + getDataString("name") + "'")
	// .get(0); // on discarte les autre entity s'il y a lieu

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
	Vector<AbstractOrmEntity> entities = new Vector<AbstractOrmEntity>();

	User user = new User();
	user.getFields().getField("name").setOperator("=");
	user.getFields().getField("name").setData(getDataString("name"));

	entities.add(user);

	User retUser = (User) get(entities).get(0);

	// User retUser = (User) get("name='" + getDataString("name") + "'")
	// .get(0); // on discarte les autre entity s'il y a lieu
	retUser.setSubmitAction(new BaseAction("Get", this));

	retUser.setSubmitModule(new UserRightModule());

	return retUser;
    }

}