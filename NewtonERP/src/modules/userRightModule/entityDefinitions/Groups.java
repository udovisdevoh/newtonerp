package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.UserRightModule;
import modules.userRightModule.actions.GetGroupsList;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.ForwardEntity;
import newtonERP.module.exception.FieldNotFoundException;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.Orm;
import newtonERP.serveur.Servlet;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * @author r3hallejo
 * 
 *         Entity defenition class representing a group for the users
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
	String[] foreignUiControlKeys = { "moduleName", "actionName" };
	addFlagPool("Droits", groupRightDefinition, "groupID", "rightID",
		rightDefinition, rightDefinition.getPrimaryKeyName(),
		foreignUiControlKeys);

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

    @Override
    public AbstractEntity getAfterDeleteReturnEntity() throws Exception
    {
	return new ForwardEntity(Servlet.makeLink(new UserRightModule(),
		new GetGroupsList()));
    }

    @Override
    public AbstractEntity newUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	Groups groups = new Groups();
	groups.setCurrentModule(new UserRightModule());
	groups.setData("groupName", "sans-nom");
	// user.setSubmitAction(new BaseAction("Edit", this));

	((AbstractOrmEntity) groups).newE();

	return new ForwardEntity(Servlet.makeLink(new UserRightModule(),
		new GetGroupsList()));
    }

    @Override
    public AbstractEntity editUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	Groups searchEntity = new Groups();
	searchEntity.setData(getPrimaryKeyName(), Integer
		.parseInt(getPrimaryKeyValue()));
	searchEntity.getFields().getField(getPrimaryKeyName()).setOperator("=");

	Groups retGroups = (Groups) get(searchEntity).get(0); // on discarte les
	// autre
	retGroups.setCurrentAction(new BaseAction("Edit", this));
	retGroups.setCurrentModule(new UserRightModule());
	// entity
	// s'il y a lieu

	if (parameters.containsKey("submit"))
	{
	    for (String parameterKey : parameters.keySet())
	    {
		try
		{
		    getFields().setData(parameterKey,
			    parameters.get(parameterKey));
		} catch (FieldNotFoundException e)
		{
		    // Ce catch est vide car seul les fields existant peuvent
		    // être modifiés par des paramètres - Guillaume
		    continue;
		}
	    }

	    edit(getPrimaryKeyName() + "='"
		    + getDataString(getPrimaryKeyName()) + "'");

	    return new ForwardEntity(Servlet.makeLink(new UserRightModule(),
		    new BaseAction("Edit", searchEntity))
		    + "?"
		    + searchEntity.getPrimaryKeyName()
		    + "="
		    + searchEntity.getPrimaryKeyValue());
	}

	return retGroups;
    }
}
