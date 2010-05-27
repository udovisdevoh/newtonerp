package modules.expoModule.actions;

import java.util.Hashtable;

import modules.expoModule.entityDefinitions.KioskCustomer;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.viewers.viewerData.BandDiagramViewerData;

/**
 * 
 * @author Guillaume Lacasse
 */
public class ViewDiagram extends AbstractAction
{
    /**
     * @throws Exception si ça fail
     */
    public ViewDiagram() throws Exception
    {
	super(new KioskCustomer());
	setDetailedDescription("Voir les graphiques à barre additives d'un");
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	KioskCustomer kioskCustomer = (KioskCustomer) entity;
	kioskCustomer = (KioskCustomer) kioskCustomer.get().get(0);

	Hashtable<String, Double> itemPricePairList = kioskCustomer
		.getItemPricePairList();

	BandDiagramViewerData diagram = new BandDiagramViewerData(
		itemPricePairList);

	return diagram;
    }
}
