package newtonERP.module.generalEntity;

import java.util.Hashtable;

import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.Module;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.VolatileFields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * @author Guillaume Lacasse
 * 
 *         A loging form
 */
public class Form extends AbstractEntity implements PromptViewable
{
    private String buttonCaption;
    private String backLinkUrl;
    private String backLinkName;

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
	fields = new VolatileFields();
	setCurrentModule(currentModule);
	setCurrentAction(currentAction);
    }

    /**
     * @param label étiquette du champ
     * @param fieldName nom du champ
     * @param currentValue valeur courante
     * @throws Exception remonte
     */
    public void addNewField(String label, String fieldName, String currentValue)
	    throws Exception
    {
	Field field = new FieldString(label, fieldName);
	field.setData(currentValue);
	addNewField(field);
    }

    /**
     * @param label étiquette du champ
     * @param fieldName nom du champ
     * @throws Exception remonte
     */
    public void addNewField(String label, String fieldName) throws Exception
    {
	addNewField(label, fieldName, "");
    }

    /**
     * @param field un field a ajouter
     */
    public void addNewField(Field field)

    {
	((VolatileFields) (getFields())).add(field);
    }

    @Override
    public String getButtonCaption()
    {
	if (buttonCaption == null)
	    buttonCaption = "Ok";
	return buttonCaption;
    }

    /**
     * @param buttonCaption nom visible du bouton
     */
    public void setButtonCaption(String buttonCaption)
    {
	this.buttonCaption = buttonCaption;
    }

    @Override
    public Hashtable<String, FlagPool> getPositiveFlagPoolList()
    {
	// Aucun flag pool dans un formulaire, null: OK
	return null;
    }

    @Override
    public String getInputValue(String inputName)
    {
	return fields.getField(inputName).getDataString();
    }

    @Override
    public String getBackLinkName()
    {
	return backLinkName;
    }

    @Override
    public String getBackLinkUrl()
    {
	return backLinkUrl;
    }
}
