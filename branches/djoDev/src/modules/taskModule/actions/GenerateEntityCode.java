package modules.taskModule.actions;

import java.io.File;
import java.util.Hashtable;
import java.util.Vector;

import modules.taskModule.entityDefinitions.ActionEntity;
import modules.taskModule.entityDefinitions.EntityEntity;
import modules.taskModule.entityDefinitions.ModuleEntity;
import modules.userRightModule.entityDefinitions.Right;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.sourceCodeBuilder.EntitySourceCodeBuilder;
import newtonERP.sourceCodeBuilder.SourceCodeBuilder;
import newtonERP.viewers.viewerData.BaseViewerData;

/**
 * Génère code source d'entité
 * @author Guillaume Lacasse
 */
public class GenerateEntityCode extends AbstractAction
{
    /**
     * @throws Exception si création fail
     */
    public GenerateEntityCode() throws Exception
    {
	super(new EntityEntity());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	EntityEntity entityEntity = (EntityEntity) entity;

	entityEntity = (EntityEntity) Orm.selectUnique(entityEntity);

	String fileName = buildFileName(entityEntity);

	BaseViewerData editUI = entityEntity.editUI(parameters);

	if (!(new File(fileName).exists()))
	{
	    new File(getPackagePath(entityEntity)).mkdir();
	    String sourceCode = EntitySourceCodeBuilder.build(entityEntity);
	    GenerateSourceCode.writeClassFile(fileName, sourceCode);
	    createRights(entityEntity);
	    editUI.addNormalMessage("La classe de l'entité a été créée.");
	}
	else
	{
	    editUI.addAlertMessage("La classe d'entité existe déjà.");
	}

	return editUI;
    }

    private void createRights(EntityEntity entityEntity) throws Exception
    {
	ModuleEntity moduleEntity = entityEntity.getModuleEntity();
	String moduleName = moduleEntity.getDataString("systemName");
	String entityName = entityEntity.getDataString("systemName");
	Vector<String> actionList = getActionNameList(moduleEntity);
	for (String actionName : actionList)
	    createRightIfNotExist(moduleName, entityName, actionName);
    }

    private Vector<String> getActionNameList(ModuleEntity moduleEntity)
	    throws Exception
    {
	PluralAccessor actionEntityList = moduleEntity
		.getPluralAccessor(new ActionEntity().getSystemName());

	Vector<String> actionNameList = new Vector<String>();
	actionNameList.add("Get");
	actionNameList.add("New");
	actionNameList.add("Edit");
	actionNameList.add("Delete");
	actionNameList.add("GetList");

	ActionEntity actionEntity;
	for (AbstractOrmEntity entity : actionEntityList)
	{
	    actionEntity = (ActionEntity) entity;
	    actionNameList.add(actionEntity.getDataString("systemName"));
	}

	return actionNameList;
    }

    private String buildFileName(EntityEntity entityEntity) throws Exception
    {
	String path = getPackagePath(entityEntity) + "/entityDefinitions";
	String fileName;
	fileName = path + "/" + entityEntity.getDataString("systemName")
		+ ".java";
	return fileName;
    }

    private String getPackagePath(EntityEntity entityEntity) throws Exception
    {
	ModuleEntity moduleEntity = entityEntity.getModuleEntity();
	return SourceCodeBuilder.getModulePackagePath(moduleEntity);
    }

    /**
     * @param moduleName module name
     * @param entityName entity name
     * @param actionName action name
     * @throws Exception si ça fail
     */
    public static void createRightIfNotExist(String moduleName,
	    String entityName, String actionName) throws Exception
    {
	Right right = new Right();
	right.setData("moduleName", moduleName);
	right.setData("entityName", entityName);
	right.setData("actionName", actionName);
	Orm.insertUnique(right);
    }
}
