package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.EntityException;
import newtonERP.module.Module;
import newtonERP.orm.Orm;
import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.viewers.viewables.PromptViewable;

public class User extends AbstractEntity implements Ormizable, PromptViewable {
	private int PKuserID; // Todo, change name to camelCase
	private String name;
	private String password;
	private int groupID;
	private AbstractAction submitAction;
	private Module submitModule;

	@Override
	public Hashtable<String, String> getOrmizableData() throws OrmException {
		// TODO Auto-generated method stub
		return getHashTableFromEntity();
	}

	@Override
	public void setOrmizableData(Hashtable<String, Object> parameters) {
		setEntityFromHashTable(parameters);
	}

	/**
	 * @return the pKuserID
	 */
	public int getPKuserID() {
		return PKuserID;
	}

	/**
	 * @param pKuserID
	 *            the pKuserID to set
	 */
	public void setPKuserID(int pKuserID) {
		PKuserID = pKuserID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the groupID
	 */
	public int getGroupID() {
		return groupID;
	}

	/**
	 * @param groupID
	 *            the groupID to set
	 */
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	/**
	 * permet d'obtenir directement l'entity groups lier a cet user
	 * 
	 * @return le group lier
	 */
	public Groups getGroupsEntity() {
		Vector<String> search = new Vector<String>();
		search.add("PKgroupID=" + groupID);

		try {
			return (Groups) Orm.select(new Groups(), search).get(0);
		} catch (OrmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public String getButtonCaption() {
		// TODO Auto-generated method stub
		return "Save profile";
	}

	@Override
	public String getCurrentUserName() {
		// TODO Auto-generated method stub
		return "admin";
	}

	@Override
	public Hashtable<String, String> getInputList() throws OrmException {
		// TODO Auto-generated method stub
		return getOrmizableData();
	}

	@Override
	public String getPromptMessage() {
		return "Profil des utilisateurs";
	}

	@Override
	public AbstractAction getSubmitAction() throws EntityException {
		// TODO Auto-generated method stub

		if (submitAction == null)
			throw new EntityException(
					"Missing submit action, set it with setSubmitAction()");

		return submitAction;
	}

	public void setSubmitAction(AbstractAction submitAction) {
		this.submitAction = submitAction;
	}

	public void setSubmitModule(Module submitModule) {
		this.submitModule = submitModule;
	}

	@Override
	public Module getSubmitModule() throws EntityException {
		// TODO Auto-generated method stub

		if (submitModule == null)
			throw new EntityException(
					"Missing submit module, set it with setSubmitModule()");

		return submitModule;
	}
}