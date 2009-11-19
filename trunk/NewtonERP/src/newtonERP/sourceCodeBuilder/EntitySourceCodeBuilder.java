package newtonERP.sourceCodeBuilder;

import modules.taskModule.entityDefinitions.EntityEntity;
import modules.taskModule.entityDefinitions.ModuleEntity;
import newtonERP.orm.Orm;

/**
 * Sert à générer le code source d'une entité
 * @author Guillaume Lacasse
 */
public class EntitySourceCodeBuilder
{
    /**
     * @param entityEntity entité pour laquelle on veut le code source
     * @return code source
     * @throws Exception si construction fail
     */
    public static String build(EntityEntity entityEntity) throws Exception
    {
	entityEntity = (EntityEntity) Orm.selectUnique(entityEntity);

	String sourceCode = "";

	sourceCode += "package modules." + getPackageName(entityEntity)
		+ ".entityDefinitions;\n";
	sourceCode += "\n";
	sourceCode += "import newtonERP.orm.field.*;\n";

	return sourceCode;
    }

    private static String getPackageName(EntityEntity entityEntity)
	    throws Exception
    {
	return ModuleSourceCodeBuilder
		.getPackageName(getModuleEntity(entityEntity));
    }

    private static ModuleEntity getModuleEntity(EntityEntity entityEntity)
	    throws Exception
    {
	return (ModuleEntity) entityEntity.getSingleAccessor(new ModuleEntity()
		.getForeignKeyName());
    }

}
