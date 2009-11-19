package modules.materialResourcesManagement.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;

/**
 * Class representing a delivery type for a delivery
 * 
 * @author r3hallejo
 */
public class ShippingType extends AbstractOrmEntity
{

    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public ShippingType() throws Exception
    {
	super();
	setVisibleName("Type de livraison");
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldString shippingType = new FieldString("Type", "shippingType");
	shippingType.setNaturalKey(true);

	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	fieldsInit.add(new FieldInt("Numero du type", getPrimaryKeyName()));
	fieldsInit.add(shippingType);
	return new Fields(fieldsInit);
    }
}
