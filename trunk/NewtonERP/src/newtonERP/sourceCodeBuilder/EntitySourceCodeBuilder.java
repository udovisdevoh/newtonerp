package newtonERP.sourceCodeBuilder;

import modules.taskModule.entityDefinitions.AccessorEntity;
import modules.taskModule.entityDefinitions.EntityEntity;
import modules.taskModule.entityDefinitions.FieldEntity;
import modules.taskModule.entityDefinitions.FieldTypeEntity;
import modules.taskModule.entityDefinitions.ModuleEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.PluralAccessor;

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
	sourceCode += "import newtonERP.orm.field.type.*;";
	sourceCode += "import newtonERP.orm.associations.AccessorManager;\n";
	sourceCode += "import newtonERP.module.AbstractOrmEntity;\n";
	sourceCode += "import java.util.Hashtable;\n";
	sourceCode += "import java.util.Vector;\n";

	sourceCode += "\n";
	sourceCode += "/**\n";
	sourceCode += " * " + getVisibleName(entityEntity) + "\n";
	sourceCode += " * @author NewtonERP code generator - Guillaume Lacasse\n";
	sourceCode += " */\n";
	sourceCode += "public class " + getSystemName(entityEntity)
		+ " extends AbstractOrmEntity\n";
	sourceCode += "{\n";
	sourceCode += "    /**\n";
	sourceCode += "     * constructor\n";
	sourceCode += "     * @throws Exception remonte\n";
	sourceCode += "     */\n";
	sourceCode += "    public " + getSystemName(entityEntity)
		+ "() throws Exception\n";
	sourceCode += "    {\n";
	sourceCode += "        super();\n";
	sourceCode += "        setVisibleName(\""
		+ getVisibleName(entityEntity) + "\");\n";

	sourceCode += getAccessorListCode(entityEntity);

	sourceCode += "    }\n";
	sourceCode += "\n";
	sourceCode += "    public Fields initFields() throws Exception\n";
	sourceCode += "    {\n";

	sourceCode += "        Vector<Field<?>> fieldList = new Vector<Field<?>>();\n";
	sourceCode += getFieldsCode(entityEntity);
	sourceCode += "        return new Fields(fieldList);\n";
	sourceCode += "    }\n";
	sourceCode += "}\n";

	return sourceCode;
    }

    private static String getAccessorListCode(EntityEntity entityEntity)
	    throws Exception
    {
	String sourceCode = "";

	PluralAccessor accessorList = entityEntity
		.getPluralAccessor("AccessorEntity");

	for (AbstractOrmEntity entity : accessorList)
	{
	    AccessorEntity accessorEntity = (AccessorEntity) entity;
	    sourceCode += "        AccessorManager.addAccessor(this, new "
		    + getForeignEntityName(accessorEntity) + "());\n";
	}

	return sourceCode;
    }

    private static String getForeignEntityName(AccessorEntity accessorEntity)
    {
	return accessorEntity.getDataString("foreignEntityName");
    }

    private static String getFieldsCode(EntityEntity entityEntity)
	    throws Exception
    {
	String sourceCode = "";
	for (FieldEntity fieldEntity : entityEntity)
	    sourceCode += getFieldCode(fieldEntity, entityEntity);
	return sourceCode;
    }

    private static String getFieldCode(FieldEntity fieldEntity,
	    EntityEntity entityEntity) throws Exception
    {
	String sourceCode = "";
	String quotedShortName = getSystemName(fieldEntity);
	if (quotedShortName.equals(getPrimaryKeyName(entityEntity)))
	    quotedShortName = "getPrimaryKeyName()";
	else
	    quotedShortName = "\"" + quotedShortName + "\"";

	String camelCaseName = getCamelCaseName(fieldEntity);

	sourceCode += "\n";
	sourceCode += "        " + getFieldType(fieldEntity) + " "
		+ camelCaseName + " = new " + getFieldType(fieldEntity) + "(\""
		+ getVisibleName(fieldEntity) + "\", " + quotedShortName
		+ ");\n";

	if (isNaturalKey(fieldEntity))
	    sourceCode += "        " + camelCaseName
		    + ".setNaturalKey(true);\n";
	if (isHidden(fieldEntity))
	    sourceCode += "        " + camelCaseName + ".setHidden(true);\n";
	if (isReadOnly(fieldEntity))
	    sourceCode += "        " + camelCaseName + ".setReadOnly(true);\n";
	if (isDynamicField(fieldEntity))
	    sourceCode += "        " + camelCaseName
		    + ".setDynamicField(true);\n";

	sourceCode += "        fieldList.add(" + camelCaseName + ");\n";

	return sourceCode;
    }

    private static String getCamelCaseName(FieldEntity fieldEntity)
    {
	String name = getSystemName(fieldEntity);
	name = name.substring(0, 1).toLowerCase() + name.substring(1);
	return name;
    }

    private static String getPrimaryKeyName(EntityEntity entityEntity)
    {
	String name = getSystemName(entityEntity);
	name = name.substring(0, 1).toLowerCase() + name.substring(1);
	return "PK" + name + "ID";
    }

    private static boolean isDynamicField(FieldEntity fieldEntity)
    {
	return (Boolean) fieldEntity.getData("dynamicField");
    }

    private static boolean isHidden(FieldEntity fieldEntity)
    {
	return (Boolean) fieldEntity.getData("hidden");
    }

    private static boolean isReadOnly(FieldEntity fieldEntity)
    {
	return (Boolean) fieldEntity.getData("readOnly");
    }

    private static boolean isNaturalKey(FieldEntity fieldEntity)
    {
	return (Boolean) fieldEntity.getData("naturalKey");
    }

    private static String getVisibleName(FieldEntity fieldEntity)
    {
	return fieldEntity.getDataString("visibleName");
    }

    private static String getFieldType(FieldEntity fieldEntity)
	    throws Exception
    {
	FieldTypeEntity fieldType = (FieldTypeEntity) fieldEntity
		.getSingleAccessor(new FieldTypeEntity().getForeignKeyName());

	return fieldType.getDataString("systemName");
    }

    private static String getSystemName(FieldEntity fieldEntity)
    {
	return fieldEntity.getDataString("name");
    }

    private static String getSystemName(EntityEntity entityEntity)
    {
	return entityEntity.getDataString("systemName");
    }

    private static String getVisibleName(EntityEntity entityEntity)
    {
	return entityEntity.getDataString("visibleName");
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
