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
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author Gabriel entité du client dans le module customerVendor
 */
public class Customer extends AbstractOrmEntity
{
    @Override
    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("Numéro du client", getPrimaryKeyName()));
	fields.add(new FieldString("Nom du client", "customerName"));
	fields.add(new FieldString("Téléphone du client", "customerPhone"));
	fields.add(new FieldString("Adresse du client", "customerAddress"));
	return new Fields(fields);
    }

    public Customer getCustomerEntity()
    {
	Vector<String> search = new Vector<String>();
	search.add(getPrimaryKeyName() + "="
		+ getFields().getField(getPrimaryKeyName()));

	try
	{
	    return (Customer) Orm.select(new Customer(), search).get(0);
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
