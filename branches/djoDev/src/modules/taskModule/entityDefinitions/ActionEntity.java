package modules.taskModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.taskModule.actions.GenerateActionCode;
import modules.taskModule.actions.ViewActionSource;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;
import newtonERP.module.exception.ActionNotFoundException;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Représente une action définie dans une entitée
 * @author Guillaume Lacasse
 */
public class ActionEntity extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public ActionEntity() throws Exception
    {
	super();
	setVisibleName("Action");
	AccessorManager.addAccessor(this, new ModuleEntity());
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldInt moduleEntity = new FieldInt("Module", new ModuleEntity()
		.getForeignKeyName());
	moduleEntity.setReadOnly(true);

	FieldString systemName = new FieldString("Nom système", "systemName");
	systemName.setNaturalKey(true);

	Vector<Field<?>> fieldList = new Vector<Field<?>>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(systemName);
	fieldList.add(moduleEntity);
	return new Fields(fieldList);
    }

    @Override
    public AbstractEntity deleteUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	/*
	 * On ne veut pas permettre l'effacement d'action alors on redirige
	 * l'effacement vers GetList
	 */
	ListViewerData entityList = super.getList();
	return entityList;
    }

    @Override
    public final ListViewerData getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	parameters.put(getPrimaryKeyName(), "&");

	ListViewerData entityList = super.getList(parameters);
	entityList.addSpecificActionButtonList(new ActionLink("Voir source",
		new ViewActionSource(), parameters));
	entityList.addSpecificActionButtonList(new ActionLink("Générer source",
		new GenerateActionCode(), parameters));

	// On doit créer une action seulement avec le [+] de son module
	entityList.removeGlobalActions("Nouveau " + getVisibleName());

	return entityList;
    }

    /**
     * @return retourne une instance de vrai action selon l'entité d'action
     * @throws Exception si obtention fail
     */
    public AbstractAction getAction() throws Exception
    {
	String actionName = getActionName();
	Module module = getModuleEntity().getModule();
	return module.getAction(actionName);
    }

    /**
     * @return module entity
     * @throws Exception si ça fail
     */
    public ModuleEntity getModuleEntity() throws Exception
    {
	return (ModuleEntity) getSingleAccessor(new ModuleEntity()
		.getForeignKeyName());
    }

    /**
     * @return nom de l'action
     */
    public String getActionName()
    {
	return getDataString("systemName");
    }

    /**
     * @param entity entité
     * @return base action correspondant à la définition d'entité
     * @throws ActionNotFoundException remonte
     */
    public BaseAction getBaseAction(AbstractOrmEntity entity)
	    throws ActionNotFoundException
    {
	return new BaseAction(getActionName(), entity);
    }
}
