package modules.customerVendor.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * @author Gabriel
 * 
 *         Entité du fournisseur dans le module customerVendor
 */
public class Vendor extends AbstractOrmEntity implements PromptViewable
{
    /**
     * @throws Exception si création fail
     */
    public Vendor() throws Exception
    {
	super();
    }

    public Fields initFields()
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom", "name"));
	fieldsInit.add(new FieldString("Téléphone", "phone"));
	fieldsInit.add(new FieldString("Adresse", "address"));
	return new Fields(fieldsInit);
    }
}
