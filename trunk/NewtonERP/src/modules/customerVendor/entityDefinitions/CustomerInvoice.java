/**
 * 
 */
package modules.customerVendor.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldDouble;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.Fields;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author Gabriel entité du des facture des client dans le module
 *         customerVendor
 */
public class CustomerInvoice extends AbstractOrmEntity
{

    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("Numéro de la Facture", getPrimaryKeyName()));
	fields.add(new FieldDouble("Total de la Facture", "total"));// total de
	// la
	// facture
	fields.add(new FieldInt("Numéros du client", "customerID"));
	fields.add(new FieldInt("Date de la facture", "date")); // ddmmyyyy a
	// respecter
	return new Fields(fields);
    }

    public CustomerInvoice getCustomerEntity()
    {
	Vector<String> search = new Vector<String>();
	search.add(getPrimaryKeyName() + "="
		+ getFields().getField(getPrimaryKeyName()));

	try
	{
	    return (CustomerInvoice) Orm.select(new CustomerInvoice(), search)
		    .get(0);
	} catch (OrmException e)
	{
	    e.printStackTrace();
	}
	return null;

    }

    /*
     * (non-Javadoc)
     * 
     * @see newtonERP.module.AbstractOrmEntity#deleteUI(java.util.Hashtable)
     */
    @Override
    public AbstractEntity deleteUI(Hashtable<String, String> parameters)
    {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see newtonERP.module.AbstractOrmEntity#editUI(java.util.Hashtable)
     */
    @Override
    public AbstractEntity editUI(Hashtable<String, String> parameters)
    {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see newtonERP.module.AbstractOrmEntity#getUI(java.util.Hashtable)
     */
    @Override
    public AbstractEntity getUI(Hashtable<String, String> parameters)
    {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see newtonERP.module.AbstractOrmEntity#newUI(java.util.Hashtable)
     */
    @Override
    public AbstractEntity newUI(Hashtable<String, String> parameters)
    {
	// TODO Auto-generated method stub
	return null;
    }

}
