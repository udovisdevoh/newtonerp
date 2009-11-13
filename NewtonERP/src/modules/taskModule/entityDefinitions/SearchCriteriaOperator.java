package modules.taskModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.EntityList;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

public class SearchCriteriaOperator extends AbstractOrmEntity implements
	PromptViewable
{
    public SearchCriteriaOperator() throws Exception
    {
	super();
	setVisibleName("Opérateur");
	addNaturalKey("name");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldString("Nom", "name"));
	return new Fields(fieldList);
    }

    @Override
    public AbstractEntity deleteUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	/*
	 * On ne veut pas permettre l'effacement d'opérateur alors on redirige
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
	 * On ne veut pas permettre la modification d'opérateur alors on
	 * redirige vers GetList
	 */
	EntityList entityList = (EntityList) super.getList();
	return entityList;
    }

    @Override
    public final AbstractEntity getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	// On ne veut pas permettre l'effacement ni la modification d'opérateur
	// alors on enlève les
	// bouton Delete et Modifier en passant son nom de caption en argument
	EntityList entityList = (EntityList) super.getList(parameters);
	entityList.removeSpecificActionButton("Effacer");
	entityList.removeSpecificActionButton("Modifier");
	return entityList;
    }
}
