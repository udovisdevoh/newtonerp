package newtonERP.module;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.viewers.viewables.SelectBoxViewable;

public class ListOfValue implements SelectBoxViewable
{
    private String labelName;
    private String foreignPrimaryKey;
    private String foreignDescription;
    private AbstractOrmEntity foreignEntity;

    /**
     * @param labelName
     * @param foreignPrimaryKey
     * @param foreignDescription
     * @param foreignEntity
     */
    public ListOfValue(String labelName, String foreignPrimaryKey,
	    String foreignDescription, AbstractOrmEntity foreignEntity)
    {
	this.labelName = labelName;
	this.foreignPrimaryKey = foreignPrimaryKey;
	this.foreignDescription = foreignDescription;
	this.foreignEntity = foreignEntity;
    }

    public Hashtable<String, String> getElements() throws OrmException
    {
	Vector<AbstractOrmEntity> entityList = Orm.select(foreignEntity, null);

	Hashtable<String, String> elementList = new Hashtable<String, String>();

	for (AbstractOrmEntity entity : entityList)
	    elementList.put(entity.getPrimaryKeyValue(), entity
		    .getDataString(foreignDescription));

	return elementList;
    }

    public String getLabelName()
    {
	return labelName;
    }

    public String getForeignValue(String foreignPrimaryKey) throws OrmException
    {
	Vector<String> criterias = new Vector<String>();
	criterias.add(foreignEntity.getPrimaryKeyName() + "="
		+ foreignPrimaryKey);
	Vector<AbstractOrmEntity> entityList = Orm.select(foreignEntity,
		criterias);
	AbstractOrmEntity resultEntity = entityList.get(0);

	return resultEntity.getDataString(foreignDescription);
    }
}
