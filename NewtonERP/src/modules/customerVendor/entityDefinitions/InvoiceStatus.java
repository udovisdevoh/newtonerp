package modules.customerVendor.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;

/**
 * Represents an invoice status
 * 
 * @author r3hallejo
 */
public class InvoiceStatus extends AbstractOrmEntity
{

    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public InvoiceStatus() throws Exception
    {
	super();
	setVisibleName("Status");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numero", getPrimaryKeyName()));
	fieldList.add(new FieldString("Status", "status"));
	return new Fields(fieldList);
    }

}
