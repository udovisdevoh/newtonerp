package newtonERP.serveur;

import java.util.Hashtable;

/**
 * @author Gabriel Therrien
 * 
 */
public class CommandRouteur
{
    // TODO: changer Object par entity quand il va y en avoir
    public Object routeCommand(String moduleName, String action,
	    Hashtable<String, String> parameter)
    {
	Object module;
	try
	{
	    module = openModule(moduleName);
	    // module.doAction(action,parameter);
	} catch (Exception e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return 0;
    }

    // TODO: on va voir comment on va pouvoir le gosser plus tard
    public Object openModule(String moduleName) throws Exception
    {
	return 0;
    }

}
