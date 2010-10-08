/**
 * 
 */
package newtonERP.common;

import java.io.File;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

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
	private static Hashtable<String, FileModule> allModules = new Hashtable<String, FileModule>();

	/**
	 * ajoute un module a la liste
	 * 
	 * @param moduleName le nom du module a ajoute
	 * @param path system path to the module
	 * 
	 */
	public static void addModule(String moduleName, String path)
	{
		FileModule fileMod = new FileModule(moduleName, path);
		allModules.put(fileMod.getName(), fileMod);
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
	public static Set<String> getAllModules()
	{
		return allModules.keySet();
	}

	/**
	 * retourne le module de nom moduleName
	 * 
	 * @param moduleName nom du module
	 * @return le module
	 */
	public static Module getModule(String moduleName)
	{
		Module tmpMod = allModules.get(moduleName).getCache();
		if (tmpMod != null)
		{
			tmpMod = allModules.get(moduleName).getCache();
			tmpMod.initEntityDefinition(allModules.get(moduleName)
					.getFilePath());
			return tmpMod;
		}

		Object mod = null;
		try
		{
			mod = ModuleLoader.loadClass(
					allModules.get(moduleName).getPackagePathName())
					.newInstance();
		} catch (Exception e)
		{
			e.printStackTrace(); // TODO add stackTrace to new exception
			throw new ModuleException(moduleName);
		}
		if (!(mod instanceof Module))
		{
			throw new ModuleNotFoundException(moduleName);
		}
		tmpMod = (Module) mod;

		tmpMod.initAction(allModules.get(moduleName).getFilePath());
		tmpMod.initEntityDefinition(allModules.get(moduleName).getFilePath());

		allModules.get(moduleName).setCache(tmpMod);
		return tmpMod;
	}

	/**
	 * initialise la liste de module
	 */
	public static void initAllModule()
	{
		Vector<String> paths = new Vector<String>();
		// todo: uncomment that => paths.add(ConfigManager.getModulesPath() +
		// "modules");
		paths.add("./bin/modules");
		File folder;
		File[] listOfFiles;
		for (String path : paths)
		{
			folder = new File(path);
			listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++)
			{
				if (listOfFiles[i].isDirectory()
						&& !listOfFiles[i].getName().equals(".svn"))
				{
					addModule(listOfFiles[i].getName(), path + "/"
							+ listOfFiles[i].getName());
				}
			}
		}
	}
}
