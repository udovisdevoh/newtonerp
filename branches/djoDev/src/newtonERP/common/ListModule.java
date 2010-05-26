/**
 * 
 */
package newtonERP.common;

import java.io.File;
import java.util.Hashtable;

import newtonERP.module.Module;
import newtonERP.module.exception.ModuleException;
import newtonERP.module.exception.ModuleNotFoundException;

/**
 * pour créé le jar il faut, en ligne de commande, allé dans le dossier du
 * javaSDK/bin et faire la commande suivant jar cf0 chemin:\lenomduJar.jar
 * chemin:\dossierModule attention de mettre le nom du jarFile en majuscule ex:
 * jar cf0 J:\workspace\NewtonERP\modules\TestModule.jar
 * J:\workspace\NewtonERP\bin\modules\testModule
 * 
 * @author Gabriel Therrien JoCloutier
 * 
 */
public class ListModule
{
    // todo: jumelé les 2 hashtable
    private static Hashtable<String, String> allModules = new Hashtable<String, String>();
    private static Hashtable<String, Module> modulesCache = new Hashtable<String, Module>();

    /**
     * ajoute un module a la liste
     * 
     * @param moduleName le nom du module a ajoute
     * 
     */
    public static void addModule(String moduleName)
    {
	String name = moduleName.substring(1);
	String firstChar = moduleName.substring(0, 1);
	allModules.put(firstChar.toUpperCase() + name, "modules."
		+ firstChar.toLowerCase() + name + "."
		+ firstChar.toUpperCase() + name);
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
    public static Hashtable<String, String> getAllModules()
    {
	return allModules;
    }

    /**
     * retourne le module de nom moduleName
     * 
     * @param moduleName nom du module
     * @return le module
     * @throws Exception remonte
     * @throws ModuleNotFoundException si le module n'existe pas
     */
    public static Module getModule(String moduleName) throws Exception
    {
	if (modulesCache.containsKey(moduleName))
	{
	    Module tmpMod = modulesCache.get(moduleName);
	    tmpMod.resetState();
	    return tmpMod;
	}

	Object mod = null;
	try
	{
	    mod = Class.forName(allModules.get(moduleName)).newInstance();
	    if (!(mod instanceof Module))
	    {
		throw new ModuleNotFoundException(moduleName);
	    }
	} catch (InstantiationException e)
	{
	    e.printStackTrace(); // TODO add stackTrace to new exception
	    throw new ModuleException(moduleName);
	} catch (IllegalAccessException e)
	{
	    e.printStackTrace(); // TODO add stackTrace to new exception
	    throw new ModuleException(moduleName);
	} catch (ClassNotFoundException e)
	{
	    e.printStackTrace(); // TODO add stackTrace to new exception
	    throw new ModuleNotFoundException(moduleName);
	}
	modulesCache.put(moduleName, (Module) mod);
	return (Module) mod;
    }

    /**
     * initialise la liste de module
     */
    public static void initAllModule()
    {
	File folder = new File("src/modules");
	File[] listOfFiles = folder.listFiles();

	for (int i = 0; i < listOfFiles.length; i++)
	{
	    if (listOfFiles[i].isDirectory()
		    && !listOfFiles[i].getName().equals(".svn"))
	    {
		addModule(listOfFiles[i].getName());
	    }
	}
    }
}
