package newtonERP.viewers.viewerData;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.generalEntity.ListOfValue;
import newtonERP.orm.associations.GateWay;
import newtonERP.orm.associations.GateWayManager;
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
    private SearchBar searchBar = null;

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

	GridCaseData gridCaseData;

	for (Field<?> field : dataType.getFields())
	{
	    ListOfValue listOfValue = dataType.tryMatchListOfValue(field
		    .getShortName());

	    if (listOfValue != null)
	    {
		gridCaseData = new GridCaseData(listOfValue.getLabelName(),
			new BaseAction("GetList", listOfValue
				.getForeignEntityDefinition()));
		header.add(gridCaseData);
	    }
	    else if (!field.isHidden())
	    {
		gridCaseData = new GridCaseData(field.getName());
		header.add(gridCaseData);
	    }
	}

	for (GateWay gateWay : dataType.getGateWayList())
	{
	    gridCaseData = new GridCaseData(gateWay.getExternalEntity()
		    .getNaturalKeyName());
	    header.add(gridCaseData);
	}

	return header.toArray(new GridCaseData[0]);
    }

    public GridCaseData[][] getCases() throws Exception
    {
	Vector<GridCaseData[]> dataList = new Vector<GridCaseData[]>();
	String value;
	GridCaseData gridCaseData;

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

		    gridCaseData = new GridCaseData(value, new BaseAction(
			    "Edit", listOfValue.getForeignEntityDefinition()),
			    param);
		    gridCaseData.setColored(field.isColored());
		    oneData.add(gridCaseData);
		}
		else if (!field.isHidden())
		{
		    value = field.getDataString();
		    if (value == null)
			value = "";
		    if (value.length() > 64)
			value = field.getDataString().substring(0, 64)
				+ "[...]";
		    gridCaseData = new GridCaseData(value);
		    gridCaseData.setColored(field.isColored());
		    oneData.add(gridCaseData);
		}
		else
		{
		    gridCaseData = new GridCaseData(value);
		    gridCaseData.setColored(field.isColored());
		    oneData.add(gridCaseData);
		}
	    }

	    for (GateWay gateWay : entity.getGateWayList())
	    {
		AbstractOrmEntity foreignEntity = GateWayManager
			.getExternalEntity(gateWay, entity);
		String naturalKeyValue = foreignEntity
			.getNaturalKeyDescription();

		Hashtable<String, String> param = new Hashtable<String, String>();
		param.put(foreignEntity.getPrimaryKeyName(), foreignEntity
			.getPrimaryKeyValue().toString());

		gridCaseData = new GridCaseData(naturalKeyValue,
			new BaseAction("Edit", foreignEntity), param);
		gridCaseData.setColored(gateWay.isColored());
		oneData.add(gridCaseData);
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
     * @param limit limite par page
     * @return offset de selection de liste d'entité, -1 si aucun
     */
    public final static int BuildOffset(Hashtable<String, String> parameters,
	    int limit)
    {
	int offset = 0;
	if (parameters.containsKey("offset"))
	{
	    offset = Integer.parseInt(parameters.get("offset"));
	    if (offset % limit != 0)
		offset = offset / limit * limit;
	}
	return offset;
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
     * @return page selector
     */
    public PageSelector getPageSelector()
    {
	return pageSelector;
    }

    /**
     * @param pageSelector page selector
     */
    public void setPageSelector(PageSelector pageSelector)
    {
	this.pageSelector = pageSelector;
    }

    /**
     * @return modèle de barre de recherche
     */
    public SearchBar getSearchBar()
    {
	return searchBar;
    }

    /**
     * @param searchBar modèle de barre de recherche
     */
    public void setSearchBar(SearchBar searchBar)
    {
	this.searchBar = searchBar;
    }
}
