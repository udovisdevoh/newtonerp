package modules.userRightModule.entityDefinitions;

import java.text.ParseException;
import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.FieldNotFoundException;
import newtonERP.module.exception.InvalidOperatorException;
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

    /**
     * contructeur vide
     */
    public GroupRight()
    {
	// rien a faire
    }

    /**
     * @param groups ID du groupe
     * @param right ID du droit
     * @throws FieldNotFoundException remonte
     * @throws ParseException an exception that can occur during parsing dates
     */
    public GroupRight(Groups groups, Right right)
	    throws FieldNotFoundException, ParseException
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
     * @throws OrmException remonte
     */
    public Groups getGroupsEntity() throws OrmException
    {
	Groups groupsDefinition = new Groups();

	Vector<String> search = new Vector<String>();
	search.add(groupsDefinition.getPrimaryKeyName()
		+ getFields().getField(groupsDefinition.getPrimaryKeyName()));

	return (Groups) Orm.select(new Groups(), search).get(0);
    }

    /**
     * permet d'obtenir directement l'entity Right lier a cet user
     * 
     * @return le Right lier
     * @throws Exception remonte
     */
    public Right getRightEntity() throws Exception
    {
	String rightIDValue = getFields().getField("rightID").getDataString();

	Right rightSearchEntity = new Right();
	rightSearchEntity.getFields().getField(
		rightDefinition.getPrimaryKeyName()).setOperator("=");

	rightSearchEntity.getFields().setData(
		rightDefinition.getPrimaryKeyName(), rightIDValue);

	return (Right) Orm.select(rightSearchEntity).get(0);
    }

    @Override
    public AbstractEntity getAfterDeleteReturnEntity()
    {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * @Override public AbstractEntity getUI(Hashtable<String, String>
     * parameters) throws InvalidOperatorException { // TODO Auto-generated
     * method stub return null; }
     */

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
