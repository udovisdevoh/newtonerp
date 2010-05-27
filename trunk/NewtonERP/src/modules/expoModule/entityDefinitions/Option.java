package modules.expoModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldCurrency;
import newtonERP.orm.field.type.FieldDouble;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Option
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class Option extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public Option() throws Exception
    {
	super();
	setVisibleName("Option");
	setDetailedDescription("option de zone de kiosque");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKoptionID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKoptionID);

	FieldString name = new FieldString("Description", "name");
	name.setNaturalKey(true);
	fieldList.add(name);

	FieldCurrency prix = new FieldCurrency("Prix", "price");
	prix.setNaturalKey(true);
	fieldList.add(prix);
	return new Fields(fieldList);
    }

    /**
     * @return prix de l'option
     */
    public double getPrice()
    {
	FieldDouble fieldDoublePrice = (FieldDouble) getFields().getField(
		"price");
	return fieldDoublePrice.getData();
    }
}
