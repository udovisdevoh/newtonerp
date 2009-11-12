package modules.customerVendor.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Represents an invoice tax line.
 * 
 * @author r3hallejo
 */
public class InvoiceTaxLine extends AbstractOrmEntity implements PromptViewable
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public InvoiceTaxLine() throws Exception
    {
	super();
	AccessorManager.addAccessor(this, new Tax());
	AccessorManager.addAccessor(this, new Invoice());
	setVisibleName("Lignes de taxes");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numero", getPrimaryKeyName()));
	fieldsInit.add(new FieldInt("Facture associée", new Invoice()
		.getForeignKeyName()));
	fieldsInit.add(new FieldInt("Numero de taxe", new Tax()
		.getForeignKeyName()));
	return new Fields(fieldsInit);
    }

}
