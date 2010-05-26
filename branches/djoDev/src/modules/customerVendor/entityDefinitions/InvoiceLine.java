package modules.customerVendor.entityDefinitions;

import java.util.Vector;

import modules.materialResourcesManagement.entityDefinitions.Product;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldCurrency;
import newtonERP.orm.field.type.FieldInt;

/**
 * An invoice line
 * 
 * @author r3hallejo
 */
public class InvoiceLine extends AbstractOrmEntity
{

    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public InvoiceLine() throws Exception
    {
	super();
	AccessorManager.addAccessor(this, new Product());
	AccessorManager.addAccessor(this, new Invoice());
	setVisibleName("Ligne de facture");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldInt("Numéro de facture", new Invoice()
		.getForeignKeyName()));
	fieldList.add(new FieldInt("Id de produit", new Product()
		.getForeignKeyName()));
	fieldList.add(new FieldInt("Quantité", "quantity"));
	fieldList.add(new FieldCurrency("Prix unitaire", "unitPrice"));
	return new Fields(fieldList);
    }

}
