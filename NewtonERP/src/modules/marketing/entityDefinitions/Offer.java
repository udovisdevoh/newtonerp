package modules.marketing.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldCurrency;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

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
	// Gab: est-ce ce field que tu veux rendre read-only? car tu le met
	// readonly et ça marche
	FieldCurrency currencyField = new FieldCurrency("Prix", "Price");
	currencyField.setReadOnly(true);
	fieldsInit.add(currencyField);
	return new Fields(fieldsInit);
    }
}
