package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.orm.field.type.FieldText;

/**
 * Paramètres custom utilisé par effet de task
 * @author Guillaume Lacasse
 */
public class Parameter extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public Parameter() throws Exception
    {
	super();
	setVisibleName("Paramêtre générique");
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldString key = new FieldString("Nom de clef", "key");
	key.setNaturalKey(true);

	FieldText value = new FieldText("Valeur", "value", false);
	value.setNaturalKey(true);

	Vector<Field<?>> fieldList = new Vector<Field<?>>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(key);
	fieldList.add(value);

	return new Fields(fieldList);
    }

    /**
     * @return clef du paramètre
     */
    public String getKey()
    {
	return getDataString("key");
    }

    /**
     * @return valeur du paramètre
     */
    public String getValue()
    {
	return getDataString("value");
    }
}
