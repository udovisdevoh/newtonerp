package modules.userRightModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.FlagPoolManager;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Entity defenition class representing a group for the users
 * @author r3hallejo
 */
public class Groups extends AbstractOrmEntity implements PromptViewable
{
    private static GroupsRight GroupsRightDefinition = new GroupsRight();
    private static Right rightDefinition = new Right();

    public Fields initFields()
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro du groupe", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom du groupe", "groupName"));

	// Ajout du flag pool. Sert à choisir les droits via GroupsRight et
	// Right
	String[] foreignUiControlKeys = { "moduleName", "entityName",
		"actionName" };

	FlagPoolManager.addFlagPool(this, "Droits", GroupsRightDefinition,
		"groupsID", "rightID", rightDefinition, rightDefinition
			.getPrimaryKeyName(), foreignUiControlKeys);

	/*
	 * Sera simplifié tel que: addFlagPool(this, "Droits", rightDefinition,
	 * rightDefinition.getPrimaryKeyName(), foreignUiControlKeys);
	 */

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
	Vector<Right> rightResult = new Vector<Right>();
	GroupsRight GroupsRightSearch = new GroupsRight();
	String groupsId = getFields().getField(getPrimaryKeyName())
		.getDataString();

	GroupsRightSearch.getFields().setData("groupsID", groupsId);

	Vector<AbstractOrmEntity> GroupsRights = Orm.select(GroupsRightSearch);
	for (AbstractOrmEntity entity : GroupsRights)
	{
	    GroupsRight GroupsRight = (GroupsRight) (entity);

	    rightResult.add(GroupsRight.getRightEntity());
	}

	return rightResult;
    }
}
