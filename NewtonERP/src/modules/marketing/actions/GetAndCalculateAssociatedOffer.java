package modules.marketing.actions;

import java.util.Hashtable;

import modules.marketing.entityDefinitions.LineOffer;
import modules.marketing.entityDefinitions.Offer;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;

/**
 * trouve les lignes unused
 * @author Gabriel Therrien
 * 
 */
public class GetAndCalculateAssociatedOffer extends AbstractAction
{

    /**
     * @throws Exception a general exception
     */
    public GetAndCalculateAssociatedOffer() throws Exception
    {
	super(new LineOffer());
    }

    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Offer offre = (Offer) entity;
	LineOffer lineOffer = (LineOffer) entity;
	offre.setData(offre.getPrimaryKeyName(), lineOffer.getData(new Offer()
		.getForeignKeyName()));
	Offer returnedOffer = (Offer) Orm.selectUnique(offre);

	return returnedOffer;
    }
}