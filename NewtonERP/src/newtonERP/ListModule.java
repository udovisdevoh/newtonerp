/**
 * 
 */
package newtonERP;

import java.util.Hashtable;

/**
 * @author Gabriel Therrien
 * 
 */
public class ListModule
{
    // TODO: changer object par module partout
    private static Hashtable<String, Object> allModules;

    public static void addModule(String moduleName, Object module)
    {
	allModules.put(moduleName, module);
    }

    public static void removeModule(String moduleName)
    {
	allModules.remove(moduleName);
    }

    public static Hashtable<String, Object> getAllModules()
    {
	return allModules;
    }

    public static Object giveMeTheModule(String moduleName)
    {
	return allModules.get(moduleName);
    }

    // TODO: trouve la liste des dossier
    public static void initAllModule()
    {

    }
}
