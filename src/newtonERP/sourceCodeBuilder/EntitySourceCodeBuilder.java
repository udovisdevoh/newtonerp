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
	 */
	public static String build(
			modules.taskModule.entityDefinitions.EntityEntity entityEntity)
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
		sourceCode += "     */\n";
		sourceCode += "    public " + getSystemName(entityEntity) + "()\n";
		sourceCode += "    {\n";
		sourceCode += "        super();\n";
		sourceCode += "        setVisibleName(\""
				+ getVisibleName(entityEntity) + "\");\n";

		sourceCode += getAccessorListCode(entityEntity);

		sourceCode += "    }\n";
		sourceCode += "\n";
		sourceCode += "    public Fields initFields()\n";
		sourceCode += "    {\n";

		sourceCode += "        Vector<Field<?>> fieldList = new Vector<Field<?>>();\n";
		sourceCode += getFieldsCode(entityEntity);
		sourceCode += "        return new Fields(fieldList);\n";
		sourceCode += "    }\n";
		sourceCode += "}\n";

		return sourceCode;
	}

	private static String getAccessorListCode(
			modules.taskModule.entityDefinitions.EntityEntity entityEntity)
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

	private static String getForeignEntityName(
			modules.taskModule.entityDefinitions.AccessorEntity accessorEntity)
	{
		return accessorEntity.getDataString("foreignEntityName");
	}

	private static String getFieldsCode(
			modules.taskModule.entityDefinitions.EntityEntity entityEntity)
	{
		String sourceCode = "";
		for (FieldEntity fieldEntity : entityEntity)
			sourceCode += getFieldCode(fieldEntity, entityEntity);
		return sourceCode;
	}

	private static String getFieldCode(
			modules.taskModule.entityDefinitions.FieldEntity fieldEntity,
			modules.taskModule.entityDefinitions.EntityEntity entityEntity)
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

	private static String getCamelCaseName(
			modules.taskModule.entityDefinitions.FieldEntity fieldEntity)
	{
		String name = getSystemName(fieldEntity);
		name = name.substring(0, 1).toLowerCase() + name.substring(1);
		return name;
	}

	private static String getPrimaryKeyName(
			modules.taskModule.entityDefinitions.EntityEntity entityEntity)
	{
		String name = getSystemName(entityEntity);
		name = name.substring(0, 1).toLowerCase() + name.substring(1);
		return "PK" + name + "ID";
	}

	private static boolean isDynamicField(
			modules.taskModule.entityDefinitions.FieldEntity fieldEntity)
	{
		return (Boolean) fieldEntity.getData("dynamicField");
	}

	private static boolean isHidden(
			modules.taskModule.entityDefinitions.FieldEntity fieldEntity)
	{
		return (Boolean) fieldEntity.getData("hidden");
	}

	private static boolean isReadOnly(
			modules.taskModule.entityDefinitions.FieldEntity fieldEntity)
	{
		return (Boolean) fieldEntity.getData("readOnly");
	}

	private static boolean isNaturalKey(
			modules.taskModule.entityDefinitions.FieldEntity fieldEntity)
	{
		return (Boolean) fieldEntity.getData("naturalKey");
	}

	private static String getVisibleName(
			modules.taskModule.entityDefinitions.FieldEntity fieldEntity)
	{
		return fieldEntity.getDataString("visibleName");
	}

	private static String getFieldType(
			modules.taskModule.entityDefinitions.FieldEntity fieldEntity)
	{
		FieldTypeEntity fieldType = (FieldTypeEntity) fieldEntity
				.getSingleAccessor(new FieldTypeEntity().getForeignKeyName());

		return fieldType.getDataString("systemName");
	}

	private static String getSystemName(
			modules.taskModule.entityDefinitions.FieldEntity fieldEntity)
	{
		return fieldEntity.getDataString("name");
	}

	private static String getSystemName(
			modules.taskModule.entityDefinitions.EntityEntity entityEntity)
	{
		return entityEntity.getDataString("systemName");
	}

	private static String getVisibleName(
			modules.taskModule.entityDefinitions.EntityEntity entityEntity)
	{
		return entityEntity.getDataString("visibleName");
	}

	private static String getPackageName(
			modules.taskModule.entityDefinitions.EntityEntity entityEntity)
	{
		return ModuleSourceCodeBuilder
				.getPackageName(getModuleEntity(entityEntity));
	}

	private static modules.taskModule.entityDefinitions.ModuleEntity getModuleEntity(
			modules.taskModule.entityDefinitions.EntityEntity entityEntity)
	{
		return (ModuleEntity) entityEntity.getSingleAccessor(new ModuleEntity()
				.getForeignKeyName());
	}

}
