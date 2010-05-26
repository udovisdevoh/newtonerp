package newtonERP.module.generalEntity;

import newtonERP.module.AbstractAction;
import newtonERP.module.Module;
import newtonERP.orm.field.Field;
import newtonERP.viewers.viewerData.PromptViewerData;

/**
 * @author Guillaume Lacasse
 * 
 *         A loging form
 */
public class Form extends PromptViewerData
{
    /**
     * @param currentModule module courant
     * @param currentAction action courante
     * @param currentSelectedUser the currecntly selected user
     * @throws Exception an exception that can occur
     */
    public Form(Module currentModule, AbstractAction currentAction)
	    throws Exception
    {
	super();
	setData(new VolatilEntity());
	setCurrentModule(currentModule);
	setCurrentAction(currentAction);
    }

    /**
     * @param label étiquette du champ
     * @param fieldName nom du champ
     * @param currentValue valeur courante
     * @throws Exception remonte
     */
    public void addField(String label, String fieldName, String currentValue)
	    throws Exception
    {
	((VolatilEntity) getData()).addField(label, fieldName, currentValue);
    }

    /**
     * @param label étiquette du champ
     * @param fieldName nom du champ
     * @throws Exception remonte
     */
    public void addField(String label, String fieldName) throws Exception
    {
	((VolatilEntity) getData()).addField(label, fieldName);
    }

    /**
     * @param field un field a ajouter
     */
    public void addField(Field<?> field)
    {
	((VolatilEntity) getData()).addField(field);
    }
}
