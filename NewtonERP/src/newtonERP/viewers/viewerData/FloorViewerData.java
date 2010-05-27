package newtonERP.viewers.viewerData;

import java.util.HashSet;
import java.util.Vector;

import modules.expoModule.entityDefinitions.Floor;
import modules.expoModule.entityDefinitions.WallType;
import modules.expoModule.entityDefinitions.Zone;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.viewers.viewables.FloorViewable;

/**
 * Information pour visualiser un plancher
 * @author Guillaume Lacasse
 */
public class FloorViewerData extends AbstractEntity implements FloorViewable
{
    private int columnCount = 0;

    private int rowCount = 0;

    private Boolean[][] corridorMask;

    private String[][] zoneNameMap;

    private Zone[][] zoneMap = null;

    private Floor sourceFloor = null;

    private HashSet<String> wallHash;

    private static Vector<String> lazyWallTypeNameList = null;

    /**
     * @throws Exception si ça fail
     */
    public FloorViewerData() throws Exception
    {
	super();
	wallHash = new HashSet<String>();
    }

    /**
     * @param sourceFloor source floor
     * @throws Exception si ça fail
     */
    public FloorViewerData(Floor sourceFloor) throws Exception
    {
	super();
	this.sourceFloor = sourceFloor;
	wallHash = new HashSet<String>();

	sourceFloor = (Floor) sourceFloor.get().get(0);

	columnCount = sourceFloor.getColumnCount();
	rowCount = sourceFloor.getRowCount();
	corridorMask = new Boolean[columnCount][rowCount];
	zoneNameMap = new String[columnCount][rowCount];
	zoneMap = new Zone[columnCount][rowCount];

	for (int x = 0; x < columnCount; x++)
	{
	    for (int y = 0; y < rowCount; y++)
	    {
		corridorMask[x][y] = sourceFloor.isCorridorAt(x, y);

		Zone zone = sourceFloor.getZoneAt(x, y);

		if (zone == null)
		    zoneNameMap[x][y] = "-";
		else
		    zoneNameMap[x][y] = zone.getKioskName();

		zoneMap[x][y] = zone;

		if (zone == null)
		    continue;

		for (String wallTypeName : getWallTypeNameList())
		    if (zone.isMuretAt(wallTypeName, x, y))
			wallHash.add(wallTypeName + "-" + x + "-" + y);
	    }
	}
    }

    private Vector<String> getWallTypeNameList() throws Exception
    {
	if (lazyWallTypeNameList == null)
	{
	    lazyWallTypeNameList = new Vector<String>();

	    WallType wallType = new WallType();

	    Vector<AbstractOrmEntity> entityList = wallType.get();

	    for (AbstractOrmEntity entity : entityList)
		lazyWallTypeNameList.add((entity.getDataString("Name")));
	}
	return lazyWallTypeNameList;
    }

    @Override
    public int getColumnCount()
    {
	return columnCount;
    }

    @Override
    public int getRowCount()
    {
	return rowCount;
    }

    @Override
    public boolean isCorridorAt(int x, int y)
    {
	if (corridorMask == null)
	    return false;

	return corridorMask[x][y];
    }

    @Override
    public String tryGetZoneNameAt(int x, int y) throws Exception
    {
	if (zoneNameMap == null)
	    return "-";
	return zoneNameMap[x][y];
    }

    @Override
    public Vector<ActionLink> getActionLinkListAt(int x, int y)
	    throws Exception
    {
	if (sourceFloor == null)
	    return new Vector<ActionLink>();

	return sourceFloor.getActionLinkListAt(x, y);
    }

    @Override
    public boolean isWallAt(String wallName, int x, int y)
    {
	return wallHash.contains(wallName + "-" + x + "-" + y);
    }
}
