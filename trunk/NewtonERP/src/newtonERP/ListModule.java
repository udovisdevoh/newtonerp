/**
 * 
 */
package newtonERP;

import java.util.Hashtable;

import newtonERP.module.Module;

/**
 * @author Gabriel Therrien JoCloutier
 * 
 */
public class ListModule
{
    private static Hashtable<String, Module> allModules;

    /**
     * ajoute un module a la liste
     * 
     * @param module le module a ajoute
     */
    public static void addModule(Module module)
    {
	String moduleName = module.getClass().getSimpleName();
	allModules.put(moduleName, module);
    }

    /**
     * retire un module de la liste
     * 
     * @param moduleName nom du module a retirer
     */
    public static void removeModule(String moduleName)
    {
	allModules.remove(moduleName);
    }

    /**
     * permet d'avoir la liste de tout les modules du programe
     * 
     * @return liste de tout les modules
     */
    public static Hashtable<String, Module> getAllModules()
    {
	return allModules;
    }

    /**
     * retourne le module de nom moduleName
     * 
     * @param moduleName nom du module
     * @return le module
     */
    public static Module getModule(String moduleName)
    {
	return allModules.get(moduleName);
    }

    /**
     * initialise la liste de module
     */
    public static void initAllModule()
    {
	// TODO: trouve la liste des dossier
    }
}
