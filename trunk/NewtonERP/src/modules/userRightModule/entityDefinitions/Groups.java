package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;

import newtonERP.module.AbstractEntity;
import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;

public class Groups extends AbstractEntity implements Ormizable
{
    private int PKgroupID;
    private String groupName;

    @Override
    public Hashtable<String, String> getOrmizableData() throws OrmException
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setOrmizableData(Hashtable<String, Object> parameters)
    {
	// TODO Auto-generated method stub

    }

    /**
     * @return the pKgroupID
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
     * @return the groupName
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

}
