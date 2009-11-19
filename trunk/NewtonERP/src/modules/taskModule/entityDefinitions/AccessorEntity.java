package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;

/**
 * Entité qui représente un accesseur
 * @author Guillaume Lacasse
 */
public class AccessorEntity extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public AccessorEntity() throws Exception
    {
	super();
	setVisibleName("Accesseur");
	AccessorManager.addAccessor(this, new EntityEntity());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldInt("Entité parent", new EntityEntity()
		.getForeignKeyName()));
	fieldList.add(new FieldString("Entité étrangère", "foreignEntityName"));
	return new Fields(fieldList);
    }
}
