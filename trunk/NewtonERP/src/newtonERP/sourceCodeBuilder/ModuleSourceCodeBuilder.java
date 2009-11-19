package newtonERP.sourceCodeBuilder;

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
	sourceCode += "/**\n";
	sourceCode += " * " + getVisibleName(moduleEntity) + "\n";
	sourceCode += " * @author NewtonERP code generator\n";
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

    private static String getPackageName(ModuleEntity moduleEntity)
    {
	return getSystemName(moduleEntity).substring(0, 1).toLowerCase()
		+ getSystemName(moduleEntity).substring(1);
    }

    private static String getSystemName(ModuleEntity moduleEntity)
    {
	return moduleEntity.getDataString("systemName");
    }
}
