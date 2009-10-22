/**
 * 
 */
package modules.costumerVendor.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;

/**
 * @author Gabriel entité du fournisseur dans le module costumerVendor
 */
public class Vendor extends AbstractOrmEntity
{

    public Fields initFields1()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("numéro du Fournisseur", "pKVendorID"));
	fields.add(new FieldString("nom du Fournisseur", "vendorName"));
	fields.add(new FieldString("téléphone du Fournisseur", "vendorPhone"));
	fields.add(new FieldString("adresse du Fournisseur", "vendorAdress"));
	return new Fields(fields);
    }

    public Vendor getCostumerEntity()
    {
	Vector<String> search = new Vector<String>();
	search.add("PKVendorID=" + getFields().getField("PKVendorID"));

	try
	{
	    return (Vendor) Orm.select(new Vendor(), search).get(0);
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
     * @see newtonERP.module.AbstractOrmEntity#initFields()
     */
    @Override
    public Fields initFields()
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
