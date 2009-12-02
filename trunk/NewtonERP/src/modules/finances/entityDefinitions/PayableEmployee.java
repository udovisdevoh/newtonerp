package modules.finances.entityDefinitions;

import java.util.Hashtable;
import java.util.Vector;

import modules.finances.actions.CalculateTaxAndSalary;
import modules.finances.actions.PayingEmployee;
import modules.humanResources.entityDefinitions.Employee;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldCurrency;
import newtonERP.orm.field.type.FieldDate;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.viewers.viewerData.ListViewerData;

/**
 * Entité PayableEmployee du module finances: représente les employes à payer
 * pour la période de paye courante et les montants payables à ceux-ci.
 * 
 * Sert également de donnée pour générer talon de paye...à venir
 * 
 * 
 * @author Pascal Lemay
 */
public class PayableEmployee extends AbstractOrmEntity
{
    /**
     * @throws Exception if creation fails
     */
    public PayableEmployee() throws Exception
    {
	super();
	AccessorManager.addAccessor(this, new Employee());
	AccessorManager.addAccessor(this, new StateType());
	AccessorManager.addAccessor(this, new BankAccount());
	setVisibleName("Employés et paies");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
	FieldInt primaryKey = new FieldInt("Numéro", getPrimaryKeyName());
	primaryKey.setNaturalKey(true);
	fieldsInit.add(primaryKey);

	fieldsInit.add(new FieldInt("Employee", new Employee()
		.getForeignKeyName()));
	// periode de paye
	fieldsInit.add(new FieldDate("Début de période", "beginning"));
	fieldsInit.add(new FieldDate("Fin de période", "end"));

	fieldsInit.add(new FieldDate("Date de paiement", "paymentDate"));
	fieldsInit.add(new FieldCurrency("Impôt Fedéral.", "fedTax"));
	fieldsInit.add(new FieldCurrency("Impôt Provincial.", "provTax"));
	fieldsInit.add(new FieldCurrency("Gains", "gains"));
	fieldsInit.add(new FieldCurrency("Paie nette", "balance"));
	fieldsInit
		.add(new FieldInt("État", new StateType().getForeignKeyName()));
	fieldsInit.add(new FieldInt("Compte pour paiement", new BankAccount()
		.getForeignKeyName()));

	return new Fields(fieldsInit);
    }

    // tempo, à voir si ces boutons resteront là
    @Override
    public final ListViewerData getList(Hashtable<String, String> parameters)
	    throws Exception
    {
	PayingEmployee paying = new PayingEmployee();
	paying.setOwnedByModul(getCurrentModule());

	CalculateTaxAndSalary calcul = new CalculateTaxAndSalary();//
	calcul.setOwnedByModul(getCurrentModule());//

	Hashtable<String, String> actionParameters = new Hashtable<String, String>();
	actionParameters.put(getPrimaryKeyName(), "&");

	ListViewerData list = super.getList(parameters);

	list.addSpecificActionButtonList(new ActionLink("Payer", paying,
		actionParameters));
	list.addSpecificActionButtonList(new ActionLink("Impôts", calcul,
		actionParameters));

	return list;
    }
    // ------------------------------------------------------------------

}
