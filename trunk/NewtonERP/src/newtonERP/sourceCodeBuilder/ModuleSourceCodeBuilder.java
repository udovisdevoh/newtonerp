package newtonERP.sourceCodeBuilder;

import java.io.File;

import modules.taskModule.entityDefinitions.ModuleEntity;
import newtonERP.orm.Orm;

/**
 * Cette classe sert à construire le code source de module
 * 
 * @author Guillaume Lacasse
 */
public class ModuleSourceCodeBuilder {
	/**
	 * @param moduleEntity entité du module
	 * @return code source
	 */
	public static String build(ModuleEntity moduleEntity) {
		moduleEntity = (ModuleEntity) Orm.getInstance().selectUnique(moduleEntity);

		String sourceCode = "";

		sourceCode += "package modules." + getPackageName(moduleEntity) + ";\n";
		sourceCode += "\n";
		sourceCode += "import newtonERP.module.Module;\n";
		sourceCode += "\n";
		sourceCode += "/**\n";
		sourceCode += " * " + getVisibleName(moduleEntity) + "\n";
		sourceCode += " * @author NewtonERP code generator - Guillaume Lacasse\n";
		sourceCode += " */\n";

		sourceCode += "public class " + getSystemName(moduleEntity) + " extends Module\n";
		sourceCode += "{\n";
		sourceCode += "    /**\n";
		sourceCode += "     * constructor\n";
		sourceCode += "     */\n";
		sourceCode += "    public " + getSystemName(moduleEntity) + "()\n";
		sourceCode += "    {\n";
		sourceCode += "        super();\n";
		sourceCode += "        setVisibleName(\"" + getVisibleName(moduleEntity) + "\");\n";
		sourceCode += "    }\n";
		sourceCode += "\n";
		sourceCode += "    public void initDB()\n";
		sourceCode += "    {\n";
		sourceCode += "        super.initDB();\n";
		sourceCode += "    }\n";
		sourceCode += "}\n";

		return sourceCode;
	}

	private static String getVisibleName(ModuleEntity moduleEntity) {
		return moduleEntity.getDataString("visibleName");
	}

	protected static String getPackageName(ModuleEntity moduleEntity) {
		return getSystemName(moduleEntity).substring(0, 1).toLowerCase() + getSystemName(moduleEntity).substring(1);
	}

	private static String getSystemName(ModuleEntity moduleEntity) {
		return moduleEntity.getDataString("systemName");
	}

	/**
	 * @param moduleEntity module entity
	 * @return source class file name
	 */
	public static String buildClassFileName(ModuleEntity moduleEntity)

	{
		String packagePath = getPackagePath(moduleEntity);

		moduleEntity = (ModuleEntity) Orm.getInstance().selectUnique(moduleEntity);
		return packagePath + "/" + getSystemName(moduleEntity) + ".java";
	}

	/**
	 * @param moduleEntity module entity
	 * @return package path
	 */
	public static String getPackagePath(ModuleEntity moduleEntity)

	{
		moduleEntity = (ModuleEntity) Orm.getInstance().selectUnique(moduleEntity);
		return "./src/modules/" + getPackageName(moduleEntity);
	}

	/**
	 * @param moduleEntity module entity
	 */
	public static void createDirectories(ModuleEntity moduleEntity)

	{
		File file;
		String packagePath = getPackagePath(moduleEntity);
		file = new File(packagePath);
		file.mkdir();
		file = new File(packagePath + "/actions");
		file.mkdir();
		file = new File(packagePath + "/entityDefinitions");
		file.mkdir();
	}
}
