package modules.expoModule.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.expoModule.actions.ViewDiagram;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldDateTime;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Client de kiosque
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class KioskCustomer extends AbstractOrmEntity
{
    /**
     * constructor
     * @throws Exception remonte
     */
    public KioskCustomer() throws Exception
    {
	super();
	setVisibleName("Kiosque");
	setDetailedDescription("client de kiosque");
	AccessorManager.addAccessor(this, new CompanyDomain());
	AccessorManager.addAccessor(this, new User());
	AccessorManager.addAccessor(this, new InternetConnectionType());
    }

    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldList = new Vector<Field<?>>();

	FieldInt pKkioskCustomerID = new FieldInt("Numéro", getPrimaryKeyName());
	fieldList.add(pKkioskCustomerID);

	FieldString company = new FieldString("Compagnie", "Company");
	company.setNaturalKey(true);
	fieldList.add(company);

	FieldInt domain = new FieldInt("Domaine", "companyDomainID");
	fieldList.add(domain);

	FieldString lastName = new FieldString("Nom du responsable", "LastName");
	fieldList.add(lastName);

	FieldString firstName = new FieldString("Prénom du responsable",
		"FirstName");
	fieldList.add(firstName);

	FieldString email = new FieldString("Courriel", "Email");
	fieldList.add(email);

	FieldInt user = new FieldInt("Utilisateur", "userID");
	user.setReadOnly(true);
	fieldList.add(user);

	FieldInt internetConnectionType = new FieldInt("Connexion internet",
		new InternetConnectionType().getForeignKeyName());
	fieldList.add(internetConnectionType);

	FieldDateTime date = new FieldDateTime("Date d`inscription", "Date");
	date.setReadOnly(true);
	fieldList.add(date);
	return new Fields(fieldList);
    }

    @Override
    public ListViewerData getList(Hashtable<String, String> parameters)
	    throws Exception
    {

	parameters.put(getPrimaryKeyName(), "&");

	ListViewerData entityList = super.getList(parameters);
	entityList.addSpecificActionButtonList(new ActionLink(
		"Voir graphiques", new ViewDiagram(), parameters));

	return entityList;
    }

    /**
     * @return liste des clef/valeur d'achat d'options
     * @throws Exception si ça fail
     */
    public Hashtable<String, Double> getItemPricePairList() throws Exception
    {
	Hashtable<String, Double> itemPricePairList = new Hashtable<String, Double>();

	PluralAccessor kioskInvoiceList = this
		.getPluralAccessor("KioskInvoice");

	for (AbstractOrmEntity kioskInvoice : kioskInvoiceList)
	{
	    PluralAccessor kioskInvoiceItemList = kioskInvoice
		    .getPluralAccessor("KioskInvoiceItem");

	    for (AbstractOrmEntity kioskInvoiceItemOrmEntity : kioskInvoiceItemList)
	    {
		KioskInvoiceItem invoiceItem = (KioskInvoiceItem) kioskInvoiceItemOrmEntity;

		Option option = (Option) invoiceItem
			.getSingleAccessor("optionID");

		if (option != null)
		{
		    String itemName = option.getDataString("name");

		    if (!itemPricePairList.containsKey(itemName))
			itemPricePairList.put(itemName, 0.0);

		    itemPricePairList.put(itemName, itemPricePairList
			    .get(itemName)
			    + invoiceItem.getTotal());

		}
	    }
	}

	return itemPricePairList;
    }
}
