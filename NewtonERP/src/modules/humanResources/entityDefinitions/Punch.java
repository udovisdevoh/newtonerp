package modules.humanResources.entityDefinitions;

import java.util.GregorianCalendar;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldCalcule;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldDateTime;
import newtonERP.orm.field.type.FieldDouble;
import newtonERP.orm.field.type.FieldInt;

/**
 * Représente un département dans une compagnie
 * @author Guillaume, CloutierJo
 */
public class Punch extends AbstractOrmEntity
{

    /**
     * @throws Exception si création fails
     */
    public Punch() throws Exception
    {
	super();
	setVisibleName("Département");
	AccessorManager.addAccessor(this, new Employee());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldsData = new Vector<Field<?>>();
	fieldsData.add(new FieldInt("No de Punch", getPrimaryKeyName()));
	fieldsData.add(new FieldInt("Employer", new Employee()
		.getForeignKeyName()));
	fieldsData.add(new FieldDateTime("Arrivé", "in"));
	fieldsData.add(new FieldDateTime("Départ", "out"));
	FieldDouble diff = new FieldDouble("Durée", "diff");
	diff.setCalcul(new FieldCalcule<Double>()
	{
	    protected Double calcul(Fields entityFields)
	    {
		GregorianCalendar in = (GregorianCalendar) entityFields
			.getField("in").getData();
		GregorianCalendar out = (GregorianCalendar) entityFields
			.getField("out").getData();
		long msDiff = out.getTimeInMillis() - in.getTimeInMillis();
		msDiff /= (1000 * 60);

		return ((double) msDiff) / 60;
	    }
	});
	fieldsData.add(diff);
	return new Fields(fieldsData);
    }
}
