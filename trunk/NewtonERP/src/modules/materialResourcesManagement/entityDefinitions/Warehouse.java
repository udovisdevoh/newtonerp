package modules.materialResourcesManagement.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * A warehouse
 * 
 * @author r3hallejo
 */
public class Warehouse extends AbstractOrmEntity implements PromptViewable
{

    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public Warehouse() throws Exception
    {
	super();
	// TODO : Comment on fait une relation de 1 a 1???
	// AccessorManager.addAccessor(this, new Address());
	AccessorManager.addAccessor(this, new Product());
	AccessorManager.addAccessor(this, new Address());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit
		.add(new FieldInt("Numero de warehouse", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom de l'entrpôt", "warehouseName"));
	fieldsInit.add(new FieldString("Adresse", "addressID"));
	return new Fields(fieldsInit);
    }
}
