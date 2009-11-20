package modules.production.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.Type.FieldInt;
import newtonERP.orm.field.Type.FieldString;

/**
 * A schedule type
 * 
 * @author r3hallejo
 */
public class EquipmentPeriodType extends AbstractOrmEntity
{
    /**
     * Default constructor
     * 
     * @throws Exception a general exception
     */
    public EquipmentPeriodType() throws Exception
    {
	setVisibleName("Type de période");
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldString periodType = new FieldString("Type", "periodType");
	periodType.setNaturalKey(true);

	Vector<Field<?>> fieldsData = new Vector<Field<?>>();
	fieldsData.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsData.add(periodType);
	return new Fields(fieldsData);
    }

}
