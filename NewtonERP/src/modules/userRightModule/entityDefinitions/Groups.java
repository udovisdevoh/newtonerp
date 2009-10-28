package modules.userRightModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.Orm;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Entity defenition class representing a group for the users
 * @author r3hallejo
 */
public class Groups extends AbstractOrmEntity implements PromptViewable
{
    private static GroupRight groupRightDefinition = new GroupRight();
    private static Right rightDefinition = new Right();

    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("Numéro du groupe", getPrimaryKeyName()));
	fields.add(new FieldString("Nom du groupe", "groupName"));

	// Ajout du flag pool. Sert à choisir les droits via GroupRight et Right
	String[] foreignUiControlKeys = { "moduleName", "entityName",
		"actionName" };

	addFlagPool(this, "Droits", groupRightDefinition, "groupsID",
		"rightID", rightDefinition,
		rightDefinition.getPrimaryKeyName(), foreignUiControlKeys);

	/*
	 * Sera simplifié tel que: addFlagPool(this, "Droits", rightDefinition,
	 * rightDefinition.getPrimaryKeyName(), foreignUiControlKeys);
	 */

	return new Fields(fields);
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
	GroupRight groupRightSearch = new GroupRight();
	String groupsId = getFields().getField(getPrimaryKeyName())
		.getDataString();

	groupRightSearch.getFields().setData("groupsID", groupsId);
	groupRightSearch.getFields().getField("groupsID").setOperator("=");

	Vector<AbstractOrmEntity> groupRights = Orm.select(groupRightSearch);
	for (AbstractOrmEntity entity : groupRights)
	{
	    GroupRight groupRight = (GroupRight) (entity);

	    rightResult.add(groupRight.getRightEntity());
	}

	return rightResult;
    }
}
