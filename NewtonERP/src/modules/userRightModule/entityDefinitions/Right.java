package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.userRightModule.UserRightModule;
import modules.userRightModule.actions.GetRightList;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.exception.FieldNotFoundException;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.module.generalEntity.ForwardEntity;
import newtonERP.serveur.Servlet;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * @author r3hallejo
 * 
 *         Entity defenition class representing a right
 */
public class Right extends AbstractOrmEntity implements PromptViewable
{
    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("Numéro du droit", getPrimaryKeyName()));
	fields.add(new FieldString("Nom de module", "moduleName"));
	fields.add(new FieldString("Nom de l'action", "actionName"));
	fields.add(new FieldString("Nom de l'entité", "entityName"));
	return new Fields(fields);
    }

    @Override
    public AbstractEntity getAfterDeleteReturnEntity() throws Exception
    {
	return new ForwardEntity(Servlet.makeLink(new UserRightModule(),
		new GetRightList()));
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
	Right right = new Right();
	right.setCurrentModule(new UserRightModule());
	right.setData("moduleName", "aucun");
	right.setData("entityName", "aucun");
	right.setData("actionName", "aucun");
	// user.setSubmitAction(new BaseAction("Edit", this));

	((AbstractOrmEntity) right).newE();

	return new ForwardEntity(Servlet.makeLink(new UserRightModule(),
		new GetRightList()));
    }

    @Override
    public AbstractEntity editUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	/*
	 * for (Field field : getFields()) field.setOperator("=");
	 */

	Right searchEntity = new Right();
	searchEntity.setData(getPrimaryKeyName(), Integer
		.parseInt(getPrimaryKeyValue()));
	searchEntity.getFields().getField(getPrimaryKeyName()).setOperator("=");

	Right retRight = (Right) get(searchEntity).get(0); // on discarte les
	// autre
	retRight.setCurrentAction(new BaseAction("Edit", this));
	retRight.setCurrentModule(new UserRightModule());
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

	return retRight;
    }
}
