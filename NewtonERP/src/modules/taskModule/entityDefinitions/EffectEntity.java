package modules.taskModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldText;

/**
 * Représente l'effet d'une task son action son entité de recherche ses
 * paramètres custom
 * @author Guillaume Lacasse
 */
public class EffectEntity extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public EffectEntity() throws Exception
    {
	super();
	setVisibleName("Effet");
	AccessorManager.addAccessor(this, new SearchEntity());
	AccessorManager.addAccessor(this, new Parameter());
	AccessorManager.addAccessor(this, new ActionEntity());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldText("Description", "name", false));
	fieldList.add(new FieldInt("Entité de recherche", new SearchEntity()
		.getForeignKeyName()));
	fieldList.add(new FieldInt("Action", new ActionEntity()
		.getForeignKeyName()));
	return new Fields(fieldList);
    }

    /**
     * Exécute l'effet d'une tâche
     * @param entityParameters paramètres de l'entité
     * @param isStraightSearch si c'est une recherche
     * @return entité viewable en tant que résultat de la tâche
     * @throws Exception si exécution fail
     */
    public AbstractEntity execute(Hashtable<String, String> entityParameters,
	    boolean isStraightSearch) throws Exception
    {
	AbstractEntity returnEntity = null;

	System.out.println("Exécution d'une tâche");
	AbstractAction action = getAction();
	Vector<AbstractOrmEntity> entityList = getAffectedEntityList(
		entityParameters, isStraightSearch);
	for (AbstractOrmEntity entity : entityList)
	{
	    if (action instanceof BaseAction)
		returnEntity = doBaseAction((BaseAction) action, entity,
			getParameters(entity));
	    else
		returnEntity = action.doAction(entity, getParameters(entity));
	}

	return returnEntity;
    }

    private AbstractEntity doBaseAction(BaseAction baseAction,
	    AbstractOrmEntity entity, Hashtable<String, String> parameters)
	    throws Exception
    {
	if (baseAction.getSystemName().equals("Get"))
	    return entity.getUI(parameters);
	else if (baseAction.getSystemName().equals("Delete"))
	    return entity.deleteUI(parameters);
	else if (baseAction.getSystemName().equals("New"))
	    return entity.newUI(parameters);
	else if (baseAction.getSystemName().equals("GetList"))
	    return entity.getList(parameters);
	else if (baseAction.getSystemName().equals("GetList"))
	    return entity.editUI(parameters);
	throw new Exception("Base Entity type not found");
    }

    private Vector<AbstractOrmEntity> getAffectedEntityList(
	    Hashtable<String, String> entityParameters, boolean isStraightSearch)
	    throws Exception
    {
	AbstractOrmEntity searchEntity = getSearchEntity();

	if (isStraightSearch)
	    searchEntity.setData(searchEntity.getPrimaryKeyName(),
		    entityParameters.get(searchEntity.getPrimaryKeyName()));

	return Orm.select(searchEntity);
    }

    private AbstractOrmEntity getSearchEntity() throws Exception
    {
	SearchEntity searchEntity = (SearchEntity) getSingleAccessor(new SearchEntity()
		.getForeignKeyName());

	return searchEntity.getEntity();
    }

    private Hashtable<String, String> getParameters(AbstractOrmEntity entity)
	    throws Exception
    {
	Hashtable<String, String> rawParameters = getRawParameters();
	return parseDynamicParameters(rawParameters, entity);
    }

    private Hashtable<String, String> getRawParameters() throws Exception
    {
	Hashtable<String, String> rawParameters = new Hashtable<String, String>();

	PluralAccessor parameterList = this.getPluralAccessor("Parameter");

	Parameter parameter;
	for (AbstractOrmEntity entity : parameterList)
	{
	    parameter = (Parameter) entity;
	    rawParameters.put(parameter.getKey(), parameter.getValue());
	}

	return rawParameters;
    }

    private Hashtable<String, String> parseDynamicParameters(
	    Hashtable<String, String> parameters, AbstractOrmEntity entity)
    {
	String value;
	for (String key : parameters.keySet())
	{
	    value = parameters.get(key);
	    for (String variable : entity.getFields().getKeyList())
	    {
		value = replaceToEntityFieldValue(entity, value, variable);
		parameters.put(key, value);
	    }
	}

	return parameters;
    }

    private String replaceToEntityFieldValue(AbstractOrmEntity entity,
	    String value, String variable)
    {

	return value.replace(":" + entity.getSystemName() + "." + variable,
		entity.getDataString(variable));
    }

    private AbstractAction getAction() throws Exception
    {
	ActionEntity actionEntity = getActionEntity();

	if (BaseAction.isNameMatchesBaseAction(actionEntity.getActionName()))
	    return actionEntity.getBaseAction(getSearchEntity());
	return actionEntity.getAction();
    }

    private ActionEntity getActionEntity() throws Exception
    {
	return (ActionEntity) getSingleAccessor(new ActionEntity()
		.getForeignKeyName());
    }
}