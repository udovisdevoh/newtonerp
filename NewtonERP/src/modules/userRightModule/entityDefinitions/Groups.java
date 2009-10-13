package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author r3hallejo
 * 
 * 	Entity defenition class representing a group for the users
 */
public class Groups extends AbstractEntity implements Ormizable
{
    private int PKgroupID;
    private String groupName;

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
     * @return PKgroupID the PKgroupID
     */
    public int getPKgroupID()
    {
	return PKgroupID;
    }

    /**
     * @param pKgroupID the pKgroupID to set
     */
    public void setPKgroupID(int pKgroupID)
    {
	PKgroupID = pKgroupID;
    }

    /**
     * @return groupName the groupName
     */
    public String getGroupName()
    {
	return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName)
    {
	this.groupName = groupName;
    }

    /**
     * Searches the rights for this group by the groupID and then returns this list
     * 
     * @return rightResult the right list
     */
    public Vector<Right> getRightList()
    {
	Vector<Right> rightResult = new Vector<Right>();
	Vector<String> search = new Vector<String>();
	search.add("groupID=" + PKgroupID);
	try
	{
	    Vector<Ormizable> groupRights = Orm
		    .select(new GroupRight(), search);
	    for (Ormizable gr : groupRights)
	    {
		rightResult.add(((GroupRight) gr).getRightEntity());
	    }
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}

	return rightResult;
    }
}
