package newtonERP.sourceCodeBuilder;

import java.io.File;

import modules.taskModule.entityDefinitions.ModuleEntity;
import newtonERP.orm.Orm;

/**
 * Cette classe sert à construire le code source de module
 * @author Guillaume Lacasse
 */
public class ModuleSourceCodeBuilder
{
    /**
     * @param moduleEntity entité du module
     * @return code source
     * @throws Exception si construction fail
     */
    public static String build(ModuleEntity moduleEntity) throws Exception
    {
	moduleEntity = (ModuleEntity) Orm.selectUnique(moduleEntity);

	String sourceCode = "";

	sourceCode += "package modules." + getPackageName(moduleEntity) + ";\n";
	sourceCode += "\n";
	sourceCode += "import newtonERP.module.Module;\n";
	sourceCode += "\n";
	sourceCode += "/**\n";
	sourceCode += " * " + getVisibleName(moduleEntity) + "\n";
	sourceCode += " * @author NewtonERP code generator - Guillaume Lacasse\n";
	sourceCode += " */\n";

	sourceCode += "public class " + getSystemName(moduleEntity)
		+ " extends Module\n";
	sourceCode += "{\n";
	sourceCode += "    /**\n";
	sourceCode += "     * constructor\n";
	sourceCode += "     * @throws Exception remonte\n";
	sourceCode += "     */\n";
	sourceCode += "    public " + getSystemName(moduleEntity)
		+ "() throws Exception\n";
	sourceCode += "    {\n";
	sourceCode += "        super();\n";
	sourceCode += "        setVisibleName(\""
		+ getVisibleName(moduleEntity) + "\");\n";
	sourceCode += "    }\n";
	sourceCode += "\n";
	sourceCode += "    public void initDB() throws Exception\n";
	sourceCode += "    {\n";
	sourceCode += "        super.initDB();\n";
	sourceCode += "    }\n";
	sourceCode += "}\n";

	return sourceCode;
    }

    private static String getVisibleName(ModuleEntity moduleEntity)
    {
	return moduleEntity.getDataString("visibleName");
    }

    protected static String getPackageName(ModuleEntity moduleEntity)
    {
	return getSystemName(moduleEntity).substring(0, 1).toLowerCase()
		+ getSystemName(moduleEntity).substring(1);
    }

    private static String getSystemName(ModuleEntity moduleEntity)
    {
	return moduleEntity.getDataString("systemName");
    }

    /**
     * @param moduleEntity module entity
     * @return source class file name
     * @throws Exception si ça fail
     */
    public static String buildClassFileName(ModuleEntity moduleEntity)
	    throws Exception
    {
	String packagePath = getPackagePath(moduleEntity);

	moduleEntity = (ModuleEntity) Orm.selectUnique(moduleEntity);
	return packagePath + "/" + getSystemName(moduleEntity) + ".java";
    }

    /**
     * @param moduleEntity module entity
     * @return package path
     * @throws Exception si ça fail
     */
    public static String getPackagePath(ModuleEntity moduleEntity)
	    throws Exception
    {
	moduleEntity = (ModuleEntity) Orm.selectUnique(moduleEntity);
	return "./src/modules/" + getPackageName(moduleEntity);
    }

    /**
     * @param moduleEntity module entity
     * @throws Exception si ça fail
     */
    public static void createDirectories(ModuleEntity moduleEntity)
	    throws Exception
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
