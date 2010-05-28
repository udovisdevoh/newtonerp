package modules.expoModule.entityDefinitions;

import java.util.Hashtable;
import java.util.TreeMap;
import java.util.Vector;

import modules.expoModule.actions.BuyZone;
import modules.expoModule.actions.ViewFloor;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Plancher
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class Floor extends AbstractOrmEntity
{
    private PluralAccessor lazyCorridorList = null;

    private Hashtable<String, String> zoneIdParameters = new Hashtable<String, String>();

    /**
     * constructor
     * @throws Exception remonte
     */
    public Floor() throws Exception
    {
	super();
	setVisibleName("Plancher");
	setDetailedDescription("plancher d'exposition");
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKfloorID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKfloorID);

	FieldString nom = new FieldString("Description", "Nom");
	fieldList.add(nom);

	FieldInt width = new FieldInt("Largeur", "Width");
	fieldList.add(width);

	FieldInt height = new FieldInt("Hauteur", "Height");
	fieldList.add(height);

	return new Fields(fieldList);
    }

    /**
     * @param x x position
     * @param y y position
     * @return s'il y a un corridor
     * @throws Exception si ça fail
     */
    public boolean isCorridorAt(int x, int y) throws Exception
    {
	for (AbstractOrmEntity entity : getLazyCorridorList())
	{
	    Corridor corridor = (Corridor) entity;

	    boolean isVertical = (Boolean) (corridor.getFields().getField(
		    "IsVertical").getData());
	    int position = (Integer) (corridor.getFields().getField("Position")
		    .getData());

	    if (isVertical && position == x)
		return true;
	    else if (!isVertical && position == y)
		return true;
	}
	return false;
    }

    private PluralAccessor getLazyCorridorList() throws Exception
    {
	if (lazyCorridorList == null)
	{
	    TreeMap<String, PluralAccessor> accessorList = AccessorManager
		    .getPluralAccessorList(this);
	    lazyCorridorList = accessorList.get("Corridor");
	}
	return lazyCorridorList;
    }

    /**
     * @return combien de ranger
     */
    public int getRowCount()
    {
	return (Integer) getData("Height");
    }

    /**
     * @return combien de colonnes
     */
    public int getColumnCount()
    {
	return (Integer) getData("Width");
    }

    /**
     * @param x position x
     * @param y position y
     * @return zone aux positions spécifiées ou null si rien trouvé
     * @throws Exception si ça fail
     */
    public Zone getZoneAt(int x, int y) throws Exception
    {
	Zone zone = new Zone();
	zone.setData("PositionX", x);
	zone.setData("PositionY", y);
	zone.setData(getForeignKeyName(), getPrimaryKeyValue());

	Vector<AbstractOrmEntity> zoneList = zone.get();
	if (zoneList.size() < 1)
	    return null;

	for (AbstractOrmEntity currentZone : zoneList)
	    if ((Boolean) currentZone.getData("isActive"))
		return (Zone) currentZone;

	return null;
    }

    @Override
    public ListViewerData getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	parameters.put(getPrimaryKeyName(), "&");

	ListViewerData entityList = super.getList(parameters);
	entityList.addSpecificActionButtonList(new ActionLink("Voir plancher",
		new ViewFloor(), parameters));

	return entityList;
    }

    /**
     * @param x position x
     * @param y position y
     * @return liste des actionLink à la position spécifiée
     * @throws Exception si ça fail
     */
    public Vector<ActionLink> getActionLinkListAt(int x, int y)
	    throws Exception
    {
	Zone zone = new Zone();
	zone.setData("PositionX", x);
	zone.setData("PositionY", y);
	zone.setData(getForeignKeyName(), getPrimaryKeyValue());

	Vector<AbstractOrmEntity> zoneList = zone.get();
	if (zoneList.size() < 1)
	{
	    Hashtable<String, String> parameters = new Hashtable<String, String>();
	    parameters.put("PositionX", Integer.toString(x));
	    parameters.put("PositionY", Integer.toString(y));
	    parameters
		    .put(getPrimaryKeyName(), getPrimaryKeyValue().toString());

	    Vector<ActionLink> actionLinkList = new Vector<ActionLink>();
	    actionLinkList.add(new ActionLink("Acheter", new BuyZone(),
		    parameters));

	    return actionLinkList;
	}

	zone = (Zone) zoneList.get(0);

	zoneIdParameters.put(zone.getPrimaryKeyName(), zone
		.getPrimaryKeyValue().toString());

	Vector<ActionLink> actionLinkList = new Vector<ActionLink>();
	actionLinkList.add(new ActionLink("Info", new BaseAction("Edit", zone),
		zoneIdParameters));

	return actionLinkList;
    }
}
