package modules.userRightModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldInt;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.Fields;
import newtonERP.module.generalEntity.EntityList;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * @author r3hallejo
 * 
 *         Entity defenition class representing a right
 */
public class Right extends AbstractOrmEntity implements PromptViewable
{
    public Right() throws Exception
    {
	super();
    }

    public Fields initFields()
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro du droit", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom de module", "moduleName"));
	fieldsInit.add(new FieldString("Nom de l'action", "actionName"));
	fieldsInit.add(new FieldString("Nom de l'entité", "entityName"));

	addNaturalKey("moduleName");
	addNaturalKey("entityName");
	addNaturalKey("actionName");

	return new Fields(fieldsInit);
    }

    @Override
    public AbstractEntity deleteUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	/*
	 * On ne veut pas permettre l'effacement de droit alors on redirige
	 * l'effacement vers GetList
	 */
	EntityList entityList = (EntityList) super.getList();
	return entityList;
    }

    @Override
    public final AbstractEntity getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	// On ne veut pas permettre l'effacement de droit alors on enlève le
	// bouton Delete en passant son nom de caption en argument
	EntityList entityList = (EntityList) super.getList(parameters);
	entityList.removeSpecificActionButton("Effacer");
	return entityList;
    }
}
