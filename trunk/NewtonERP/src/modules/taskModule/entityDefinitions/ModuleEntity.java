package modules.taskModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.taskModule.actions.GenerateSourceCode;
import modules.taskModule.actions.ViewModuleSource;
import newtonERP.common.ActionLink;
import newtonERP.common.ListModule;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.Module;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Entité représentant un module
 * @author Guillaume Lacasse
 */
public class ModuleEntity extends AbstractOrmEntity
{
	/**
	 */
	public ModuleEntity()
	{
		super();
		setVisibleName("Module");
	}

	protected Fields preInitFields()
	{
		// always build the field from initField and not from DB, thats mean
		// that we cannot add a dynamic Field, this should not be done anywhere
		// else
		return initFields();
	}

	@Override
	public Fields initFields()
	{
		FieldString visibleName = new FieldString("Nom visible", "visibleName");
		visibleName.setNaturalKey(true);

		Vector<Field<?>> fieldList = new Vector<Field<?>>();
		fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
		fieldList.add(new FieldString("Nom système", "systemName"));
		fieldList.add(visibleName);
		return new Fields(fieldList);
	}

	/**
	 * @return vrai module
	 */
	public Module getModule()
	{
		return ListModule.getModule(getModuleName());
	}

	private String getModuleName()
	{
		return getDataString("systemName");
	}

	@Override
	public ListViewerData getList(Hashtable<String, String> parameters)

	{
		parameters.put(getPrimaryKeyName(), "&");

		ListViewerData entityList = super.getList(parameters);
		entityList.addSpecificActionButtonList(new ActionLink("Voir source",
				new ViewModuleSource(), parameters));

		entityList.addSpecificActionButtonList(new ActionLink("Générer source",
				new GenerateSourceCode(), parameters));

		return entityList;
	}

	/**
	 * @return list of entity entity for this module
	 */
	public Vector<EntityEntity> getEntityEntityList()
	{
		PluralAccessor accessor = getPluralAccessor(new EntityEntity()
				.getSystemName());
		Vector<EntityEntity> entityEntityList = new Vector<EntityEntity>();
		for (AbstractOrmEntity entity : accessor)
			entityEntityList.add((EntityEntity) entity);
		return entityEntityList;
	}

	/**
	 * @return list of action entity for this module
	 */
	public Vector<ActionEntity> getActionEntityList()
	{
		PluralAccessor accessor = getPluralAccessor(new ActionEntity()
				.getSystemName());
		Vector<ActionEntity> actionEntityList = new Vector<ActionEntity>();
		for (AbstractOrmEntity entity : accessor)
			actionEntityList.add((ActionEntity) entity);
		return actionEntityList;
	}
}
