package modules.taskModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Représente une définition d'entité pour une entité
 * @author Guillaume Lacasse
 */
public class EntityEntity extends AbstractOrmEntity implements PromptViewable
{
    /**
     * @throws Exception si création fail
     */
    public EntityEntity() throws Exception
    {
	super();
	setVisibleName("Entité");
	addNaturalKey(new ModuleEntity().getForeignKeyName());
	addNaturalKey("systemName");
	AccessorManager.addAccessor(this, new ModuleEntity());
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldString("Nom système", "systemName"));
	fieldList.add(new FieldInt("Module", new ModuleEntity()
		.getForeignKeyName()));
	return new Fields(fieldList);
    }

    @Override
    public AbstractEntity deleteUI(Hashtable<String, String> parameters)
	    throws Exception
    {
	/*
	 * On ne veut pas permettre l'effacement d'entité alors on redirige
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
	 * On ne veut pas permettre la modification d'entité alors on redirige
	 * vers GetList
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
