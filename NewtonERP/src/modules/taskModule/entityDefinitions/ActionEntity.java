package modules.taskModule.entityDefinitions;

// TODO: clean up that file

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
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.FieldFactory;
import newtonERP.orm.fields.field.FieldType;
import newtonERP.orm.fields.field.property.NaturalKey;
import newtonERP.orm.fields.field.property.ReadOnly;
import newtonERP.orm.fields.field.type.FieldInt;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Représente une action définie dans une entitée
 * 
 * @author Guillaume Lacasse
 */
public class ActionEntity extends AbstractOrmEntity {
	/**
	 */
	public ActionEntity() {
		super();
		setVisibleName("Action");
		AccessorManager.addAccessor(this, new ModuleEntity());
	}

	@Override
	public Fields initFields() {
		Field moduleEntity = new FieldInt("Module", new ModuleEntity().getForeignKeyName());
		moduleEntity.addProperty(new ReadOnly());

		Field systemName = FieldFactory.newField(FieldType.STRING, "systemName");
		systemName.addProperty(new NaturalKey());

		Vector<Field> fieldList = new Vector<Field>();
		fieldList.add(systemName);
		fieldList.add(moduleEntity);
		return new Fields(fieldList);
	}

	@Override
	public AbstractEntity deleteUI(Hashtable<String, String> parameters)

	{
		/*
		 * On ne veut pas permettre l'effacement d'action alors on redirige l'effacement vers GetList
		 */
		ListViewerData entityList = super.getList();
		return entityList;
	}

	@Override
	public final ListViewerData getList(Hashtable<String, String> parameters)

	{
		parameters.put(getPrimaryKeyName(), "&");

		ListViewerData entityList = super.getList(parameters);
		entityList.addSpecificActionButtonList(new ActionLink("Voir source", new ViewActionSource(), parameters));
		entityList.addSpecificActionButtonList(new ActionLink("Générer source", new GenerateActionCode(), parameters));

		// On doit créer une action seulement avec le [+] de son module
		entityList.removeGlobalActions("Nouveau " + getVisibleName());

		return entityList;
	}

	/**
	 * @return retourne une instance de vrai action selon l'entité d'action
	 */
	public AbstractAction getAction() {
		String actionName = getActionName();
		Module module = getModuleEntity().getModule();
		return module.getAction(actionName);
	}

	/**
	 * @return module entity
	 */
	public ModuleEntity getModuleEntity() {
		return (ModuleEntity) getSingleAccessor(new ModuleEntity().getForeignKeyName());
	}

	/**
	 * @return nom de l'action
	 */
	public String getActionName() {
		return getDataString("systemName");
	}

	/**
	 * @param entity entité
	 * @return base action correspondant à la définition d'entité
	 */
	public BaseAction getBaseAction(AbstractOrmEntity entity) {
		return new BaseAction(getActionName(), entity);
	}
}
