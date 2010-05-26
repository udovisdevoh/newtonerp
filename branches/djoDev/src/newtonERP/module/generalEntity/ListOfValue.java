package newtonERP.module.generalEntity;

import java.util.Vector;

import newtonERP.common.NaturalMap;
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
    private AbstractOrmEntity sourceEntity;
    private AbstractOrmEntity foreignEntity;

    /**
     * @param sourceEntity source entity
     * @param labelName the label name
     * @param foreignPrimaryKey the foreign primary key naturelle de l'entité
     *            étrangere
     * @param foreignEntity the foreign entity
     */
    public ListOfValue(AbstractOrmEntity sourceEntity, String labelName,
	    AbstractOrmEntity foreignEntity)
    {
	this.sourceEntity = sourceEntity;
	this.labelName = labelName;
	this.foreignEntity = foreignEntity;
    }

    public NaturalMap<String, String> getElements() throws Exception
    {
	Vector<AbstractOrmEntity> entityList = Orm.select(foreignEntity, null);

	NaturalMap<String, String> elementList = new NaturalMap<String, String>();

	for (AbstractOrmEntity entity : entityList)
	{
	    elementList.put(entity.getPrimaryKeyValue() + "", entity
		    .getNaturalKeyDescription());
	}

	return elementList;
    }

    public String getLabelName()
    {
	return labelName;
    }

    /**
     * @param currentForeignPrimaryKey the primary key
     * @return the foreign value of the primary key
     * @throws OrmException an exception that can occur in the orm
     */
    public String getForeignValue(String currentForeignPrimaryKey)
	    throws OrmException
    {
	try
	{
	    Vector<String> criterias = new Vector<String>();
	    criterias.add(foreignEntity.getPrimaryKeyName() + "="
		    + currentForeignPrimaryKey);
	    Vector<AbstractOrmEntity> entityList = Orm.select(foreignEntity,
		    criterias);
	    AbstractOrmEntity resultEntity = entityList.get(0);

	    return resultEntity.getNaturalKeyDescription();
	} catch (Exception e)
	{
	    return "- " + foreignEntity.getVisibleName() + " invalide - ";
	}
    }

    /**
     * @return définition pour entité étrangère
     */
    public AbstractOrmEntity getForeignEntityDefinition()
    {
	return foreignEntity;
    }

    /**
     * @return entité qui a une listOfValue
     */
    public AbstractOrmEntity getSourceEntityDefinition()
    {
	return sourceEntity;
    }

    /**
     * @param other autre list of value
     * @return si les list of value sont pareilles
     */
    public boolean equals(ListOfValue other)
    {
	if (getForeignEntityDefinition().getSystemName().equals(
		other.getForeignEntityDefinition().getSystemName()))
	    if (getSourceEntityDefinition().getSystemName().equals(
		    other.getSourceEntityDefinition().getSystemName()))
		if (getLabelName().equals(other.getLabelName()))
		    return true;
	return false;
    }
}
