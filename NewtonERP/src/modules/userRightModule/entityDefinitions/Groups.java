package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;
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
	fields.add(new FieldInt("Numéro du groupe", getPrimaryKeyName()));
	fields.add(new FieldString("Nom du groupe", "groupName"));
	return new Fields(fields);
    }

    /**
     * Searches the rights for this group by the groupsID and then returns this
     * list
     * 
     * @return rightResult the right list
     */
    public Vector<Right> getRightList() throws Exception
    {
	Vector<Right> rightResult = new Vector<Right>();
	GroupRight groupRightSearch = new GroupRight();
	String groupsId = getFields().getField(getPrimaryKeyName())
		.getDataString();

	groupRightSearch.getFields().setData("groupsID", groupsId);
	groupRightSearch.getFields().getField("groupsID").setOperator("=");

	try
	{
	    Vector<AbstractOrmEntity> groupRights = Orm
		    .select(groupRightSearch);
	    for (AbstractOrmEntity entity : groupRights)
	    {
		GroupRight groupRight = (GroupRight) (entity);

		rightResult.add(groupRight.getRightEntity());
	    }
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}

	return rightResult;
    }

    @Override
    public AbstractEntity getAfterDeleteReturnEntity()
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AbstractEntity getUI(Hashtable<String, String> parameters)
	    throws InvalidOperatorException
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AbstractEntity newUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AbstractEntity editUI(Hashtable<String, String> parameters)
	    throws InvalidOperatorException, FieldNotCompatibleException
    {
	// TODO Auto-generated method stub
	return null;
    }
}
