package modules.marketing.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.marketing.actions.GetAndCalculateAssociatedOffer;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldCurrency;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Entité d'une promotion
 * @author Gabriel
 * 
 */
public class Offer extends AbstractOrmEntity
{
	/**
	 * @throws Exception si création fail
	 */
	public Offer() throws Exception
	{
		super();
		// Le nom visible de LineOffer était le même
		setVisibleName("Offre");
	}

	@Override
	public Fields initFields() throws Exception
	{

		Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
		fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
		fieldsInit.add(new FieldString("Nom De l'offre", "Offre"));
		// Gab: est-ce ce field que tu veux rendre read-only? car tu le met
		// readonly et ça marche
		FieldCurrency currencyField = new FieldCurrency("Prix", "Price");
		currencyField.setReadOnly(true);
		fieldsInit.add(currencyField);
		return new Fields(fieldsInit);
	}

	public final ListViewerData getList(Hashtable<String, String> parameters)
			throws Exception
	{
		Hashtable<String, String> actionParameters = new Hashtable<String, String>();
		actionParameters.put(getPrimaryKeyName(), "&");

		ListViewerData entityList = super.getList(parameters);
		entityList.addSpecificActionButtonList(new ActionLink(
				"Calculer les l'offre", new GetAndCalculateAssociatedOffer(),
				actionParameters));

		return entityList;
	}
}
