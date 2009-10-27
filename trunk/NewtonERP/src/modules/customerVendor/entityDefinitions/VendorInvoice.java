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
 * @author Gabriel
 * 
 *         Entité du des facture des fournisseur dans le module customerVendor
 */
public class VendorInvoice extends AbstractOrmEntity
{

    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("Numéro de la Facture", getPrimaryKeyName()));
	fields.add(new FieldDouble("Total de la Facture", "total"));
	fields.add(new FieldInt("Numéros du client", "vendorID"));

	// FIXME : Tu store une date dans un field int???? et pourquoi cette
	// restriction de format?
	fields.add(new FieldInt("Date de la facture", "date"));
	return new Fields(fields);
    }

    // FIXME : C'est une action, a mettre dans les actions. Arrete le copier
    // coller getCustomerEntity dans une classe VendorInvoice c louche un peu
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
