package modules.finances.actions;

import java.util.Collection;
import java.util.Hashtable;

import modules.finances.entityDefinitions.PayableEmployee;
import modules.humanResources.entityDefinitions.Employee;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.generalEntity.AlertEntity;
import newtonERP.orm.Orm;

/**
 * Action PayingEmployees: représente l'action de payer les employés
 * correspondants au ID passés en paramètres.
 * 
 * Pourra être callé par task également
 * 
 * parameters data:ID des employés que l'on veut payer
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
	Collection<String> empId = parameters.values();

	for (String id : empId)
	{
	    searchEmp.setData(new Employee().getForeignKeyName(), Integer
		    .parseInt(id));
	    AbstractOrmEntity employeeToPay = Orm.selectUnique(searchEmp);

	    AlertEntity alert = (AlertEntity) new PayingEmployee().doAction(
		    employeeToPay, null);
	    if (alert != null)
		unpaid++;

	}
	if (unpaid != 0)
	    return new AlertEntity(
		    String.valueOf(unpaid)
			    + " employé(s) non payé(s) faute de fond\n vérifiez le compte correspondant au paiement");

	return new AlertEntity(null);
    }

}
