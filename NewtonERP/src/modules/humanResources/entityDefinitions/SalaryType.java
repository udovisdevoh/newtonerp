package modules.humanResources.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Représente un département dans une compagnie
 * @author Guillaume
 */
public class SalaryType extends AbstractOrmEntity
{

    /**
     * @throws Exception si création fails
     */
    public SalaryType() throws Exception
    {
	super();
	setVisibleName("Type de salaire");
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldString salaryType = new FieldString("type", "SalaryType");
	salaryType.setNaturalKey(true);

	Vector<Field<?>> fieldsData = new Vector<Field<?>>();
	fieldsData.add(new FieldInt("Numéro de type", getPrimaryKeyName()));
	fieldsData.add(salaryType);

	return new Fields(fieldsData);
    }
}
