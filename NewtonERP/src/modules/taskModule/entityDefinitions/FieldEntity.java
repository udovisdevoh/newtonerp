package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldBool;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Représente un champ
 * @author Guillaume Lacasse
 */
public class FieldEntity extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public FieldEntity() throws Exception
    {
	super();
	setVisibleName("Champ");
	AccessorManager.addAccessor(this, new FieldTypeEntity());
	AccessorManager.addAccessor(this, new EntityEntity());
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldString visibleName = new FieldString("Nom visible", "visibleName");
	visibleName.setNaturalKey(true);

	Vector<Field<?>> fieldList = new Vector<Field<?>>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldString("Nom", "name"));
	fieldList.add(visibleName);
	fieldList.add(new FieldInt("Type", new FieldTypeEntity()
		.getForeignKeyName()));
	fieldList.add(new FieldInt("Entité", new EntityEntity()
		.getForeignKeyName()));
	fieldList.add(new FieldBool("Lecture seule", "readOnly"));
	fieldList.add(new FieldBool("Caché", "hidden"));
	fieldList.add(new FieldBool("Clef naturelle", "naturalKey"));

	FieldBool dynamicField = new FieldBool("Champ dynamique",
		"dynamicField");
	fieldList.add(dynamicField);

	return new Fields(fieldList);
    }

    /**
     * @return retourne un vrai field
     * @throws Exception si création fail
     */
    public Field<?> getFieldInstance() throws Exception
    {
	String name = getDataString("name");
	String visibleName = getDataString("visibleName");
	String type = getFieldTypeEntity().getDataString("systemName");
	Boolean readOnly = (Boolean) getData("readOnly");
	Boolean hidden = (Boolean) getData("hidden");
	Boolean naturalKey = (Boolean) getData("naturalKey");
	Boolean dynamicField = (Boolean) getData("dynamicField");

	Field<?> field = (Field<?>) Class.forName("orm.field.Type." + type)
		.newInstance();

	field.setShortName(name);
	field.setVisibleName(visibleName);
	field.setHidden(hidden);
	field.setNaturalKey(naturalKey);
	field.setReadOnly(readOnly);
	field.setDynamicField(dynamicField);

	return field;
    }

    private FieldTypeEntity getFieldTypeEntity() throws Exception
    {
	return (FieldTypeEntity) getSingleAccessor("FieldTypeEntity");
    }
}
