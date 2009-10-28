package newtonERP.module.field;

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
 * @author djo
 */
public class Fields implements Iterable<Field>
{
    Hashtable<String, Field> fieldsData;

    /**
     * constructeur vide, permet de construire un Fields sans aucun champ
     */
    public Fields()
    {
	fieldsData = new Hashtable<String, Field>();// RENAMÉ pour ne pas le
	// conforndre avec une
	// instance de la classe
	// Fields
    }

    /**
     * @param fields une liste de champ a inclure dans le Fields
     */
    public Fields(Vector<Field> fields)
    {
	fieldsData = new Hashtable<String, Field>();
	for (Field field : fields)
	{
	    field.getShortName();
	    fieldsData.put(field.getShortName(), field);
	}
    }

    /**
     * @return the fields
     */
    public Collection<Field> getFields()
    {
	return fieldsData.values();
    }

    /**
     * @param shortName le nom du champ voulu
     * @return the named field
     */
    public Field getField(String shortName)
    {
	return fieldsData.get(shortName);
    }

    /**
     * @param field the fields to set
     * @throws FieldNotFoundException si le champ donne n'existe pas
     */
    public void setField(Field field) throws FieldNotFoundException
    {
	if (fieldsData.containsKey(field.getShortName()))
	{
	    fieldsData.put(field.getShortName(), field);
	}
	else
	    throw new FieldNotFoundException(field.getShortName());
    }

    /**
     * methode rapide pour change la valeur d'un champ
     * 
     * @param shortName nom du champ
     * @param data valeur modifie
     * @throws FieldNotFoundException si le champ demandé n'existe pas
     * @throws ParseException an exception that can occur when parsing dates
     */
    public void setData(String shortName, Object data)
	    throws FieldNotFoundException, ParseException
    {
	try
	{
	    if (data instanceof String)
		fieldsData.get(shortName).setData((String) data);
	    else if (data instanceof Number)
		fieldsData.get(shortName).setData(data + "");
	    else if (data instanceof Boolean)
		fieldsData.get(shortName).setData(((Boolean) data).toString());
	} catch (NullPointerException e)
	{
	    throw new FieldNotFoundException(shortName);
	}
    }

    /**
     * @param parameters Hashtable de parametre
     * @throws ParseException an exception that can occur during date parsing
     */
    public void setFromHashTable(Hashtable<String, ?> parameters)
	    throws ParseException
    {
	for (String key : parameters.keySet())
	{
	    try
	    {
		setData(key, parameters.get(key));
	    } catch (FieldNotFoundException e)
	    {
		// nothing to do
	    }

	}
    }

    /**
     * @return Hashtable contenant chaqu'un des champ
     */
    public Hashtable<String, String> getHashTableFrom()
    {
	Hashtable<String, String> hash = new Hashtable<String, String>();
	for (Field field : fieldsData.values())
	{
	    if (field.getDataString() != null)
		hash.put(field.getShortName(), field.getDataString());
	}
	return hash;
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
	if (fieldsData == null)
	{
	    if (other.fieldsData != null)
		return false;
	}
	else if (!fieldsData.equals(other.fieldsData))
	    return false;
	return true;
    }

    public String toString()
    {
	return fieldsData.toString();
    }

    @Override
    public Iterator<Field> iterator()
    {
	return fieldsData.values().iterator();
    }

    /**
     * @return Liste des clefs des fields
     */
    public Vector<String> getKeyList()
    {
	return new Vector<String>(fieldsData.keySet());
    }

    /**
     * @return Liste des clefs des fields
     */
    public Vector<String> getLongFieldNameList()
    {
	Vector<String> longFieldName = new Vector<String>();
	for (Field field : this)
	    longFieldName.add(field.getName());
	return longFieldName;
    }

    /**
     * @throws FieldNotCompatibleException remonte
     */
    public void setDefaultValue() throws FieldNotCompatibleException
    {
	for (Field field : getFields())
	{
	    field.setDefaultValue();
	}
    }

    /**
     * @param field field à ajouter ou remplacer s'il existe
     */
    public void add(Field field)
    {
	fieldsData.put(field.getShortName(), field);
    }
}