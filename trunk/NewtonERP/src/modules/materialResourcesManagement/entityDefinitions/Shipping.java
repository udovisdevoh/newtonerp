package modules.materialResourcesManagement.entityDefinitions;

import java.util.Vector;

import modules.customerVendor.entityDefinitions.Invoice;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldDate;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.Fields;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * A delivery when a new Invoice (Customer or Vendor) has been generated
 * 
 * @author r3hallejo
 */
public class Shipping extends AbstractOrmEntity implements PromptViewable
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public Shipping() throws Exception
    {
	super();
	setVisibleName("Livraison");
	AccessorManager.addAccessor(this, new ShippingType());
	AccessorManager.addAccessor(this, new Invoice());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit
		.add(new FieldInt("Numero de livraison", getPrimaryKeyName()));
	fieldsInit.add(new FieldInt("Type de livraison", "shippingTypeID"));
	fieldsInit.add(new FieldDate("Date estimée de livraison",
		"estimatedShippingDate"));
	fieldsInit.add(new FieldInt("Facture associée", new Invoice()
		.getForeignKeyName()));
	return new Fields(fieldsInit);
    }

}
