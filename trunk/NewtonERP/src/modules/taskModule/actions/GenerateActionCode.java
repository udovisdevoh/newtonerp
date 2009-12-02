package modules.taskModule.actions;

import java.util.Hashtable;

import modules.taskModule.entityDefinitions.ActionEntity;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;

/**
 * Sert à générer dynamiquement le code d'une action
 * @author Guillaume Lacasse
 */
public class GenerateActionCode extends AbstractAction
{
    /**
     * @throws Exception si création fail
     */
    public GenerateActionCode() throws Exception
    {
	super(new ActionEntity());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	// TODO Auto-generated method stub
	return null;
    }
}
