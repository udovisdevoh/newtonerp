package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author r3hallejo
 * 
 *         Entity defenition class representing a group for the users
 */
public class Groups extends AbstractOrmEntity
{
    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("numéro du groupe", "PKgroupID"));
	fields.add(new FieldString("nom du groupe", "groupName"));
	return new Fields(fields);
    }

    /**
     * Searches the rights for this group by the groupID and then returns this
     * list
     * 
     * @return rightResult the right list
     */
    public Vector<Right> getRightList()
    {
	Vector<Right> rightResult = new Vector<Right>();
	Vector<String> search = new Vector<String>();
	search.add("groupID=" + getFields().getField("PKgroupID"));
	try
	{
	    Vector<AbstractOrmEntity> groupRights = Orm.select(
		    new GroupRight(), search);
	    for (AbstractOrmEntity gr : groupRights)
	    {
		rightResult.add(((GroupRight) gr).getRightEntity());
	    }
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}

	return rightResult;
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
