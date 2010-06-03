/**
 * 
 */
package newtonERP.common;

import newtonERP.module.Module;

public class FileModule
{
	private String name;
	private String filePath;
	private String packagePath;
	private Module cache;

	/**
	 * @param name
	 * @param filePath
	 */
	public FileModule(String name, String path)
	{
		String endName = name.substring(1);
		String firstChar = name.substring(0, 1);
		this.name = firstChar.toUpperCase() + endName;
		packagePath = "modules." + firstChar.toLowerCase() + endName;
		filePath = path;
	}

	public String getPackagePathName()
	{
		return getPackagePath() + "." + getName();
	}

	/**
	 * @return the cache
	 */
	public Module getCache()
	{
		return cache;
	}

	/**
	 * @param cache the cache to set
	 */
	public void setCache(Module cache)
	{
		this.cache = cache;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath()
	{
		return filePath;
	}

	/**
	 * @return the packagePath
	 */
	public String getPackagePath()
	{
		return packagePath;
	}

}