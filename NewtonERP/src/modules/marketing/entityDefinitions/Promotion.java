package modules.marketing.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.Type.FieldCurrency;
import newtonERP.orm.field.Type.FieldDate;
import newtonERP.orm.field.Type.FieldInt;
import newtonERP.orm.field.Type.FieldString;
import newtonERP.orm.field.Type.FieldText;

/**
 * Entité d'une promotion
 * @author Gabriel
 * 
 */
public class Promotion extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public Promotion() throws Exception
    {
	super();
	setVisibleName("Promotion");
	AccessorManager.addAccessor(this, new Sector());
    }

    @Override
    public Fields initFields() throws Exception
    {

	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom De La Promotion", "promotionname"));
	fieldsInit.add(new FieldDate("Date du début de la promotion",
		"startDate"));
	fieldsInit.add(new FieldDate("Date de la fin de la promotion",
		"endingDate"));
	fieldsInit.add(new FieldCurrency("Budjet accordé", "budget"));
	fieldsInit.add(new FieldText("Notes de la modalité", "modality"));
	fieldsInit
		.add(new FieldInt("Secteur", new Sector().getForeignKeyName()));
	return new Fields(fieldsInit);
    }
}
