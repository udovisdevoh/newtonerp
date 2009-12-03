package modules.finances.actions;

import java.util.Hashtable;
import java.util.Vector;

import modules.finances.entityDefinitions.PayableEmployee;
import modules.finances.entityDefinitions.StateType;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.AlertEntity;
import newtonERP.orm.Orm;

/**
 * Action PayingEmployees: représente l'action de payer les employés de la
 * période de paie actuelle.
 * 
 * 
 * @author Pascal Lemay
 */
public class PayingEmployees extends AbstractAction
{
    /**
     * constructeur
     * @throws Exception si création fail
     */
    public PayingEmployees() throws Exception
    {
	super(null);
    }

    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	int unpaid = 0;
	PayableEmployee searchEmp = new PayableEmployee();
	// employés non payés
	searchEmp.setData(new StateType().getForeignKeyName(), 1);

	Vector<AbstractOrmEntity> employeeToPay = Orm.select(searchEmp);

	for (int i = 0; i < employeeToPay.size(); i++)
	    new CalculateTaxAndSalary().doAction(employeeToPay.get(i), null);

	employeeToPay = Orm.select(searchEmp);

	for (int i = 0; i < employeeToPay.size(); i++)

	{

	    AlertEntity alert = (AlertEntity) new PayingEmployee().doAction(
		    employeeToPay.get(i), null);

	    if (alert != null)
		unpaid++;
	}

	if (unpaid != 0)
	    return new AlertEntity(
		    String.valueOf(unpaid)
			    + " employé(s) non payé(s) faute de fond\n vérifiez le compte correspondant au paiement");

	return new PayableEmployee().getList();
    }
}
