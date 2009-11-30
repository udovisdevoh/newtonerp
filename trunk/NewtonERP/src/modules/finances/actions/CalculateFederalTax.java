package modules.finances.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.finances.entityDefinitions.FederalWageBracket;
import modules.finances.entityDefinitions.PayableEmployee;
import modules.humanResources.entityDefinitions.Employee;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;

/**
 * Action CalculateFederalTax : représente l'action de calculer l'impôt fédéral
 * retenue en fonction du montant...
 * @author Pascal Lemay
 */
public class CalculateFederalTax extends AbstractAction
{
    /**
     * constructeur
     * @throws Exception si création fail
     */
    public CalculateFederalTax() throws Exception
    {
	super(new PayableEmployee());
    }

    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	PayableEmployee emp = (PayableEmployee) entity;
	AbstractOrmEntity employee = Orm.selectUnique(emp);
	// salaire annuel
	Double salary = (Double) employee.getData("gains");

	// type de salaire
	Integer type = (Integer) employee.getData(new Employee()
		.getForeignKeyName());

	if (type == 1)// si salaire horaire, convertion par extension en salaire
	    // annuel pour les calcules
	    salary *= 26;

	// salaire - min de la tranche actuelle
	Double basedAmount = 0.0;

	// impôt sur la montant de base :(pour impôt sur tranche(s)
	// inférieure(s) si salaire sur
	// plus d'une tranche
	Double taxResult = 0.0;

	// taxResult + (le montant d'adjustment : impôt sur le min)
	Double finalTaxResult = 0.0;

	Vector<AbstractOrmEntity> fedBrackets = Orm
		.select(new FederalWageBracket());

	for (int i = 0; i < fedBrackets.size(); i++)
	{
	    if (salary <= (Double) fedBrackets.get(i).getData("maxBracket"))
	    {

		basedAmount = salary
			- (Double) fedBrackets.get(i).getData("minBracket");

		taxResult = basedAmount
			* ((Double) fedBrackets.get(i).getData("tax") / 100.0);

		finalTaxResult = taxResult
			+ (Double) fedBrackets.get(i).getData("ajustment");

		finalTaxResult /= 26;

		employee.setData("fedTax", finalTaxResult);
		employee.save();

		return null; // sortie
	    }
	}

	return null;
    }

}
