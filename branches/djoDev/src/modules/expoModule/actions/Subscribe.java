package modules.expoModule.actions;

import java.util.GregorianCalendar;
import java.util.Hashtable;

import modules.expoModule.entityDefinitions.CompanyDomain;
import modules.expoModule.entityDefinitions.InternetConnectionType;
import modules.expoModule.entityDefinitions.KioskCustomer;
import modules.expoModule.entityDefinitions.KioskInvoice;
import modules.expoModule.entityDefinitions.KioskInvoiceItem;
import modules.expoModule.entityDefinitions.Option;
import modules.userRightModule.UserRightModule;
import modules.userRightModule.entityDefinitions.Groups;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.common.ActionLink;
import newtonERP.common.Authentication;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.Form;
import newtonERP.module.generalEntity.StaticTextEntity;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.type.FieldBool;
import newtonERP.orm.field.type.FieldString;

/**
 * Subscribe
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class Subscribe extends AbstractAction
{
    /**
     * Créer instance d'action
     */
    public Subscribe()
    {
	setDetailedDescription("Vous abonner à l'exposition");
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	String currentLoginName = parameters.get("name");
	String currentPassword = parameters.get("password");

	if (currentLoginName == null)
	    currentLoginName = "";

	if (currentPassword == null)
	    currentPassword = "";

	Form subscribeForm = new Form(new UserRightModule(), new Subscribe());

	subscribeForm.addField("Choisir un nom d'utilisateur", "name",
		currentLoginName);

	Field<?> passwordField = new FieldString("Choisir un mot de passe",
		"password");
	passwordField.setHidden(true);

	subscribeForm.addField(passwordField);
	subscribeForm.setTitle("Création d'un compte");
	subscribeForm.setButtonAction(new ActionLink("Créer", this));

	if (parameters.containsKey("submit"))
	{
	    User user = new User();
	    user.setData("name", currentLoginName);

	    if (user.get().size() == 0)
	    {
		Groups groups = new Groups();
		groups.setData("groupName", "expoGroup");
		groups = (Groups) groups.get().get(0);

		InternetConnectionType internetConnectionType = new InternetConnectionType();
		internetConnectionType.setData("Name", "aucune");
		internetConnectionType = (InternetConnectionType) internetConnectionType
			.get().get(0);

		user.setData("groupsID", groups.getPrimaryKeyValue());
		user.setData("password", currentPassword);
		user.newE();

		CompanyDomain companyDomain = new CompanyDomain();
		companyDomain.setData("Name", "autre");
		companyDomain = (CompanyDomain) companyDomain.get().get(0);

		KioskCustomer kioskCustomer = new KioskCustomer();
		kioskCustomer.assign(user);
		kioskCustomer.assign(companyDomain);
		kioskCustomer.assign(internetConnectionType);
		kioskCustomer.setData("Company", currentLoginName);
		kioskCustomer.getFields().getField("Date").setData(
			new GregorianCalendar());

		kioskCustomer.newE();
		Authentication.setCurrentUserName(currentLoginName);

		// On fait la transaction d'inscription
		KioskInvoice currentActiveTransaction = getCurrentActiveTransaction();
		currentActiveTransaction.setData("isPaid", true);
		currentActiveTransaction.save();

		Option option = new Option();
		option.setData("name", "Inscription");
		option = (Option) option.get().get(0);

		KioskInvoiceItem invoiceItem = new KioskInvoiceItem();
		invoiceItem.assign(option);
		invoiceItem.assign(currentActiveTransaction);
		invoiceItem.setData("amount", 1);
		invoiceItem.newE();

		return new StaticTextEntity("Votre compte a été créé.");
	    }

	    subscribeForm.addAlertMessage("Ce compte existe déjà");
	}

	return subscribeForm;
    }

    private KioskInvoice getCurrentActiveTransaction() throws Exception
    {
	User currentUser = Authentication.getCurrentUser();

	PluralAccessor customerList = currentUser
		.getPluralAccessor("KioskCustomer");

	KioskCustomer kioskCustomer = (KioskCustomer) customerList.get(0);

	PluralAccessor invoiceList = kioskCustomer
		.getPluralAccessor("KioskInvoice");

	KioskInvoice selectedInvoice = null;
	for (AbstractOrmEntity invoice : invoiceList)
	{
	    FieldBool isPaid = (FieldBool) invoice.getFields().getField(
		    "isPaid");
	    if (isPaid.getData() == null || isPaid.getData() != true)
	    {
		selectedInvoice = (KioskInvoice) invoice;
	    }
	}

	if (selectedInvoice == null)
	{
	    selectedInvoice = new KioskInvoice();
	    selectedInvoice.assign(kioskCustomer);
	    selectedInvoice.setData("isPaid", false);
	    selectedInvoice.newE();
	}

	return selectedInvoice;
    }
}
