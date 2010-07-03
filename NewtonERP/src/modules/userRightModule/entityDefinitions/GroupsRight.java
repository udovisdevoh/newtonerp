package modules.userRightModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;

/**
 * Entity defenition representing a group right for the users
 * @author CloutierJo
 */
public class GroupsRight extends AbstractOrmEntity
{
	private static Right rightDefinition;

	/**
	 */
	public GroupsRight()
	{
		rightDefinition = new Right();
		setVisibleName("Droit de groupe");
	}

	/**
	 * @param groups ID du groupe
	 * @param right ID du droit
	 */
	public GroupsRight(Groups groups, Right right)
	{
		int groupsIdValue = groups.getPrimaryKeyValue();
		int rightIdValue = right.getPrimaryKeyValue();

		getFields().setData("groupsID", groupsIdValue);
		getFields().setData("rightID", rightIdValue);
	}

	public Fields initFields()
	{
		Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
		fieldsInit.add(new FieldInt("Numéro de groupe", "groupsID"));
		fieldsInit.add(new FieldInt("Numéro de droit", "rightID"));
		return new Fields(fieldsInit);
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

		return (Groups) Orm.select(new Groups(), search).get(0);
	}

	/**
	 * permet d'obtenir directement l'entity Right lier a cet user
	 * 
	 * @return le Right lier @ remonte
	 */
	public Right getRightEntity()
	{
		String rightIDValue = getFields().getField("rightID").getDataString();

		Right rightSearchEntity = new Right();
		rightSearchEntity.getFields().setData(
				rightDefinition.getPrimaryKeyName(), rightIDValue);

		return (Right) Orm.select(rightSearchEntity).get(0);
	}
}
