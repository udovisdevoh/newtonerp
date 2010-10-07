package newtonERP.module.generalEntity;

/**
 * @author Guillaume Lacasse
 * 
 *         A loging form
 */
public class Form extends newtonERP.viewers.viewerData.PromptViewerData
{
	/**
	 * @param currentModule module courant
	 * @param currentAction action courante
	 * @param currentSelectedUser the currently selected user
	 */
	public Form(newtonERP.module.Module currentModule,
			newtonERP.module.AbstractAction currentAction)
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
	public void addField(newtonERP.orm.field.Field field)
	{
		((VolatilEntity) getData()).addField(field);
	}

}
