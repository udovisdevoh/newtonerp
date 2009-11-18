package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldBool;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;

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

	Vector<Field> fieldList = new Vector<Field>();
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

	return new Fields(fieldList);
    }
}
