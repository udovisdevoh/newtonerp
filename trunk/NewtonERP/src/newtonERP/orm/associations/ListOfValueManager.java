package newtonERP.orm.associations;

import java.util.Hashtable;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.ListOfValue;

public class ListOfValueManager
{
    /**
     * @param entity Entité source
     * @param foreignEntity Entité cible
     */
    public static final void addListOfValue(AbstractEntity entity,
	    AbstractOrmEntity foreignEntity)
    {
	Hashtable<String, ListOfValue> listOfValueList = entity
		.getListOfValueList();

	if (listOfValueList == null)
	    listOfValueList = new Hashtable<String, ListOfValue>();

	ListOfValue listOfValue = new ListOfValue(foreignEntity
		.getNaturalKeyName(), foreignEntity.getPrimaryKeyName(),
		foreignEntity.getNaturalKeyNameList(), foreignEntity);

	listOfValueList.put(foreignEntity.getForeignKeyName(), listOfValue);

	entity.setListOfValueList(listOfValueList);
    }
}
