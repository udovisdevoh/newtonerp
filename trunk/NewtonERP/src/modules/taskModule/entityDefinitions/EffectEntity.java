package modules.taskModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldText;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Représente l'effet d'une task son action son entité de recherche ses
 * paramètres custom
 * @author Guillaume Lacasse
 */
public class EffectEntity extends AbstractOrmEntity implements PromptViewable
{
    /**
     * @throws Exception si création fail
     */
    public EffectEntity() throws Exception
    {
	super();
	setVisibleName("Effet");
	addNaturalKey("name");
	AccessorManager.addAccessor(this, new SearchEntity());
	AccessorManager.addAccessor(this, new Parameter());
	AccessorManager.addAccessor(this, new ActionEntity());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
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
     * @throws Exception si exécution fail
     */
    public void execute() throws Exception
    {
	System.out.println("Exécution d'une tâche");
	AbstractAction action = getAction();
	Vector<AbstractOrmEntity> entityList = getAffectedEntityList();
	for (AbstractOrmEntity entity : entityList)
	    action.doAction(entity, getParameters(entity));
    }

    private Vector<AbstractOrmEntity> getAffectedEntityList() throws Exception
    {
	AbstractOrmEntity searchEntity = getSearchEntity();
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
	    value = replaceToEntityFieldValue(entity, key, value);
	    parameters.put(key, value);
	}

	return parameters;
    }

    private String replaceToEntityFieldValue(AbstractOrmEntity entity,
	    String key, String value)
    {
	return value.replace(":" + entity.getSystemName() + "." + key, entity
		.getDataString(key));
    }

    private AbstractAction getAction() throws Exception
    {
	ActionEntity actionEntity = getActionEntity();
	return actionEntity.getAction();
    }

    private ActionEntity getActionEntity() throws Exception
    {
	return (ActionEntity) getSingleAccessor(new ActionEntity()
		.getForeignKeyName());
    }
}