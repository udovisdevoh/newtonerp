package modules.taskModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import modules.taskModule.actions.GenerateEntityCode;
import modules.taskModule.actions.ViewEntitySource;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.Module;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Représente une définition d'entité pour une entité
 * @author Guillaume Lacasse
 */
public class EntityEntity extends AbstractOrmEntity implements
	Iterable<FieldEntity>
{
    /**
     * @throws Exception si création fail
     */
    public EntityEntity() throws Exception
    {
	super();
	setVisibleName("Entité");
	AccessorManager.addAccessor(this, new ModuleEntity());
    }

    protected Fields preInitFields() throws Exception
    {
	// always build the field from initField and not from DB, thats mean
	// that we cannot add a dynamic Field, this should not be done anywhere
	// else
	return initFields();
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldInt module = new FieldInt("Module", new ModuleEntity()
		.getForeignKeyName());
	module.setReadOnly(true);
	module.setNaturalKey(true);

	FieldString visibleName = new FieldString("Nom visible", "visibleName");
	visibleName.setNaturalKey(true);

	Vector<Field<?>> fieldList = new Vector<Field<?>>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(module);
	fieldList.add(new FieldString("Nom système", "systemName"));
	fieldList.add(visibleName);
	return new Fields(fieldList);
    }

    @Override
    public AbstractEntity deleteUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	/*
	 * On ne veut pas permettre l'effacement d'entité alors on redirige
	 * l'effacement vers GetList
	 */
	ListViewerData entityList = super.getList();
	return entityList;
    }

    @Override
    public ListViewerData getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	parameters.put(getPrimaryKeyName(), "&");

	ListViewerData entityList = super.getList(parameters);
	entityList.addSpecificActionButtonList(new ActionLink("Voir source",
		new ViewEntitySource(), parameters));
	entityList.addSpecificActionButtonList(new ActionLink("Générer source",
		new GenerateEntityCode(), parameters));

	// On doit créer une entité seulement avec le [+] de son module
	entityList.removeGlobalActions("Nouveau" + getVisibleName());

	return entityList;
    }

    /**
     * @return definition réele d'entité selon EntityEntity
     * @throws Exception si obtention fail
     */
    public AbstractOrmEntity getEntityDefinition() throws Exception
    {
	Module module = getModuleEntity().getModule();
	String entityName = getEntityName();
	return module.getEntityDefinition(entityName);
    }

    private String getEntityName()
    {
	return getDataString("systemName");
    }

    /**
     * @return entité du module
     * @throws Exception si ça fail
     */
    public ModuleEntity getModuleEntity() throws Exception
    {
	return (ModuleEntity) getSingleAccessor(new ModuleEntity()
		.getForeignKeyName());
    }

    @Override
    public Iterator<FieldEntity> iterator()
    {
	Vector<FieldEntity> fieldEntityVector = new Vector<FieldEntity>();
	PluralAccessor accessor;
	try
	{
	    accessor = getPluralAccessor("FieldEntity");
	    for (AbstractOrmEntity entity : accessor)
		fieldEntityVector.add((FieldEntity) entity);
	} catch (Exception e)
	{
	    e.printStackTrace();
	    // Obligé de faire un catch car iterator() ne peut pas avoir de
	    // throws pour lancer
	    // d'exception non-gérée, maudites exceptions de Java
	}

	return fieldEntityVector.iterator();
    }

    /**
     * @return true si l'entité d'entité a un entité de field qui correspond à
     *         sa clef primaire
     * @throws Exception si ça fail
     */
    public boolean ContainsPrimaryKeyField() throws Exception
    {
	String primaryKeyName = buildPrimaryKeyName();

	FieldEntity primaryKeyField = new FieldEntity();
	primaryKeyField.setData("name", primaryKeyName);
	primaryKeyField.assign(this);

	return Orm.select(primaryKeyField).size() > 0;
    }

    /**
     * @return primary key name for real entity (not entity entity)
     */
    public String buildPrimaryKeyName()
    {
	String primaryKeyName = getDataString("systemName");
	primaryKeyName = primaryKeyName.substring(0, 1).toLowerCase()
		+ primaryKeyName.substring(1);
	primaryKeyName = "PK" + primaryKeyName + "ID";
	return primaryKeyName;
    }
}