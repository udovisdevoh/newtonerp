package modules.userRightModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Entity defenition class representing a group for the users
 * @author CloutierJo, r3hallejo
 */
public class Groups extends AbstractOrmEntity
{
    /**
     * @throws Exception Cré un Groups
     */
    public Groups() throws Exception
    {
	super();
	AccessorManager.addAccessor(this, new Right());
	setVisibleName("Groupe");
    }

    public Fields initFields() throws Exception
    {
	FieldString groupName = new FieldString("Nom", "groupName");
	groupName.setNaturalKey(true);

	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(groupName);
	return new Fields(fieldsInit);
    }

    /**
     * Searches the rights for this group by the groupsID and then returns this
     * list
     * 
     * @return rightResult the right list
     * @throws Exception remonte
     */
    public Vector<Right> getRightList() throws Exception
    {
	Vector<Right> rightList = new Vector<Right>();
	for (AbstractOrmEntity entity : getPluralAccessor("Right"))
	    rightList.add((Right) entity);
	return rightList;
    }
}
