package modules.taskModule.actions;

import java.io.File;
import java.util.Hashtable;
import java.util.Vector;

import modules.taskModule.entityDefinitions.ActionEntity;
import modules.taskModule.entityDefinitions.EntityEntity;
import modules.taskModule.entityDefinitions.ModuleEntity;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.Orm;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.sourceCodeBuilder.ActionSourceCodeBuilder;
import newtonERP.sourceCodeBuilder.SourceCodeBuilder;
import newtonERP.viewers.viewerData.BaseViewerData;

/**
 * Sert à générer dynamiquement le code d'une action
 * @author Guillaume Lacasse
 */
public class GenerateActionCode extends AbstractAction
{
    /**
     * @throws Exception si création fail
     */
    public GenerateActionCode() throws Exception
    {
	super(new ActionEntity());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	ActionEntity actionEntity = (ActionEntity) entity;

	actionEntity = (ActionEntity) Orm.selectUnique(actionEntity);

	String fileName = buildFileName(actionEntity);

	BaseViewerData editUI = actionEntity.editUI(parameters);

	if (!(new File(fileName).exists()))
	{
	    new File(getPackagePath(actionEntity)).mkdir();
	    String sourceCode = ActionSourceCodeBuilder.build(actionEntity);
	    GenerateSourceCode.writeClassFile(fileName, sourceCode);
	    createRights(actionEntity);
	    editUI.addAlertMessage("La classe de l'action a été créée.");
	}
	else
	{
	    editUI.addAlertMessage("La classe d'action existe déjà.");
	}

	return editUI;
    }

    private void createRights(ActionEntity actionEntity) throws Exception
    {
	ModuleEntity moduleEntity = actionEntity.getModuleEntity();
	String moduleName = moduleEntity.getDataString("systemName");
	String actionName = actionEntity.getDataString("systemName");
	Vector<String> entityList = getEntityNameList(moduleEntity);
	for (String entityName : entityList)
	    GenerateEntityCode.createRightIfNotExist(moduleName, entityName,
		    actionName);
    }

    private Vector<String> getEntityNameList(ModuleEntity moduleEntity)
	    throws Exception
    {
	PluralAccessor entityEntityList = moduleEntity
		.getPluralAccessor(new EntityEntity().getSystemName());

	Vector<String> entityNameList = new Vector<String>();
	entityNameList.add("");

	EntityEntity entityEntity;
	for (AbstractOrmEntity entity : entityEntityList)
	{
	    entityEntity = (EntityEntity) entity;
	    entityNameList.add(entityEntity.getDataString("systemName"));
	}

	return entityNameList;
    }

    private String buildFileName(ActionEntity actionEntity) throws Exception
    {
	String path = getPackagePath(actionEntity) + "/actions";
	String fileName;
	fileName = path + "/" + actionEntity.getDataString("systemName")
		+ ".java";
	return fileName;
    }

    private String getPackagePath(ActionEntity actionEntity) throws Exception
    {
	ModuleEntity moduleEntity = actionEntity.getModuleEntity();
	return SourceCodeBuilder.getModulePackagePath(moduleEntity);
    }
}
