package modules.humanResources.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldDate;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.Fields;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Représente un département dans une compagnie
 * @author Guillaume
 */
public class schedule extends AbstractOrmEntity implements PromptViewable
{

    /**
     * @throws Exception si création fails
     */
    public schedule() throws Exception
    {
	super();

	AccessorManager.addAccessor(this, new Employee());
	AccessorManager.addAccessor(this, new PeriodeType());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsData = new Vector<Field>();

	fieldsData.add(new FieldInt("Numéro d'horair", getPrimaryKeyName()));

	fieldsData.add(new FieldInt("Numéro d'employer", new Employee()
		.getForeignKeyName()));
	fieldsData.add(new FieldDate("début de la période", "timeStart"));
	// todo: j'ai besoins des heures
	fieldsData.add(new FieldDate("fin de la période", "timeStop"));
	fieldsData.add(new FieldInt("type de la période", new PeriodeType()
		.getForeignKeyName()));

	return new Fields(fieldsData);
    }
}
