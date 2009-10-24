package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.exception.FieldNotFoundException;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.Fields;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author djo
 * 
 *         Entity defenition representing a group right for the users
 */
public class GroupRight extends AbstractOrmEntity
{
    private static Right rightDefinition = new Right();

    public GroupRight()
    {
    }

    public GroupRight(Groups groups, Right right) throws FieldNotFoundException
    {
	String groupsIdValue = groups.getPrimaryKeyValue();
	String rightIdValue = right.getPrimaryKeyValue();

	getFields().setData("groupsID", groupsIdValue);
	getFields().setData("rightID", rightIdValue);
    }

    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("Numéro de groupe", "groupsID"));
	fields.add(new FieldInt("Numéro de droit", "rightID"));
	return new Fields(fields);
    }

    /**
     * permet d'obtenir directement l'entity groups lie a cet user
     * 
     * @return le group lier
     */
    public Groups getGroupsEntity()
    {
	Groups groupsDefinition = new Groups();

	Vector<String> search = new Vector<String>();
	search.add(groupsDefinition.getPrimaryKeyName()
		+ getFields().getField(groupsDefinition.getPrimaryKeyName()));

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
     * @throws FieldNotFoundException
     */
    public Right getRightEntity() throws Exception
    {
	String rightIDValue = getFields().getField("rightID").getDataString();

	Right rightSearchEntity = new Right();
	rightSearchEntity.getFields().getField(
		rightDefinition.getPrimaryKeyName()).setOperator("=");

	rightSearchEntity.getFields().setData(
		rightDefinition.getPrimaryKeyName(), rightIDValue);

	try
	{
	    return (Right) Orm.select(rightSearchEntity).get(0);
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}
	return null;
    }

    @Override
    public AbstractEntity deleteUI(Hashtable<String, String> parameters)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AbstractEntity editUI(Hashtable<String, String> parameters)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AbstractEntity getUI(Hashtable<String, String> parameters)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AbstractEntity newUI(Hashtable<String, String> parameters)
    {
	// TODO Auto-generated method stub
	return null;
    }
}
