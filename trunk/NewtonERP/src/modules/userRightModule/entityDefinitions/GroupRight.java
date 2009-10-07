/**
 * 
 */
package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;

import newtonERP.module.AbstractEntity;
import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author djo
 * 
 */
public class GroupRight extends AbstractEntity implements Ormizable
{
    // TODO: est-ce que l'ORM acceptera les clé primaire multiple ou non, sinon
    // sa ne dérange rien, si oui mettre les 2 variable en PK.
    private int groupID;
    private int rightID;

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
     * @return the groupID
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
     * @return the rightID
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

}
