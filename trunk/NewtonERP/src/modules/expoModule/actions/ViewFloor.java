package modules.expoModule.actions;

import java.util.Hashtable;

import modules.expoModule.entityDefinitions.Floor;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.viewers.viewerData.FloorViewerData;

/**
 * Sert à voir le plancher
 * @author Guillaume Lacasse
 */
public class ViewFloor extends AbstractAction
{
    /**
     * @throws Exception si ça fail
     */
    public ViewFloor() throws Exception
    {
	super(new Floor());
	setDetailedDescription("Afficher le plancher d'exposition");
    }

    @Override
    public FloorViewerData doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Floor floor = (Floor) entity;
	FloorViewerData floorViewerData = new FloorViewerData(floor);
	return floorViewerData;
    }
}
