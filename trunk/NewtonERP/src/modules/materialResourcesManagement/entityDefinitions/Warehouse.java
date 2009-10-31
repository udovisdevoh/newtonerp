package modules.materialResourcesManagement.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.viewers.viewables.PromptViewable;

public class Warehouse extends AbstractOrmEntity implements PromptViewable
{

    public Warehouse() throws Exception
    {
	super();
	// TODO : Comment on fait une relation de 1 a 1???
	// AccessorManager.addAccessor(this, new Address());
	AccessorManager.addAccessor(this, new Product());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit
		.add(new FieldInt("Numero de warehouse", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom de l'entrpôt", "warehouseName"));
	return new Fields(fieldsInit);
    }
}
