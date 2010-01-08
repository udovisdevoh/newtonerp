package newtonERP.viewers.viewerData;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.generalEntity.ListOfValue;
import newtonERP.orm.field.Field;

/**
 * @author Guillaume Lacasse, CloutierJo
 * 
 */
public class ListViewerData extends GridViewerData implements
	Iterable<AbstractOrmEntity>
{
    private AbstractOrmEntity dataType;
    private Vector<AbstractOrmEntity> data = new Vector<AbstractOrmEntity>();
    private PageSelector pageSelector = null;

    /**
     * constructeur par defaut
     * @param dataType type d'entity a etre liste
     * @throws Exception remonte
     */
    public ListViewerData(AbstractOrmEntity dataType) throws Exception
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
	for (Field<?> field : dataType.getFields())
	{
	    ListOfValue listOfValue = dataType.tryMatchListOfValue(field
		    .getShortName());

	    if (listOfValue != null)
		header.add(new GridCaseData(listOfValue.getLabelName(),
			new BaseAction("GetList", listOfValue
				.getForeignEntityDefinition())));
	    else if (!field.isHidden())
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
	    for (Field<?> field : entity.getFields())
	    {
		value = "";
		if (field.isHidden())
		    continue;

		ListOfValue listOfValue = entity.tryMatchListOfValue(field
			.getShortName());

		if (listOfValue != null)
		{
		    value = listOfValue.getForeignValue(field.getDataString());
		    Hashtable<String, String> param = new Hashtable<String, String>();
		    param.put(listOfValue.getForeignEntityDefinition()
			    .getPrimaryKeyName(), field.getDataString());

		    oneData.add(new GridCaseData(value, new BaseAction("Edit",
			    listOfValue.getForeignEntityDefinition()), param));
		}
		else if (!field.isHidden())
		{
		    value = field.getDataString();
		    if (value == null)
			value = "";
		    if (value.length() > 64)
			value = field.getDataString().substring(0, 64)
				+ "[...]";
		    oneData.add(new GridCaseData(value));
		}
		else
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

    /**
     * @param parameters parametres d'action GetList
     * @return offset de selection de liste d'entité, -1 si aucun
     */
    public final static int BuildOffset(Hashtable<String, String> parameters)
    {
	if (parameters.containsKey("offset"))
	    return Integer.parseInt(parameters.get("offset"));
	return 0;
    }

    /**
     * @param parameters parametres d'action GetList
     * @param defaultValue nombre d'item par page par default
     * @return limite de selection de liste d'entité, -1 si aucun
     */
    public final static int BuildLimit(Hashtable<String, String> parameters,
	    int defaultValue)
    {
	if (parameters.containsKey("limit"))
	    return Integer.parseInt(parameters.get("limit"));
	return defaultValue;
    }

    /**
     * @param pageSelector page selector
     * @throws Exception si ça fail
     */
    public void setPageSelector(PageSelector pageSelector) throws Exception
    {
	this.pageSelector = pageSelector;
    }

    /**
     * @return page selector
     */
    public PageSelector getPageSelector()
    {
	return pageSelector;
    }
}
