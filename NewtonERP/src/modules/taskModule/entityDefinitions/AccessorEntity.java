package modules.taskModule.entityDefinitions;

// TODO: clean up that file

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.FieldFactory;
import newtonERP.orm.fields.field.FieldType;
import newtonERP.orm.fields.field.type.FieldInt;

/**
 * Entité qui représente un accesseur
 * 
 * @author Guillaume Lacasse
 */
public class AccessorEntity extends AbstractOrmEntity {
	/**
	 */
	public AccessorEntity() {
		super();
		setVisibleName("Accesseur");
		AccessorManager.addAccessor(this, new EntityEntity());
	}

	@Override
	public Fields initFields() {
		Vector<Field> fieldList = new Vector<Field>();
		fieldList.add(new FieldInt("Entité parent", new EntityEntity().getForeignKeyName()));
		fieldList.add(FieldFactory.newField(FieldType.STRING, "foreignEntityName"));
		return new Fields(fieldList);
	}
}
