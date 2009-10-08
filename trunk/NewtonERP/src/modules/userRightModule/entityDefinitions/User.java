package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;

import newtonERP.module.AbstractEntity;
import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;

public class User extends AbstractEntity implements Ormizable
{
    private int PKuserID;
    private String name;
    private String password;
    private int GroupID;

    @Override
    public Hashtable<String, String> getOrmizableData() throws OrmException
    {
	// TODO Auto-generated method stub
	return getHashTableFromEntity();
    }

    @Override
    public void setOrmizableData(Hashtable<String, Object> parameters)
    {
	setEntityFromHashTable(parameters);
    }

    /**
     * @return the pKuserID
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
     * @return the name
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
     * @return the password
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
     * @return the groupID
     */
    public int getGroupID()
    {
	return GroupID;
    }

    /**
     * @param groupID the groupID to set
     */
    public void setGroupID(int groupID)
    {
	GroupID = groupID;
    }

}
