package modules.humanResources.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Représente un département dans une compagnie
 * @author Guillaume
 */
public class SalaryType extends AbstractOrmEntity implements PromptViewable
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
	Vector<Field> fieldsData = new Vector<Field>();
	fieldsData.add(new FieldInt("Numéro de type", getPrimaryKeyName()));
	fieldsData.add(new FieldString("type", "SalaryType"));

	addNaturalKey("SalaryType");
	return new Fields(fieldsData);
    }

}
