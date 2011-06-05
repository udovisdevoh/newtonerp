/**
 * 
 */
package newtonERP.common;

import newtonERP.module.Module;

/**
 * représente les fichier de module et permet donc de ne pas faire la gestion du systeme de fichier a l'extérieur de
 * cette class
 * 
 * @author Cloutierjo
 */
public class FileModule {
	private String name;
	private String filePath;
	private String packagePath;
	private Module cache;

	/**
	 * constructor
	 * 
	 * @param name module name
	 * @param filePath path to the module's root
	 */
	public FileModule(String name, String filePath) {
		String endName = name.substring(1);
		String firstChar = name.substring(0, 1);
		this.name = firstChar.toUpperCase() + endName;
		packagePath = "modules." + firstChar.toLowerCase() + endName;
		this.filePath = filePath;
	}

	/**
	 * get a full file path to a package
	 * 
	 * @return a full file path to a package
	 */
	public String getPackagePathName() {
		return getPackagePath() + "." + getName();
	}

	/**
	 * get a cached module
	 * 
	 * @return the cached module, return null if there is no cache
	 */
	public Module getCache() {
		return cache;
	}

	/**
	 * set a cached module
	 * 
	 * @param cache the cache to set
	 */
	public void setCache(Module cache) {
		this.cache = cache;
	}

	/**
	 * get the name of the module
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * get the path to the modules root package of this module's package
	 * 
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * get the package path to the module (whitout the module name)
	 * 
	 * @return the packagePath
	 */
	public String getPackagePath() {
		return packagePath;
	}
}
