package modules.taskModule.actions;

import java.io.File;
import java.util.Hashtable;

import modules.taskModule.entityDefinitions.EntityEntity;
import modules.taskModule.entityDefinitions.ModuleEntity;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.Orm;
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
	    editUI.addAlertMessage("La classe de l'entité a été créée.");
	}
	else
	{
	    editUI.addAlertMessage("La classe d'entité existe déjà.");
	}

	return editUI;
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

}
