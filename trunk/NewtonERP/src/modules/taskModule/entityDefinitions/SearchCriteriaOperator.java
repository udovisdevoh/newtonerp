package modules.taskModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Opérateur de critère de recherche
 * @author Guillaume Lacasse
 */
public class SearchCriteriaOperator extends AbstractOrmEntity implements
	PromptViewable
{
    /**
     * @throws Exception si création fail
     */
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
	ListViewerData entityList = (ListViewerData) super.getList();
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
	ListViewerData entityList = (ListViewerData) super.getList();
	return entityList;
    }

    @Override
    public final ListViewerData getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	ListViewerData entityList = super.getList(parameters);
	return entityList;
    }
}
