package modules.marketing.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldCurrency;
import newtonERP.orm.field.type.FieldInt;

/**
 * Entité d'une promotion
 * @author Gabriel
 * 
 */
public class LineOffer extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public LineOffer() throws Exception
    {
	super();
	setVisibleName("Offre");
	AccessorManager.addAccessor(this, new Offer());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldInt("Offre", new Offer().getForeignKeyName()));
	FieldCurrency currencyField = new FieldCurrency("Prix", "Price");
	// currencyField.setReadOnly(true);
	fieldsInit.add(currencyField);
	return new Fields(fieldsInit);
    }
}