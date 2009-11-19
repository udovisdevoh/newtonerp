package modules.taskModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.taskModule.actions.ViewModuleSource;
import newtonERP.common.ActionLink;
import newtonERP.common.ListModule;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.Module;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Entité représentant un module
 * @author Guillaume Lacasse
 */
public class ModuleEntity extends AbstractOrmEntity
{
    /**
     * @throws Exception si création fail
     */
    public ModuleEntity() throws Exception
    {
	super();
	setVisibleName("Module");
    }

    @Override
    public Fields initFields() throws Exception
    {
	FieldString visibleName = new FieldString("Nom visible", "visibleName");
	visibleName.setNaturalKey(true);

	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldString("Nom système", "systemName"));
	fieldList.add(visibleName);
	return new Fields(fieldList);
    }

    /**
     * @return vrai module
     * @throws Exception si obtention fail
     */
    public Module getModule() throws Exception
    {
	return ListModule.getModule(getModuleName());
    }

    private String getModuleName()
    {
	return getDataString("systemName");
    }

    @Override
    public ListViewerData getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	parameters.put(getPrimaryKeyName(), "&");

	ListViewerData entityList = super.getList(parameters);
	entityList.addSpecificActionButtonList(new ActionLink(
		"Générer code source", new ViewModuleSource(), parameters));
	return entityList;
    }
}
