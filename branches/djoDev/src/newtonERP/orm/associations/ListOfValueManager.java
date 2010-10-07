package newtonERP.orm.associations;

import newtonERP.module.exception.TableAssociationException;
import newtonERP.module.generalEntity.ListOfValue;

/**
 * List of value manager
 * 
 * @author Guillaume Lacasse
 */
public class ListOfValueManager
{
	/**
	 * @param entity Entité source
	 * @param foreignEntity Entité cible
	 */
	public final static void addListOfValue(
			newtonERP.module.AbstractOrmEntity entity,
			newtonERP.module.AbstractOrmEntity foreignEntity)
	{
		String foreignKeyName = foreignEntity.getForeignKeyName();

		if (entity.getFields().getField(foreignKeyName) == null)
			throw new TableAssociationException(foreignKeyName
					+ " introuvable dans " + entity.getSystemName());

		ListOfValue listOfValue = new ListOfValue(entity,
				foreignEntity.getNaturalKeyName(), foreignEntity);

		entity.addPositiveListOfValue(foreignKeyName, listOfValue);

		foreignEntity.addNegativeListOfValue(listOfValue);
	}

}
