package modules.marketing.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldCurrency;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;

/**
 * Entité d'une promotion
 * @author Gabriel
 * 
 */
public class Offer extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public Offer() throws Exception
    {
	super();
	setVisibleName("Offre");
    }

    @Override
    public Fields initFields() throws Exception
    {

	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom De l'offre", "Offre"));
	fieldsInit.add(new FieldCurrency("Prix", "Price"));
	return new Fields(fieldsInit);
    }
}
