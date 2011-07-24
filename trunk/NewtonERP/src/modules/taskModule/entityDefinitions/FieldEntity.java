package modules.taskModule.entityDefinitions; 
 // TODO: clean up that file

import java.util.Hashtable;
import java.util.Vector;

import modules.taskModule.actions.AddFieldToOrm;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.type.FieldBool;
import newtonERP.orm.fields.field.type.FieldCurrency;
import newtonERP.orm.fields.field.type.FieldDate;
import newtonERP.orm.fields.field.type.FieldDateTime;
import newtonERP.orm.fields.field.type.FieldDouble;
import newtonERP.orm.fields.field.type.FieldInt;
import newtonERP.orm.fields.field.type.FieldString;
import newtonERP.orm.fields.field.type.FieldText;
import newtonERP.orm.fields.field.type.FieldTime;
import newtonERP.viewers.viewerData.BaseViewerData;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Représente un champ
 * 
 * @author Guillaume Lacasse
 */
public class FieldEntity extends AbstractOrmEntity {
	/**
	 */
	public FieldEntity() {
		super();
		setVisibleName("Champ");
		AccessorManager.addAccessor(this, new FieldTypeEntity());
		AccessorManager.addAccessor(this, new EntityEntity());
	}

	@Override
	protected Fields preInitFields() {
		// always build the field from initField and not from DB, thats mean
		// that we cannot add a dynamic Field, this should not be done anywhere
		// else
		return initFields();
	}

	@Override
	public Fields initFields() {
		FieldString visibleName = new FieldString("Nom visible", "visibleName");
		visibleName.setNaturalKey(true);

		Vector<Field<?>> fieldList = new Vector<Field<?>>();
		fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
		fieldList.add(new FieldString("Nom", "name"));
		fieldList.add(visibleName);
		fieldList.add(new FieldInt("Type", new FieldTypeEntity().getForeignKeyName()));
		FieldInt entityID = new FieldInt("Entité", new EntityEntity().getForeignKeyName());
		entityID.setReadOnly(true);
		fieldList.add(entityID);
		fieldList.add(new FieldBool("Lecture seule", "readOnly"));
		fieldList.add(new FieldBool("Caché", "hidden"));
		fieldList.add(new FieldBool("Clef naturelle", "naturalKey"));

		FieldBool dynamicField = new FieldBool("Champ dynamique", "dynamicField");
		// dynamicField.setReadOnly(true);On doit pouvoir mettre certains champs
		// statiques pour génération de code d'entité
		fieldList.add(dynamicField);

		return new Fields(fieldList);
	}

	@Override
	public AbstractEntity newUI(Hashtable<String, String> parameters)

	{
		getFields().setDefaultValue(false);
		// parameters.put("dynamicField", "true"); On doit pouvoir aussi créer
		// des fields statiques pour softcoder des modules

		String entityEntityAccessorName = new EntityEntity().getForeignKeyName();
		if(parameters.containsKey(entityEntityAccessorName)){
			String entityEntityIdString = parameters.get(entityEntityAccessorName);
			int entityEntityId = Integer.parseInt(entityEntityIdString);
			EntityEntity entityEntity = getEntityEntity(entityEntityId);
			if(!entityEntity.containsPrimaryKeyField()){
				String primaryKeyName = entityEntity.buildPrimaryKeyName();
				if(!parameters.contains("name")){
					parameters.put("name", primaryKeyName);
					parameters.put("visibleName", "Numéro");
				}
			}
		}

		return editUI(parameters);
	}

	private EntityEntity getEntityEntity(int entityPrimaryKey) {
		EntityEntity entityEntity = new EntityEntity();
		entityEntity.setData(entityEntity.getPrimaryKeyName(), entityPrimaryKey);
		entityEntity = (EntityEntity) Orm.getInstance().selectUnique(entityEntity);

		return entityEntity;
	}

	@Override
	public ListViewerData getList(Hashtable<String, String> parameters)

	{
		parameters.put(getPrimaryKeyName(), "&");

		ListViewerData entityList = super.getList(parameters);

		// On doit pouvoir deleter les fields s'ils font partie de nouvelles
		// entitées pas encore générées
		// entityList.removeSpecificActions("Effacer");

		// On doit créer un field seulement avec le [+] de son entité
		entityList.removeGlobalActions("Nouveau " + getVisibleName());

		entityList.addSpecificActionButtonList(new ActionLink("Mettre dans Orm", new AddFieldToOrm(), parameters));
		return entityList;
	}

	/**
	 * @return retourne un vrai field
	 */
	public Field<?> getFieldInstance() {
		FieldTypeEntity fieldTypeEntity = getFieldTypeEntity();

		String name = getDataString("name");
		String visibleName = getDataString("visibleName");
		String type = fieldTypeEntity.getDataString("systemName");
		Boolean readOnly = (Boolean) getData("readOnly");
		Boolean hidden = (Boolean) getData("hidden");
		Boolean naturalKey = (Boolean) getData("naturalKey");
		Boolean dynamicField = (Boolean) getData("dynamicField");

		// je n'utilise pas Class.forName() mais on pourrait l'utiliser
		Field<?> field;
		if(type.equals("FieldBool")){
			field = new FieldBool(visibleName, name);
		}else if(type.equals("FieldCurrency")){
			field = new FieldCurrency(visibleName, name);
		}else if(type.equals("FieldDate")){
			field = new FieldDate(visibleName, name);
		}else if(type.equals("FieldDateTime")){
			field = new FieldDateTime(visibleName, name);
		}else if(type.equals("FieldDouble")){
			field = new FieldDouble(visibleName, name);
		}else if(type.equals("FieldInt")){
			field = new FieldInt(visibleName, name);
		}else if(type.equals("FieldString")){
			field = new FieldString(visibleName, name);
		}else if(type.equals("FieldText")){
			field = new FieldText(visibleName, name);
		}else if(type.equals("FieldTime")){
			field = new FieldTime(visibleName, name);
		}else{
			field = new FieldString(visibleName, name);
		}

		field.setHidden(hidden);
		field.setNaturalKey(naturalKey);
		field.setReadOnly(readOnly);
		field.setDynamicField(dynamicField);

		return field;
	}

	private FieldTypeEntity getFieldTypeEntity() {
		return (FieldTypeEntity) getSingleAccessor(new FieldTypeEntity().getForeignKeyName());
	}

	/**
	 * BaseAction Delete, on ne veut pas deleter de Field
	 * 
	 * @param parameters parametre suplementaire
	 * @return todo: qu'Est-ce que l'on devrai retourné en general?
	 */
	@Override
	public AbstractEntity deleteUI(Hashtable<String, String> parameters)

	{
		FieldEntity fieldEntity = (FieldEntity) Orm.getInstance().selectUnique(this);

		EntityEntity entityEntity = (EntityEntity) fieldEntity
		        .getSingleAccessor(new EntityEntity().getForeignKeyName());

		if(Orm.getInstance().isEntityExists(entityEntity.getDataString("systemName"))){
			BaseViewerData editUI = editUI(parameters);
			editUI.addAlertMessage("Impossible d'effacer ce champ car l'entité à laquelle il appartient est déjà dans la base de donnée.");
			return editUI;
		}

		// return getList();
		// On doit pouvoir deleter les fields s'ils font partie de nouvelles
		// entitées pas encore générées
		return super.deleteUI(parameters);
	}
}
