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
	 * @param currentSelectedUser the currently selected user
	 */
	public Form(Module currentModule, AbstractAction currentAction)

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
	 */
	public void addField(String label, String fieldName, String currentValue)

	{
		((VolatilEntity) getData()).addField(label, fieldName, currentValue);
	}

	/**
	 * @param label étiquette du champ
	 * @param fieldName nom du champ
	 */
	public void addField(String label, String fieldName)
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
