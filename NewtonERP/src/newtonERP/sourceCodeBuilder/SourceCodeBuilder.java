package newtonERP.sourceCodeBuilder;

import modules.taskModule.entityDefinitions.ActionEntity;
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

    /**
     * @param actionEntity entité de l'action
     * @return code source de l'action
     * @throws Exception si construction fail
     */
    public static String buildActionSourceCode(ActionEntity actionEntity)
	    throws Exception
    {
	return ActionSourceCodeBuilder.build(actionEntity);
    }

    /**
     * @param moduleEntity module entity
     * @return source class file name
     * @throws Exception si ça fail
     */
    public static String buildModuleClassFileName(ModuleEntity moduleEntity)
	    throws Exception
    {
	return ModuleSourceCodeBuilder.buildClassFileName(moduleEntity);
    }

    /**
     * @param moduleEntity module entity
     * @throws Exception si ça fail
     */
    public static void createDirectoriesForModule(ModuleEntity moduleEntity)
	    throws Exception
    {
	ModuleSourceCodeBuilder.createDirectories(moduleEntity);
    }

    /**
     * @param moduleEntity module entity
     * @return package path
     * @throws Exception si ça fail
     */
    public static String getModulePackagePath(ModuleEntity moduleEntity)
	    throws Exception
    {
	return ModuleSourceCodeBuilder.getPackagePath(moduleEntity);
    }
}
