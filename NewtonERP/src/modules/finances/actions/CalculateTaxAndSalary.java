package modules.finances.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.finances.entityDefinitions.FederalWageBracket;
import modules.finances.entityDefinitions.PayableEmployee;
import modules.finances.entityDefinitions.ProvincialWageBracket;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;

/**
 * Action CalculateTaxAndSalary : représente l'action de calculer l'impôt
 * fédéral/provincial et salaire nette retenue en fonction du montant gagné
 * 
 * Called après avoir ajouté la période actuelle de paye dans PayableEmployee
 * 
 * @author Pascal Lemay
 */
public class CalculateTaxAndSalary extends AbstractAction
{
    /**
     * constructeur
     * @throws Exception si création fail
     */
    public CalculateTaxAndSalary() throws Exception
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

	// Convertion par extension en salaire annuel pour les calcules
	salary *= 26;

	// salaire - min de la tranche actuelle
	Double basedAmount = 0.0;

	// impôt sur la montant de base :(pour impôt sur tranche(s)
	// inférieure(s) si salaire sur
	// plus d'une tranche
	Double taxResult = 0.0;

	// taxResult + (le montant d'adjustment : impôt sur le min)
	Double finalTaxResult = 0.0;

	Double totalTax = 0.0;// Impôt fédéral+Provincial

	Vector<AbstractOrmEntity> fedBrackets = Orm
		.select(new FederalWageBracket());
	Vector<AbstractOrmEntity> provBrackets = Orm
		.select(new ProvincialWageBracket());

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

		totalTax += finalTaxResult;
		employee.setData("fedTax", finalTaxResult);

		i = fedBrackets.size(); // sortie
	    }
	}

	basedAmount = 0.0;// reset pour calculer taxes provicial
	taxResult = 0.0;
	finalTaxResult = 0.0;

	for (int i = 0; i < provBrackets.size(); i++)
	{
	    if (salary <= (Double) provBrackets.get(i).getData("maxBracket"))
	    {

		basedAmount = salary
			- (Double) provBrackets.get(i).getData("minBracket");

		taxResult = basedAmount
			* ((Double) provBrackets.get(i).getData("tax") / 100.0);

		finalTaxResult = taxResult
			+ (Double) provBrackets.get(i).getData("ajustment");

		finalTaxResult /= 26;

		totalTax += finalTaxResult;
		employee.setData("provTax", finalTaxResult);

		i = provBrackets.size(); // sortie
	    }
	}

	// salaire nette
	employee.setData("balance", (salary / 26) - totalTax);
	employee.save();
	return null;
    }

}
