package newtonERP.sourceCodeBuilder;

/**
 * Cette classe sert à construire le code source de modules, entités et actions
 * @author Guillaume Lacasse
 */
public class SourceCodeBuilder
{
	/**
	 * @param moduleEntity entité du module
	 * @return code source
	 */
	public static String buildModuleSourceCode(
			modules.taskModule.entityDefinitions.ModuleEntity moduleEntity)
	{
		return ModuleSourceCodeBuilder.build(moduleEntity);
	}

	/**
	 * @param entityEntity entité pour laquelle on veut le code source
	 * @return code source d'une entité
	 */
	public static String buildEntitySourceCode(
			modules.taskModule.entityDefinitions.EntityEntity entityEntity)
	{
		return EntitySourceCodeBuilder.build(entityEntity);
	}

	/**
	 * @param actionEntity entité de l'action
	 * @return code source de l'action
	 */
	public static String buildActionSourceCode(
			modules.taskModule.entityDefinitions.ActionEntity actionEntity)
	{
		return ActionSourceCodeBuilder.build(actionEntity);
	}

	/**
	 * @param moduleEntity module entity
	 * @return source class file name
	 */
	public static String buildModuleClassFileName(
			modules.taskModule.entityDefinitions.ModuleEntity moduleEntity)
	{
		return ModuleSourceCodeBuilder.buildClassFileName(moduleEntity);
	}

	/**
	 * @param moduleEntity module entity
	 */
	public static void createDirectoriesForModule(
			modules.taskModule.entityDefinitions.ModuleEntity moduleEntity)
	{
		ModuleSourceCodeBuilder.createDirectories(moduleEntity);
	}

	/**
	 * @param moduleEntity module entity
	 * @return package path
	 */
	public static String getModulePackagePath(
			modules.taskModule.entityDefinitions.ModuleEntity moduleEntity)
	{
		return ModuleSourceCodeBuilder.getPackagePath(moduleEntity);
	}

}
