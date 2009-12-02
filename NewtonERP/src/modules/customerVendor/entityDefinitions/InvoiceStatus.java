package modules.customerVendor.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.viewers.viewerData.ListViewerData;

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
	Vector<Field<?>> fieldList = new Vector<Field<?>>();
	fieldList.add(new FieldInt("Numero", getPrimaryKeyName()));
	fieldList.add(new FieldString("Status", "status"));
	return new Fields(fieldList);
    }

    @Override
    public final ListViewerData getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	// FIXME: Does not work
	Hashtable<String, String> actionParameters = new Hashtable<String, String>();
	actionParameters.put(getPrimaryKeyName(), "&");

	ListViewerData entityList = super.getList(parameters);
	entityList.removeSpecificActions("Effacer");
	entityList.removeSpecificActions("Modifier");

	return entityList;
    }
}
