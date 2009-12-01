package modules.taskModule.actions;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Hashtable;

import modules.taskModule.entityDefinitions.ModuleEntity;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.sourceCodeBuilder.SourceCodeBuilder;
import newtonERP.viewers.viewerData.BaseViewerData;

/**
 * Cette action sert à écrire les fichiers de code source d'un module
 * @author Guillaume Lacasse
 */
public class GenerateSourceCode extends AbstractAction
{
    /**
     * @throws Exception si création fail
     */
    public GenerateSourceCode() throws Exception
    {
	super(new ModuleEntity());
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	ModuleEntity moduleEntity = (ModuleEntity) entity;

	String moduleClassCode;

	String moduleClassFileName = SourceCodeBuilder
		.buildModuleClassFileName(moduleEntity);

	BaseViewerData edit = moduleEntity.editUI(parameters);

	if (fileExists(moduleClassFileName))
	{
	    edit.addAlertMessage("La classe du module existe déjà.");
	}
	else
	{
	    moduleClassCode = SourceCodeBuilder
		    .buildModuleSourceCode(moduleEntity);

	    SourceCodeBuilder.createDirectoryForModule(moduleEntity);

	    edit.addNormalMessage("La classe du module a été générée.");
	    writeClassFile(moduleClassFileName, moduleClassCode);
	}

	return edit;
    }

    private boolean fileExists(String moduleClassFileName)
    {
	File file = new File(moduleClassFileName);
	return file.exists();
    }

    private void writeClassFile(String fileName, String classCode)
	    throws Exception
    {
	File file = new File(fileName);
	file.createNewFile();

	FileWriter outFile = new FileWriter(fileName);
	PrintWriter out = new PrintWriter(outFile);
	out.print(classCode);
	out.close();
    }
}
