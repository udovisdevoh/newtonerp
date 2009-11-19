package newtonERP.sourceCodeBuilder;

import modules.taskModule.entityDefinitions.EntityEntity;
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

	sourceCode += "//TEST1\n";
	sourceCode += "//TEST2\n";

	return sourceCode;
    }

}
