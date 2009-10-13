package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.UserRightModule;
import modules.userRightModule.actions.GetUser;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.EntityException;
import newtonERP.module.Module;
import newtonERP.module.ModuleException;
import newtonERP.orm.Orm;
import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * @author r3hallejo
 * 
 * 	Entity defenition class representing a user
 */
public class User extends AbstractEntity implements Ormizable, PromptViewable
{
    private int PKuserID;
    private String name;
    private String password;
    private int groupID;

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
     * @return PKuserID the pKuserID
     */
    public int getPKuserID()
    {
	return PKuserID;
    }

    /**
     * @param pKuserID the pKuserID to set
     */
    public void setPKuserID(int pKuserID)
    {
	PKuserID = pKuserID;
    }

    /**
     * @return name the name
     */
    public String getName()
    {
	return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
	this.name = name;
    }

    /**
     * @return password the password
     */
    public String getPassword()
    {
	return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
	this.password = password;
    }

    /**
     * @return groupID the groupID
     */
    public int getGroupID()
    {
	return groupID;
    }

    /**
     * @param groupID the groupID to set
     */
    public void setGroupID(int groupID)
    {
	this.groupID = groupID;
    }

    /**
     * permet d'obtenir directement l'entity groups lie a cet user
     * 
     * @return le group lie
     */
    public Groups getGroupsEntity()
    {
	Vector<String> search = new Vector<String>();
	search.add("PKgroupID=" + groupID);

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
	return new GetUser();
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

	try
	{
	    return new UserRightModule();
	} catch (ModuleException e)
	{
	    // FIXME : cest peut etre juste moi mais ca sert pas a rien de lancer cette entity exception et de la printer en meme temps? 
	    // Au final on va la printer 2 fois cette exception lorsque la entity exception sera catchee...
	    // Sinon lors de la creation du user right module on throw meme pas dexception... Alors pourquoi les gerer? Bref on sen reparle....
	    e.printStackTrace();
	    throw new EntityException(e.getMessage());
	}
    }
}