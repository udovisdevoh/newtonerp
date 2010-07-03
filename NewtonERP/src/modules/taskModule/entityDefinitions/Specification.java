package modules.taskModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.logging.Logger;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldText;

/**
 * Spécification d'une tâche
 * @author Guillaume Lacasse
 */
public class Specification extends AbstractOrmEntity
{
	/**
	 */
	public Specification()
	{
		super();
		setVisibleName("Spécification");
		AccessorManager.addAccessor(this, new SearchEntity());
	}

	@Override
	public Fields initFields()
	{
		Vector<Field<?>> fieldList = new Vector<Field<?>>();

		fieldList.add(new FieldInt("Numéro", getPrimaryKeyName()));
		fieldList.add(new FieldText("Description", "name", false));
		fieldList.add(new FieldInt("Entité de recherche", new SearchEntity()
				.getForeignKeyName()));
		return new Fields(fieldList);
	}

	/**
	 * @param entityParameters paramètres de l'entité
	 * @param isStraightSearch si c'est une recherche directe
	 * @return true si la spécification est satisfaite @ si test fail
	 */
	public boolean isSatisfied(Hashtable<String, String> entityParameters,
			boolean isStraightSearch)
	{
		Logger
				.info("[TASKMODULE] Vérification à savoir si une spécification est satisfaite");

		AbstractOrmEntity searchEntity = getSearchEntity();

		if (isStraightSearch)
		{
			if (!entityParameters.containsKey(searchEntity.getPrimaryKeyName()))
				return false;
			else if (entityParameters.get(searchEntity.getPrimaryKeyName())
					.equals("&"))
				return false;

			searchEntity.setData(searchEntity.getPrimaryKeyName(),
					entityParameters.get(searchEntity.getPrimaryKeyName()));
		}

		Vector<AbstractOrmEntity> result = Orm.select(searchEntity);

		if (result.size() > 0)
			return true;
		return false;
	}

	private AbstractOrmEntity getSearchEntity()
	{
		SearchEntity searchEntity = (SearchEntity) getSingleAccessor(new SearchEntity()
				.getForeignKeyName());

		return searchEntity.getEntity();
	}
}
