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
 * Entity defenition class representing a right
 * @author CloutierJo, r3hallejo
 */
public class Right extends AbstractOrmEntity implements PromptViewable
{
    /**
     * @throws Exception si création fail
     */
    public Right() throws Exception
    {
	super();
	setVisibleName("Droit");
    }

    public Fields initFields()
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Nom", "moduleName"));
	fieldsInit.add(new FieldString("Action", "actionName"));
	fieldsInit.add(new FieldString("Entité", "entityName"));

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
    public AbstractEntity editUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	/*
	 * On ne veut pas permettre la modification de droit alors on redirige
	 * vers GetList
	 */
	EntityList entityList = (EntityList) super.getList();
	return entityList;
    }

    @Override
    public final AbstractEntity getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	// On ne veut pas permettre l'effacement ni la modification de droit
	// alors on enlève les
	// bouton Delete et Modifier en passant son nom de caption en argument
	EntityList entityList = (EntityList) super.getList(parameters);
	entityList.removeSpecificActionButton("Effacer");
	entityList.removeSpecificActionButton("Modifier");
	return entityList;
    }
}
