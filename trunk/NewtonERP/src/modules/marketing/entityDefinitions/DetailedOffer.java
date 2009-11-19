package modules.marketing.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldCurrency;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.FieldText;
import newtonERP.orm.field.Fields;

/**
 * Entité d'une promotion
 * @author Gabriel
 * 
 */
public class DetailedOffer extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public DetailedOffer() throws Exception
    {
	super();
	setVisibleName("Offre détaillé");
	AccessorManager.addAccessor(this, new Offer());
    }

    @Override
    public Fields initFields() throws Exception
    {

	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldInt("Offre", new Offer().getForeignKeyName()));
	fieldsInit.add(new FieldString("Nom Du detail", "detailName"));
	fieldsInit.add(new FieldText("details", "detail"));
	fieldsInit.add(new FieldCurrency("Prix", "Price"));
	return new Fields(fieldsInit);
    }
}
