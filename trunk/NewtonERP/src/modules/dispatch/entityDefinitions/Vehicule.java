package modules.dispatch.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.orm.field.type.FieldText;

/**
 * Vehicule
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class Vehicule extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public Vehicule() throws Exception
    {
	super();
	setVisibleName("Vehicule");
	AccessorManager.addAccessor(this, new Livreur());
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKvehiculeID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKvehiculeID);

	FieldString description = new FieldString("Description", "Description");
	description.setNaturalKey(true);
	fieldList.add(description);

	FieldText noteSpeciale = new FieldText("Note spéciale", "NoteSpeciale");
	fieldList.add(noteSpeciale);

	FieldInt livreurID = new FieldInt("Livreur assigné", "livreurID");
	fieldList.add(livreurID);
	return new Fields(fieldList);

	/*
	 * FieldInt fieldCalc = new FieldInt("calcule", "calcul");
	 * fieldCalc.setCalcul(new FieldCalcule<Integer>() { public Integer
	 * calcul(Fields entityFields) { return (Integer)
	 * entityFields.getField("nbVacancyDays") .getData() - (Integer)
	 * entityFields.getField("nbSicknessDays") .getData(); } });
	 */
    }
}
