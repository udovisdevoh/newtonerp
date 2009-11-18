package modules.humanResources.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;

/**
 * Représente un département dans une compagnie
 * @author Guillaume
 */
public class PeriodeType extends AbstractOrmEntity
{

    /**
     * @throws Exception si création fails
     */
    public PeriodeType() throws Exception
    {
	super();
	setVisibleName("Type de période");
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldString periodType = new FieldString("type", "PeriodeType");
	periodType.setNaturalKey(true);

	Vector<Field> fieldsData = new Vector<Field>();
	fieldsData.add(new FieldInt("Numéro de type", getPrimaryKeyName()));
	fieldsData.add(periodType);
	return new Fields(fieldsData);
    }

}
