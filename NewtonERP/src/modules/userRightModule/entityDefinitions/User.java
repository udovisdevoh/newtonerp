package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.UserRightModule;
import modules.userRightModule.actions.GetUserList;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.EntityException;
import newtonERP.module.Module;
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
    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("numéro de user", "PKuserID"));
	fields.add(new FieldString("nom", "name"));
	fields.add(new FieldString("mot de pass", "password"));
	fields.add(new FieldInt("numéro de groupe", "groupID"));
	return new Fields(fields);
    }

    @Override
    public Hashtable<String, String> getOrmizableData() throws OrmException
    {
	return getHashTableFromEntity();
    }

    @Override
    public void setOrmizableData(Hashtable<String, Object> parameters)
    {
	setEntityFromHashTable(parameters);
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
	return "Save profile";
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
    public AbstractAction getSubmitAction() throws EntityException
    {
	/*
	 * 
	 * if (submitAction == null) throw new EntityException(
	 * "Missing submit action, set it with setSubmitAction()");
	 * 
	 * return submitAction;
	 */
	// TODO: remove dummy code
	return new GetUserList(); // TODO : a remplacé des que possible c'Est
				  // pour évité une null pointer...
    }

    /**
     * Sets the submit action
     * 
     * @param submitAction
     */
    public void setSubmitAction(AbstractAction submitAction)
    {
	// TODO : To be completed or changed
    }

    /**
     * Sets the submit module
     * 
     * @param submitModule
     */
    public void setSubmitModule(Module submitModule)
    {
	// TODO : To be completed or changed
    }

    @Override
    public Module getSubmitModule() throws EntityException
    {
	// TODO Auto-generated method stub
	/*
	 * if (submitModule == null) throw new EntityException(
	 * "Missing submit module, set it with setSubmitModule()");
	 * 
	 * return submitModule;
	 */

	return new UserRightModule();
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
    {
	// TODO Auto-generated method stub
	return null;
    }

    public AbstractEntity getUI(Hashtable<String, String> parameters)
    {
	User retUser = (User) get("name='" + getDataString("name") + "'");
	// retUser.setSubmitAction(new GetUser()); // todo: find a way to call
	// baseAction

	retUser.setSubmitModule(new UserRightModule());

	return retUser;
    }

}