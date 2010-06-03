package modules.finances.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldBool;
import newtonERP.orm.field.type.FieldDate;
import newtonERP.orm.field.type.FieldInt;

/**
 * Entité PayablePeriod du module finances: représente les périodes de paie pour
 * l'année en cour.
 * 
 * @author Pascal Lemay
 */
public class PayablePeriod extends AbstractOrmEntity
{
	/**
	 * @throws Exception if creation fails
	 */
	public PayablePeriod() throws Exception
	{
		super();
		setVisibleName("Périodes de paies");
	}

	@Override
	public Fields initFields() throws Exception
	{
		Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
		FieldInt primaryKey = new FieldInt("Numéro", getPrimaryKeyName());
		primaryKey.setNaturalKey(true);
		fieldsInit.add(primaryKey);

		fieldsInit.add(new FieldDate("Début de période", "beginning"));
		fieldsInit.add(new FieldDate("Fin de période", "end"));
		fieldsInit.add(new FieldBool("Période actuelle", "isCurrentPeriod"));

		return new Fields(fieldsInit);
	}

}
