package newtonERP.sourceCodeBuilder;

import modules.taskModule.entityDefinitions.EntityEntity;
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

    /**
     * @param entityEntity entité pour laquelle on veut le code source
     * @return code source d'une entité
     * @throws Exception si construction fail
     */
    public static String buildEntitySourceCode(EntityEntity entityEntity)
	    throws Exception
    {
	return EntitySourceCodeBuilder.build(entityEntity);
    }
}
