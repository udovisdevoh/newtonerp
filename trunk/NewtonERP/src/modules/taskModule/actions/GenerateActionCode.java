package modules.taskModule.actions;

import java.io.File;
import java.util.Hashtable;

import modules.taskModule.entityDefinitions.ActionEntity;
import modules.taskModule.entityDefinitions.ModuleEntity;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;
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
	    editUI.addAlertMessage("La classe de l'action a été créée.");
	}
	else
	{
	    editUI.addAlertMessage("La classe d'action existe déjà.");
	}

	return editUI;
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
