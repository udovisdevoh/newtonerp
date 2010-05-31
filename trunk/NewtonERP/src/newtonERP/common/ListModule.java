/**
 * 
 */
package newtonERP.common;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Hashtable;

import newtonERP.module.Module;
import newtonERP.module.exception.ModuleException;
import newtonERP.module.exception.ModuleNotFoundException;
import newtonERP.serveur.ConfigManager;

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
			URL urls[] = new URL[1];
			urls[0] = new URL("file:" + ConfigManager.getModulesPath());
			URLClassLoader urlClassLoader = new URLClassLoader(urls);

			mod = urlClassLoader.loadClass(allModules.get(moduleName))
					.newInstance();
			if (!(mod instanceof Module))
			{
				throw new ModuleNotFoundException(moduleName);
			}
		} catch (Exception e)
		{
			e.printStackTrace(); // TODO add stackTrace to new exception
			throw new ModuleException(moduleName);
		}
		modulesCache.put(moduleName, (Module) mod);
		return (Module) mod;
	}

	/**
	 * initialise la liste de module
	 * @throws Exception si sa fail
	 */
	public static void initAllModule() throws Exception
	{
		File folder = new File(ConfigManager.getModulesPath() + "modules");
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
