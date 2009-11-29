package modules.finances.actions;

import java.util.Hashtable;

import modules.finances.entityDefinitions.PayableEmployee;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.generalEntity.AlertEntity;

/**
 * Action TestCallPayingEmployees: Test d'appel à PayingEmployees
 * 
 * @author Pascal Lemay
 */
public class TestCallPayingEmployees extends AbstractAction
{
    /**
     * constructeur
     * @throws Exception si création fail
     */
    public TestCallPayingEmployees() throws Exception
    {
	super(null);
    }

    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {

	Hashtable<String, String> h = new Hashtable<String, String>();
	h.put("key1", "1");
	h.put("key2", "3");
	AlertEntity a = (AlertEntity) new PayingEmployees().doAction(null, h);
	if (a != null)
	    return a;

	return new PayableEmployee().getList();

    }
}
