package modules.customerVendor.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;

/**
 * @author Gabriel
 * 
 *         Entité du fournisseur dans le module customerVendor
 */
public class Vendor extends AbstractOrmEntity
{

    public Fields initFields()
    {
	Vector<Field> fields = new Vector<Field>();
	fields.add(new FieldInt("Numéro du Fournisseur", getPrimaryKeyName()));
	fields.add(new FieldString("Nom du Fournisseur", "vendorName"));
	fields.add(new FieldString("Téléphone du Fournisseur", "vendorPhone"));
	fields.add(new FieldString("Adresse du Fournisseur", "vendorAddress"));
	return new Fields(fields);
    }
}
