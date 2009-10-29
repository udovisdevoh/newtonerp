package newtonERP.module.generalEntity;

import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.viewers.viewables.SelectBoxViewable;

/**
 * @author Guillaume Lacasse
 * 
 *         List of value for the viewers
 */
public class ListOfValue implements SelectBoxViewable
{
    private String labelName;
    private String foreignPrimaryKey;
    private String foreignDescription;
    private AbstractOrmEntity foreignEntity;

    /**
     * @param labelName the label name
     * @param foreignPrimaryKey the foreign primary key
     * @param foreignDescription the foreign description
     * @param foreignEntity the foreign entity
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
	    elementList.put(entity.getPrimaryKeyValue() + "", entity
		    .getDataString(foreignDescription));

	return elementList;
    }

    public String getLabelName()
    {
	return labelName;
    }

    /**
     * @param foreignPrimaryKey the primary key
     * @return the foreign value of the primary key
     * @throws OrmException an exception that can occur in the orm
     */
    public String getForeignValue(String foreignPrimaryKey) throws OrmException
    {
	try
	{
	    Vector<String> criterias = new Vector<String>();
	    criterias.add(foreignEntity.getPrimaryKeyName() + "="
		    + foreignPrimaryKey);
	    Vector<AbstractOrmEntity> entityList = Orm.select(foreignEntity,
		    criterias);
	    AbstractOrmEntity resultEntity = entityList.get(0);

	    return resultEntity.getDataString(foreignDescription);
	} catch (Exception e)
	{
	    return "- " + foreignEntity.getClass().getSimpleName()
		    + " invalide - ";
	}
    }
}
