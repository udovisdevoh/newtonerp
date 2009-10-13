package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author djo
 * 
 * 	Entity defenition representing a group right for the users
 */
public class GroupRight extends AbstractEntity implements Ormizable
{
    private int groupID;
    private int rightID;

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
     * @return rightID the rightID
     */
    public int getRightID()
    {
	return rightID;
    }

    /**
     * @param rightID the rightID to set
     */
    public void setRightID(int rightID)
    {
	this.rightID = rightID;
    }

    /**
     * permet d'obtenir directement l'entity groups lie a cet user
     * 
     * @return le group lier
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

    /**
     * permet d'obtenir directement l'entity Right lier a cet user
     * 
     * @return le Right lier
     */
    public Right getRightEntity()
    {
	Vector<String> search = new Vector<String>();
	search.add("PKrightID=" + rightID);

	try
	{
	    return (Right) Orm.select(new Right(), search).get(0);
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}
	return null;
    }
}
