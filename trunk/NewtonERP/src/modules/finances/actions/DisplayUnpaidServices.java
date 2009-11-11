package modules.finances.actions;

import java.util.Hashtable;

import modules.finances.entityDefinitions.ServiceProviderAccount;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;

/**
 * Action CalculerSalaires: represente l'action de calculer le montant pour
 * salaire mensuel.
 * 
 * p.s. cette classe me sert de test pour l'instant...
 * @author Pascal Lemay
 */

public class DisplayUnpaidServices extends AbstractAction
{
    /**
     * constructeur
     * @throws Exception si création fail
     */
    public DisplayUnpaidServices() throws Exception
    {
	super(new ServiceProviderAccount());//
    }

    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	// perform(parameters);

	return null;
    }
}
