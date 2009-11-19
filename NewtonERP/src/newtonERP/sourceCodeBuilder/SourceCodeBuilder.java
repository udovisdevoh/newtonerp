package newtonERP.sourceCodeBuilder;

import modules.taskModule.entityDefinitions.ModuleEntity;

/**
 * Cette classe sert à construire le code source de modules, entités et actions
 * @author Guillaume Lacasse
 */
public class SourceCodeBuilder
{
    /**
     * @param moduleEntity entité du module
     * @return code source
     * @throws Exception si construction fail
     */
    public static String buildModuleSourceCode(ModuleEntity moduleEntity)
	    throws Exception
    {
	return ModuleSourceCodeBuilder.build(moduleEntity);
    }
}
