/**
 * 
 */
package newtonERP.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import newtonERP.serveur.ConfigManager;

/**
 * @author djo
 * 
 */
public class ModuleLoader
{
	private static URLClassLoader loader = null;

	private static void initialise() throws MalformedURLException, Exception
	{
		URL urls[] = new URL[1];
		urls[0] = new URL("file:" + ConfigManager.getModulesPath());

		loader = new URLClassLoader(urls);
	}

	/**
	 * @param className nom e la class voulue
	 * @return la class demande
	 * @throws Exception en cas de probleme
	 */
	public static Class<?> loadClass(String className) throws Exception
	{
		if (loader == null)
			initialise();
		return loader.loadClass(className);
	}

}
