package newtonERP.orm.associations;

import java.util.Hashtable;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.exception.TableAssociationException;
import newtonERP.module.generalEntity.ListOfValue;

public class ListOfValueManager
{
    /**
     * @param entity Entité source
     * @param foreignEntity Entité cible
     * @throws Exception
     */
    public static final void addListOfValue(AbstractEntity entity,
	    AbstractOrmEntity foreignEntity) throws Exception
    {
	String foreignKeyName = foreignEntity.getForeignKeyName();

	if (entity.getFields().getField(foreignKeyName) == null)
	    throw new TableAssociationException(foreignKeyName
		    + " introuvable dans " + entity.getClass().getSimpleName());

	Hashtable<String, ListOfValue> listOfValueList = entity
		.getListOfValueList();

	if (listOfValueList == null)
	    listOfValueList = new Hashtable<String, ListOfValue>();

	ListOfValue listOfValue = new ListOfValue(foreignEntity
		.getNaturalKeyName(), foreignEntity.getPrimaryKeyName(),
		foreignEntity.getNaturalKeyNameList(), foreignEntity);

	listOfValueList.put(foreignKeyName, listOfValue);

	entity.setListOfValueList(listOfValueList);
    }
}
