package newtonERP.orm.field;

import java.text.ParseException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.FieldNotFoundException;

/**
 * All the fields of an entity
 * 
 * @author CloutierJo
 */
public class Fields implements Iterable<Field<?>>
{

    Hashtable<String, Field<?>> fieldsDataMap;
    Collection<Field<?>> fieldsDataVector;
    boolean ErrorState = false;

    /**
     * constructeur vide, permet de construire un Fields sans aucun champ
     */
    public Fields()
    {
	fieldsDataMap = new Hashtable<String, Field<?>>();
	fieldsDataVector = new Vector<Field<?>>();
    }

    /**
     * @param fields une liste de champ a inclure dans le Fields
     */
    public Fields(Vector<Field<?>> fields)
    {
	fieldsDataVector = fields;
	fieldsDataMap = new Hashtable<String, Field<?>>();
	for (Field<?> field : fields)
	{
	    if (field.getShortName().matches("PK.*"))
		field.setReadOnly(true);
	    fieldsDataMap.put(field.getShortName(), field);
	    field.setFieldsRef(this);
	}
    }

    /**
     * @return the fields
     */
    public Collection<Field<?>> getFields()
    {
	return fieldsDataVector;
    }

    /**
     * @param shortName le nom du champ voulu
     * @return the named field
     */
    public Field<?> getField(String shortName)
    {
	return fieldsDataMap.get(shortName);
    }

    /**
     * methode pour changer la valeur d'un champ
     * 
     * @param shortName nom du champ
     * @param data valeur modifie
     * @throws Exception maudites exceptions non-gérées de Java
     */
    public void setData(String shortName, Object data) throws Exception
    {
	try
	{
	    if (fieldsDataMap.get(shortName).getCalcul() == null)
		if (data instanceof String)
		    fieldsDataMap.get(shortName).setData((String) data);
		else
		    fieldsDataMap.get(shortName).setData(data);
	} catch (NullPointerException e)
	{
	    throw new FieldNotFoundException(shortName);
	}
    }

    /**
     * @param parameters Hashtable de parametre
     * @throws Exception si fail quelque part, maudite exceptions non-gérées de
     *             Java
     * @throws ParseException an exception that can occur during date parsing
     * @throws FieldNotCompatibleException si champ pas compatible
     */
    public void setFromHashTable(Hashtable<String, ?> parameters)
	    throws Exception
    {
	for (String key : parameters.keySet())
	{
	    try
	    {
		Object value = parameters.get(key);
		if (value instanceof String)
		    value = (((String) (value)).replace("&rsquo;", "'"));

		setData(key, value);
	    } catch (FieldNotFoundException e)
	    {
		// nothing to do
	    }
	}
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
	if (!(obj instanceof Fields))
	    return false;
	Fields other = (Fields) obj;
	if (fieldsDataMap == null)
	{
	    if (other.fieldsDataMap != null)
		return false;
	}
	else if (!fieldsDataMap.equals(other.fieldsDataMap))
	    return false;
	if (fieldsDataVector == null)
	{
	    if (other.fieldsDataVector != null)
		return false;
	}
	else if (!fieldsDataVector.equals(other.fieldsDataVector))
	    return false;
	return true;
    }

    public String toString()
    {
	return fieldsDataMap.toString();
    }

    /**
     * iterate over the fields (not the keys)
     */
    @Override
    public Iterator<Field<?>> iterator()
    {
	return fieldsDataVector.iterator();
    }

    /**
     * @return Liste des clefs des fields
     */
    public Collection<String> getKeyList()
    {
	return new Vector<String>(fieldsDataMap.keySet());
    }

    /**
     * @return Liste des nom utilisateur des fields
     */
    public Collection<String> getLongFieldNameList()
    {
	Vector<String> longFieldName = new Vector<String>();
	for (Field<?> field : this)
	    longFieldName.add(field.getName());
	return longFieldName;
    }

    /**
     * met les valeur par defaut dans les field pour ne pas avoir de valeur null
     * 
     * *ATTENTION* NE PAS UTILISE UNE ENTITY COMME SEARCHCRITERIA APRES AVOIR
     * UTILISER CETTE FONCTION
     * @param allField true pour mettre tous les champ a leur valeur par defaut
     *            false pour ne mettre les valeurpar defaut qu'au field n'étant
     *            pas settez
     * 
     * @throws FieldNotCompatibleException remonte
     */
    public void setDefaultValue(boolean allField)
	    throws FieldNotCompatibleException
    {
	for (Field<?> field : getFields())
	{
	    if (field.getData() == null || allField)
		field.setDefaultValue();
	}
    }

    /**
     * identique a setDefaultValue(true);
     * @throws FieldNotCompatibleException remonte
     */
    public void setDefaultValue() throws FieldNotCompatibleException
    {
	setDefaultValue(true);
    }

    /**
     * @param fieldName nom du champ
     * @return true si nom du champ existe dans les fields
     */
    public boolean containsFieldName(String fieldName)
    {
	return fieldsDataMap.containsKey(fieldName);
    }

    /**
     * @return true si au moins un field n'a pas une valeur null, sinon false
     */
    public boolean containsValues()
    {
	for (Field<?> field : this)
	    if (field.getData() != null)
		return true;
	return false;
    }

    /**
     * @return the errorState
     */
    public boolean isErrorState()
    {
	return ErrorState;
    }

    /**
     * @param errorState the errorState to set
     */
    public void setErrorState(boolean errorState)
    {
	ErrorState = errorState;
    }

    /**
     * remet les valeur a null
     * @throws Exception remonte
     */
    public void reset() throws Exception
    {
	ErrorState = false;
	for (Field<?> field : fieldsDataVector)
	{
	    field.reset();
	}
    }
}
