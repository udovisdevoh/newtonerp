/**
 * 
 */
package newtonERP.common; 
 // TODO: clean up that file

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import newtonERP.module.exception.ModuleException;
import newtonERP.serveur.ConfigManager;

/**
 * @author djo
 */
public class ModuleLoader {
	private static URLClassLoader loader = null;

	private static void initialise() throws MalformedURLException {
		URL urls[] = new URL[1];
		urls[0] = new URL("file:" + ConfigManager.loadStringProperty("modulesPath"));

		loader = new URLClassLoader(urls);
	}

	/**
	 * @param className nom de la class voulue
	 * @return la class demande
	 * @throws MalformedURLException la propriété configurant le path des module est erroné
	 */
	public static Class<?> loadClass(String className) {
		try{
			if(loader == null){
				initialise();
			}

			return loader.loadClass(className);
		}catch(Exception e){
			e.printStackTrace();
			throw new ModuleException("une erreur inconnue est survenue en tentant de chargé la classe " + className);
		}
	}

}
