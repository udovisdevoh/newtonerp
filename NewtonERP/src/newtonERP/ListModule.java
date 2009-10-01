/**
 * 
 */
package newtonERP;

import java.io.File;
import java.util.Hashtable;

import newtonERP.module.Module;
import newtonERP.module.ModuleException;
import newtonERP.module.ModuleNotFoundException;

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
     * @throws ModuleNotFoundException si le module n'existe pas
     * @throws ModuleException le module n'est pas conforme
     */
    public static Module getModule(String moduleName)
	    throws ModuleNotFoundException, ModuleException
    {
	Object mod = null;

	try
	{
	    mod = Class.forName(moduleName).newInstance();
	    if (!(mod instanceof Module))
	    {
		throw new ModuleNotFoundException(moduleName);
	    }
	} catch (InstantiationException e)
	{
	    e.printStackTrace();
	    throw new ModuleException(moduleName);
	} catch (IllegalAccessException e)
	{
	    e.printStackTrace();
	    throw new ModuleException(moduleName);
	} catch (ClassNotFoundException e)
	{
	    e.printStackTrace();
	    throw new ModuleNotFoundException(moduleName);
	}

	return (Module) mod;
    }

    /**
     * initialise la liste de module
     * 
     * @return la liste des modules
     */
    public static Hashtable<String, File> initAllModule()
    {
	Hashtable<String, File> myModules = new Hashtable<String, File>();
	File folder = new File("modules");
	File[] listOfFiles = folder.listFiles();

	for (int i = 0; i < listOfFiles.length; i++)
	{
	    if (listOfFiles[i].getName().matches(".*\\.jar"))
	    {
		myModules.put(listOfFiles[i].getName().split("\\.")[0],
			listOfFiles[i].getAbsoluteFile());
	    }
	}
	return myModules;

    }

    public static void main(String[] args)
    {
	System.out.println(ListModule.initAllModule());
	try
	{
	    getModule("TestModule");
	} catch (ModuleNotFoundException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (ModuleException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
