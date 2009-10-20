package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.Fields;
import newtonERP.orm.Orm;
import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author djo
 * 
 *         Entity defenition representing a group right for the users
 */
public class GroupRight extends AbstractEntity implements Ormizable
{
    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("numéro de groupe", "groupID"));
	fields.add(new FieldInt("numéro de droit", "rightID"));
	return new Fields(fields);
    }

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
     * permet d'obtenir directement l'entity groups lie a cet user
     * 
     * @return le group lier
     */
    public Groups getGroupsEntity()
    {
	Vector<String> search = new Vector<String>();
	search.add("PKgroupID=" + getFields().getField("groupID"));

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
	search.add("PKrightID=" + getFields().getField("rightID"));

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
