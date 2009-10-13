package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;

import newtonERP.module.AbstractEntity;
import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author r3hallejo
 * 
 * 	Entity defenition class representing a right
 */
public class Right extends AbstractEntity implements Ormizable
{
    private int PKrightID;
    private String moduleName;
    private String actionName;

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
     * @return PKrightID the pKrightID
     */
    public int getPKrightID()
    {
	return PKrightID;
    }

    /**
     * @param pKrightID the pKrightID to set
     */
    public void setPKrightID(int pKrightID)
    {
	PKrightID = pKrightID;
    }

    /**
     * @return moduleName the moduleName
     */
    public String getModuleName()
    {
	return moduleName;
    }

    /**
     * @param moduleName the moduleName to set
     */
    public void setModuleName(String moduleName)
    {
	this.moduleName = moduleName;
    }

    /**
     * @return actionName the actionName
     */
    public String getActionName()
    {
	return actionName;
    }

    /**
     * @param actionName the actionName to set
     */
    public void setActionName(String actionName)
    {
	this.actionName = actionName;
    }

}
