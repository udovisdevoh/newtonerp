package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.FieldText;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Paramètres custom utilisé par effet de task
 * @author Guillaume Lacasse
 */
public class Parameter extends AbstractOrmEntity implements PromptViewable
{
    /**
     * @throws Exception si création fail
     */
    public Parameter() throws Exception
    {
	super();
	setVisibleName("Paramêtre générique");
	addNaturalKey("key");
	addNaturalKey("value");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldString("Nom de clef", "key"));
	fieldList.add(new FieldText("Valeur", "value", false));
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
