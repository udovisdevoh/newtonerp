package modules.userRightModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Entity defenition class representing a group for the users
 * @author CloutierJo, r3hallejo
 */
public class Groups extends AbstractOrmEntity implements PromptViewable
{
    /**
     * @throws Exception Cré un Groups
     */
    public Groups() throws Exception
    {
	super();
	addNaturalKey("groupName");
	AccessorManager.addAccessor(this, new Right());
	setVisibleName("Groupe");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom", "groupName"));
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
	/*
	 * Vector<Right> rightResult = new Vector<Right>(); GroupsRight
	 * GroupsRightSearch = new GroupsRight(); String groupsId =
	 * getFields().getField(getPrimaryKeyName()) .getDataString();
	 * 
	 * GroupsRightSearch.getFields().setData("groupsID", groupsId);
	 * 
	 * Vector<AbstractOrmEntity> GroupsRights =
	 * Orm.select(GroupsRightSearch); for (AbstractOrmEntity entity :
	 * GroupsRights) { GroupsRight GroupsRight = (GroupsRight) entity;
	 * 
	 * rightResult.add(GroupsRight.getRightEntity()); }
	 * 
	 * return rightResult;
	 */

    }
}
