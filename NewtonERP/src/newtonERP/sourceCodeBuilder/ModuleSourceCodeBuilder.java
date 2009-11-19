package newtonERP.sourceCodeBuilder;

import modules.taskModule.entityDefinitions.ModuleEntity;

/**
 * Cette classe sert à construire le code source de module
 * @author Guillaume Lacasse
 */
public class ModuleSourceCodeBuilder
{
    /**
     * @param moduleEntity entité du module
     * @return code source
     */
    public static String build(ModuleEntity moduleEntity)
    {
	String sourceCode = "";

	sourceCode += "//TEST1\n";
	sourceCode += "//TEST2\n";

	return sourceCode;
    }
}
