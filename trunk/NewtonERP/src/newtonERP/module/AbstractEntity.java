package newtonERP.module;

import java.util.HashSet;
import java.util.Hashtable;

import newtonERP.module.exception.EntityException;
import newtonERP.module.generalEntity.ListOfValue;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldBool;

/**
 * @author r3lemaypa, r3lacasgu, CloutierJo
 * 
 */
public abstract class AbstractEntity
{
    protected Fields fields;
    private Hashtable<String, ListOfValue> positiveListOfValueList;
    private static HashSet<ListOfValue> negativeListOfValueList;
    protected Module currentModule;
    private AbstractAction currentAction;

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
     * @throws Exception remonte
     */
    public void setData(String shortName, Object data) throws Exception
    {
	getFields().setData(shortName, data);
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
     * @return nom de l'entité dans le système (normalement nom de la classe
     *         mais peut être overridé si c'est une entité dynamique)
     */
    public String getSystemName()
    {
	return getClass().getSimpleName();
    }

    /**
     * @param inputName nom d'un field
     * @return nom complete d'un field
     */
    public String getLabelName(String inputName)
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

	for (ListOfValue currentListOfValue : getNegativeListOfValueList())
	    if (currentListOfValue.equals(listOfValue))
		return;

	getNegativeListOfValueList().add(listOfValue);
    }

    /**
     * @param inputName nom du champ
     * @return si le champ est un checkbox
     */
    public boolean isMatchCheckBox(String inputName)
    {
	Field<?> field = getFields().getField(inputName);

	if (field == null)
	    return false;

	if (field instanceof FieldBool)
	    return true;
	return false;
    }
}