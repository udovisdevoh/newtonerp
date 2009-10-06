/**
 * 
 */
package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;

import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author djo
 * 
 */
public class GroupRight implements Ormizable
{
    // TODO: est-ce que l'ORM acceptera les clé primaire multiple ou non, sinon
    // sa ne dérange rien, si oui mettre les 2 variable en PK.
    int groupID;
    int rightID;

    /*
     * (non-Javadoc)
     * 
     * @see newtonERP.orm.Ormizable#getOrmizableData()
     */
    public Hashtable<String, String> getOrmizableData() throws OrmException
    {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see newtonERP.orm.Ormizable#getPrimaryKeyValue()
     */
    public int getPrimaryKeyValue()
    {
	// TODO Auto-generated method stub
	return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see newtonERP.orm.Ormizable#getSearchCriteria()
     */
    public String getSearchCriteria()
    {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see newtonERP.orm.Ormizable#getTableName()
     */
    public String getTableName()
    {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see newtonERP.orm.Ormizable#setOrmizableData(java.util.Hashtable)
     */
    public void setOrmizableData(Hashtable<String, Object> parameters)
    {
	// TODO Auto-generated method stub

    }

}
