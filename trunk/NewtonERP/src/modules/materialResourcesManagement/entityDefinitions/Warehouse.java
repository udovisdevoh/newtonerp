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
	AccessorManager.addAccessor(this, new Product());
	AccessorManager.addAccessor(this, new Address());
	setVisibleName("Entrepôts");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit
		.add(new FieldInt("Numero de l'entrepot", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom de l'entrepôt", "warehouseName"));

	// TODO : changer le nom pour le foreign key name de address
	fieldsInit.add(new FieldInt("Adresse", "addressID"));
	return new Fields(fieldsInit);
    }
}
