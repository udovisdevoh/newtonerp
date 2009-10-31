package newtonERP.orm.associations;

import java.util.Hashtable;

import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.ListOfValue;

public class ListOfValueManager
{
    /**
     * @param labelName the label of the lov
     * @param fieldKeyName the field name
     * @param foreignDescriptionKey the foreign fescrption key
     * @param foreignEntity the foreign entity
     */
    public static final void addListOfValue(AbstractEntity entity,
	    String labelName, String fieldKeyName,
	    String foreignDescriptionKey, AbstractOrmEntity foreignEntity)
    {
	Hashtable<String, ListOfValue> listOfValueList = entity
		.getListOfValueList();

	if (listOfValueList == null)
	    listOfValueList = new Hashtable<String, ListOfValue>();

	ListOfValue listOfValue = new ListOfValue(labelName, foreignEntity
		.getPrimaryKeyName(), foreignDescriptionKey, foreignEntity);

	listOfValueList.put(fieldKeyName, listOfValue);

	entity.setListOfValueList(listOfValueList);
    }
}
