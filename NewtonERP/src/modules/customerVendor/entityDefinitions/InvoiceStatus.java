package modules.customerVendor.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.Type.FieldInt;
import newtonERP.orm.field.Type.FieldString;
import newtonERP.viewers.viewerData.BaseViewerData;
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
    public BaseViewerData editUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	/*
	 * On ne veut pas permettre la modification de droit alors on redirige
	 * vers GetList
	 */
	ListViewerData entityList = super.getList();
	return entityList;
    }

    @Override
    public AbstractEntity deleteUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	/*
	 * On ne veut pas permettre l'effacement de droit alors on redirige
	 * l'effacement vers GetList
	 */
	ListViewerData entityList = super.getList();
	return entityList;
    }
}
