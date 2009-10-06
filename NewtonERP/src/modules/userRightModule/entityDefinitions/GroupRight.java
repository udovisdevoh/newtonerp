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

}
