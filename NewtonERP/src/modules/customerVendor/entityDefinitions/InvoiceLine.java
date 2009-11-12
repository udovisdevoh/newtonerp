package modules.customerVendor.entityDefinitions;

import java.util.Vector;

import modules.materialResourcesManagement.entityDefinitions.Product;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldDouble;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * An invoice line
 * 
 * @author r3hallejo
 */
public class InvoiceLine extends AbstractOrmEntity implements PromptViewable
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
	addCurrencyFormat("unitPrice");
	setVisibleName("Ligne de facture");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldInt("Numéro de facture", new Invoice()
		.getForeignKeyName()));
	fieldList.add(new FieldInt("Id de produit", new Product()
		.getForeignKeyName()));
	fieldList.add(new FieldInt("Quantité", "quantity"));
	fieldList.add(new FieldDouble("Prix unitaire", "unitPrice"));
	return new Fields(fieldList);
    }

}
