package newtonERP.module.generalEntity;

import java.text.ParseException;
import java.util.Hashtable;

import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.Module;
import newtonERP.module.exception.FieldNotFoundException;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldString;
import newtonERP.module.field.VolatileFields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * @author Guillaume Lacasse
 * 
 *         A loging form
 */
public class Form extends AbstractEntity implements PromptViewable
{

    private Hashtable<String, String> inputList = new Hashtable<String, String>();
    private String buttonCaption;

    /**
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
     * @param label
     * @param fieldName
     * @param currentValue
     * @throws FieldNotFoundException
     * @throws ParseException an exception that can occur during parsing dates
     */
    public void addNewField(String label, String fieldName, String currentValue)
	    throws FieldNotFoundException, ParseException
    {
	Field field = new FieldString(label, fieldName);
	field.setData(currentValue);
	((VolatileFields) (getFields())).add(field);
    }

    /**
     * @param label
     * @param fieldName
     * @throws FieldNotFoundException
     * @throws ParseException an exception that can occur during parsong dates
     */
    public void addNewField(String label, String fieldName)
	    throws FieldNotFoundException, ParseException
    {
	addNewField(label, fieldName, "");
    }

    @Override
    public String getButtonCaption()
    {
	if (buttonCaption == null)
	    buttonCaption = "Ok";
	return buttonCaption;
    }

    public void setButtonCaption(String buttonCaption)
    {
	this.buttonCaption = buttonCaption;
    }

    @Override
    public Hashtable<String, FlagPool> getFlagPoolList()
    {
	// Aucun flag pool dans un formulaire, null: OK
	return null;
    }
}
