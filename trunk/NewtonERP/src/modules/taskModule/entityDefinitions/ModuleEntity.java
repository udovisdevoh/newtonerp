package modules.taskModule.entityDefinitions;

import java.util.Vector;

import newtonERP.common.ListModule;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.Module;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;

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
	Vector<Field> fieldList = new Vector<Field>();
	fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldList.add(new FieldString("Nom système", "systemName"));
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
}
