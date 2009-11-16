package newtonERP.module.generalEntity;

import java.util.Iterator;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.orm.field.Field;
import newtonERP.viewers.viewerData.GridCaseData;
import newtonERP.viewers.viewerData.GridViewerData;

/**
 * @author Guillaume Lacasse, CloutierJo
 * 
 */
public class EntityList extends GridViewerData implements
	Iterable<AbstractOrmEntity>
{
    private AbstractOrmEntity dataType;
    private Vector<AbstractOrmEntity> data = new Vector<AbstractOrmEntity>();

    /**
     * constructeur par defaut
     * @param dataType type d'entity a etre liste
     * @throws Exception remonte
     */
    public EntityList(AbstractOrmEntity dataType) throws Exception
    {
	super();
	this.dataType = dataType;
    }

    /**
     * @return les entity
     */
    public Vector<AbstractOrmEntity> getEntity()
    {
	return data;
    }

    public GridCaseData[] getHeader() throws Exception
    {
	Vector<GridCaseData> header = new Vector<GridCaseData>();
	for (Field field : dataType.getFields())
	{
	    ListOfValue listOfValue = dataType.tryMatchListOfValue(field
		    .getShortName());

	    if (listOfValue != null)
		header.add(new GridCaseData(listOfValue.getLabelName(),
			new BaseAction("GetList", listOfValue
				.getForeignEntityDefinition())));
	    else
		header.add(new GridCaseData(field.getName()));
	}
	return header.toArray(new GridCaseData[0]);
    }

    public GridCaseData[][] getCases() throws Exception
    {
	Vector<GridCaseData[]> dataList = new Vector<GridCaseData[]>();
	String value;

	for (AbstractOrmEntity entity : data)
	{
	    Vector<GridCaseData> oneData = new Vector<GridCaseData>();
	    for (Field field : entity.getFields())
	    {
		value = "";
		if (field.isHidden())
		    continue;

		ListOfValue listOfValue = entity.tryMatchListOfValue(field
			.getShortName());

		if (listOfValue != null)
		    value = listOfValue.getForeignValue(field.getDataString());
		else
		{
		    value = field.getDataString();
		    if (value.length() > 64)
			value = field.getDataString().substring(0, 64)
				+ "[...]";
		}
		oneData.add(new GridCaseData(value));

	    }
	    dataList.add(oneData.toArray(new GridCaseData[0]));
	}
	return dataList.toArray(new GridCaseData[0][0]);

    }

    /**
     * @param entity the entity to add
     */
    public void addEntity(AbstractOrmEntity entity)
    {
	data.add(entity);
    }

    /**
     * @return the dataType
     */
    public AbstractOrmEntity getDataType()
    {
	return dataType;
    }

    @Override
    public Iterator<AbstractOrmEntity> iterator()
    {
	return data.iterator();
    }
}
