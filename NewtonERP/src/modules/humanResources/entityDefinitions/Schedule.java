package modules.humanResources.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldDateTime;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.Fields;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Représente un département dans une compagnie
 * @author Guillaume
 */
public class Schedule extends AbstractOrmEntity implements PromptViewable
{

    /**
     * @throws Exception si création fails
     */
    public Schedule() throws Exception
    {
	super();

	setVisibleName("Horaire");
	AccessorManager.addAccessor(this, new Employee());
	AccessorManager.addAccessor(this, new PeriodeType());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsData = new Vector<Field>();

	fieldsData.add(new FieldInt("Numéro d'horaire", getPrimaryKeyName()));

	fieldsData.add(new FieldInt("Numéro d'employé", new Employee()
		.getForeignKeyName()));
	fieldsData.add(new FieldDateTime("Début de la période", "timeStart"));
	fieldsData.add(new FieldDateTime("Fin de la période", "timeStop"));
	fieldsData.add(new FieldInt("Type de la période", new PeriodeType()
		.getForeignKeyName()));

	return new Fields(fieldsData);
    }
}