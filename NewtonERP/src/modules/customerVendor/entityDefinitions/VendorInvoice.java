/**
 * 
 */
package modules.customerVendor.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldDouble;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.Fields;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author Gabriel entité du des facture des fournisseur dans le module
 *         customerVendor
 */
public class VendorInvoice extends AbstractOrmEntity
{

    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("Numéro de la Facture", getPrimaryKeyName()));
	fields.add(new FieldDouble("Total de la Facture", "total"));// total de
	// la
	// facture
	fields.add(new FieldInt("Numéros du client", "vendorID"));
	fields.add(new FieldInt("Date de la facture", "date")); // ddmmyyyy a
	// respecter
	return new Fields(fields);
    }

    public VendorInvoice getCostumerEntity()
    {
	Vector<String> search = new Vector<String>();
	search.add(getPrimaryKeyName() + "="
		+ getFields().getField(getPrimaryKeyName()));

	try
	{
	    return (VendorInvoice) Orm.select(new VendorInvoice(), search).get(
		    0);
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}
	return null;

    }

    @Override
    public AbstractEntity getAfterDeleteReturnEntity()
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AbstractEntity getUI(Hashtable<String, String> parameters)
	    throws InvalidOperatorException
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AbstractEntity newUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AbstractEntity editUI(Hashtable<String, String> parameters)
	    throws InvalidOperatorException, FieldNotCompatibleException
    {
	// TODO Auto-generated method stub
	return null;
    }
}
