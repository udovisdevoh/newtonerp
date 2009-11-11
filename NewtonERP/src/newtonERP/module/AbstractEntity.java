package newtonERP.module;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.exception.EntityException;
import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldBool;
import newtonERP.module.field.Fields;
import newtonERP.module.generalEntity.ListOfValue;

/**
 * @author r3lemaypa, r3lacasgu, CloutierJo
 * 
 */
public class AbstractEntity
{
    protected Fields fields;
    private HashSet<String> hiddenFieldList;
    private Vector<String> alertMessageList;
    private Vector<String> normalMessageList;
    private Hashtable<String, ListOfValue> positiveListOfValueList;
    private static HashSet<ListOfValue> negativeListOfValueList;
    protected Module currentModule;
    private AbstractAction currentAction;
    protected String promptMessage;
    private HashSet<String> currencyFormattedFieldList;
    private HashSet<String> longTextFieldList;

    /**
     * construit une entity ne comportant aucun champ
     * @throws Exception exceptions si création fail
     */
    public AbstractEntity() throws Exception
    {
	fields = initFields();
    }

    /**
     * initialise les champ de l'entity, doit etre overider si l'entity contient
     * des champs, sinon initialise une liste de champ vide
     * 
     * @return le Fields initialiser
     * @throws Exception si initFields fail
     */
    public Fields initFields() throws Exception
    {
	return new Fields();
    }

    /**
     * @param fieldName the field name to hide
     */
    public void addHiddenField(String fieldName)
    {
	if (hiddenFieldList == null)
	    hiddenFieldList = new HashSet<String>();
	hiddenFieldList.add(fieldName);
    }

    /**
     * @param fieldName the field to check
     * @return true if it's hidden and false if it's not
     */
    public boolean isFieldHidden(String fieldName)
    {
	if (hiddenFieldList == null)
	    return false;
	return hiddenFieldList.contains(fieldName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj)
    {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof AbstractEntity))
	    return false;
	AbstractEntity other = (AbstractEntity) obj;
	if (fields == null)
	{
	    if (other.fields != null)
		return false;
	}
	else if (!fields.equals(other.fields))
	    return false;
	return true;
    }

    @SuppressWarnings("unused")
    private void setFields(Fields fields)
    {
	// must not be implemented
    }

    /**
     * @return the fields
     */
    public Fields getFields()
    {
	return fields;
    }

    public String toString()
    {
	return fields.toString();
    }

    /**
     * methode d'Accces rapide au donnees sous forme de string
     * 
     * @param shortName le nom du champ voulu
     * @return la valeur sous forme de string
     */
    public String getDataString(String shortName)
    {
	return getFields().getField(shortName).getDataString();
    }

    /**
     * @param shortName le nom du champ voulu
     * @return the data
     */
    public Object getData(String shortName)
    {
	return getFields().getField(shortName).getData();
    }

    /**
     * @param shortName le nom du champ voulu
     * @param data the data to set
     * @throws FieldNotCompatibleException si le type ne correspond pas avec le
     *             champ demande
     */
    public void setData(String shortName, Object data)
	    throws FieldNotCompatibleException
    {
	getFields().getField(shortName).setData(data);
    }

    /**
     * @return Liste des warning pouvant être affichés
     */
    public Vector<String> getAlertMessageList()
    {
	if (alertMessageList == null)
	    alertMessageList = new Vector<String>();
	return alertMessageList;
    }

    /**
     * @param fieldKeyName the field name
     * @return If list of value exist, return it, else, return null
     */
    public ListOfValue tryMatchListOfValue(String fieldKeyName)
    {
	if (positiveListOfValueList == null)
	    return null;

	return positiveListOfValueList.get(fieldKeyName);
    }

    /**
     * @return action courante
     */
    public AbstractAction getCurrentAction()
    {
	return currentAction;
    }

    /**
     * @return module courant
     * @throws Exception si obtiention fail
     */
    public Module getCurrentModule() throws Exception
    {
	if (currentModule == null)
	    throw new EntityException(
		    "Vous devez setter le module courrant dans le viewer avec setCurrentModule()");

	return currentModule;
    }

    /**
     * @param currentModule Défini le module utilisé en ce moment pour cette
     *            entité
     */
    public final void setCurrentModule(Module currentModule)
    {
	this.currentModule = currentModule;
    }

    /**
     * @param currentAction Action qui sera utilisée
     */
    public final void setCurrentAction(AbstractAction currentAction)
    {
	this.currentAction = currentAction;
    }

    /**
     * @param message message à ajouter
     */
    public final void addAlertMessage(String message)
    {
	getAlertMessageList().add(message);
    }

    /**
     * @return message d'invite
     */
    public String getPromptMessage()
    {
	if (promptMessage == null)
	    promptMessage = this.getClass().getSimpleName();
	return promptMessage;
    }

    /**
     * @param promptMessage message d'invite
     */
    public void setPromptMessage(String promptMessage)
    {
	this.promptMessage = promptMessage;
    }

    /**
     * @param inputName nom d'un field
     * @return nom complete d'un field
     */
    public final String getLabelName(String inputName)
    {
	return getFields().getField(inputName).getName();
    }

    /**
     * @return liste des listOfValue
     */
    public Hashtable<String, ListOfValue> getPositiveListOfValueList()
    {
	if (positiveListOfValueList == null)
	    positiveListOfValueList = new Hashtable<String, ListOfValue>();

	return positiveListOfValueList;
    }

    /**
     * @return liste des listOfValue
     */
    public HashSet<ListOfValue> getNegativeListOfValueList()
    {
	if (negativeListOfValueList == null)
	    negativeListOfValueList = new HashSet<ListOfValue>();

	return negativeListOfValueList;
    }

    /**
     * @param inputName nom du field
     * @return retounre true si le field doit être formatté en argent
     */
    public boolean isMatchCurrencyFormat(String inputName)
    {
	if (currencyFormattedFieldList == null)
	    return false;

	return currencyFormattedFieldList.contains(inputName);
    }

    /**
     * @param fieldName défini un nom de champ qui sera formaté en argent
     */
    public final void addCurrencyFormat(String fieldName)
    {
	if (currencyFormattedFieldList == null)
	    currencyFormattedFieldList = new HashSet<String>();
	currencyFormattedFieldList.add(fieldName);
    }

    /**
     * @param foreignKeyName nom de la clef etrangère de la listOfValue
     * @param listOfValue listOfValue à ajouter
     */
    public void addPositiveListOfValue(String foreignKeyName,
	    ListOfValue listOfValue)
    {
	// laissez le get car c'est de la lazy initialization -Guillaume
	getPositiveListOfValueList().put(foreignKeyName, listOfValue);
    }

    /**
     * @param foreignKeyName nom de la clef etrangère de la listOfValue
     * @param listOfValue listOfValue à ajouter
     */
    public void addNegativeListOfValue(ListOfValue listOfValue)
    {
	// laissez le get car c'est de la lazy initialization -Guillaume
	getNegativeListOfValueList().add(listOfValue);
    }

    /**
     * @return Liste des messages normaux
     */
    public Vector<String> getNormalMessageList()
    {
	if (normalMessageList == null)
	    normalMessageList = new Vector<String>();
	return normalMessageList;
    }

    /**
     * @param message nouveau message normal
     */
    public void addNormalMessage(String message)
    {
	getNormalMessageList().add(message);
    }

    private HashSet<String> getLongTextFieldList()
    {
	if (longTextFieldList == null)
	    longTextFieldList = new HashSet<String>();
	return longTextFieldList;
    }

    protected void addLongText(String fieldName)
    {
	getLongTextFieldList().add(fieldName);
    }

    /**
     * @param inputName nom du champ
     * @return si le nom du champ correspond à un long champ texte
     */
    public boolean isMatchLongText(String inputName)
    {
	return getLongTextFieldList().contains(inputName);
    }

    /**
     * @param inputName nom du champ
     * @return si le champ est un checkbox
     */
    public boolean isMatchCheckBox(String inputName)
    {
	Field field = getFields().getField(inputName);

	if (field == null)
	    return false;

	if (field instanceof FieldBool)
	    return true;
	return false;
    }
}