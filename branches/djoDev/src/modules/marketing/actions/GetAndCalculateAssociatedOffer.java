package modules.marketing.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.marketing.entityDefinitions.LineOffer;
import modules.marketing.entityDefinitions.Offer;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
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
	super(new Offer());
    }

    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	double total = 0;
	Offer offre = (Offer) entity;
	LineOffer rechercheur = new LineOffer();
	rechercheur.setData(new Offer().getForeignKeyName(), offre
		.getPrimaryKeyValue());
	Vector<AbstractOrmEntity> lignesDoffre = Orm.select(rechercheur);
	for (int i = 0; i < lignesDoffre.size(); i++)
	{
	    total += (Double) ((LineOffer) lignesDoffre.elementAt(i))
		    .getData("Price");
	}
	offre.setData("Price", total);
	offre.save();
	return null;
    }
}