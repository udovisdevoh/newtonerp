package newtonERP.module.field;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

import newtonERP.module.exception.FieldNotFoundException;

/**
 * @author djo
 * 
 */
public class Fields
{
    Hashtable<String, Field> fields;

    /**
     * constructeur vide, permet de construire un Fields sans aucun champ
     */
    public Fields()
    {
	fields = new Hashtable<String, Field>();
    }

    /**
     * @param fields une liste de champ a inclure dans le Fields
     */
    public Fields(Vector<Field> fields)
    {
	this.fields = new Hashtable<String, Field>();
	for (Field field : fields)
	{
	    field.getShortName();
	    this.fields.put(field.getShortName(), field);
	}
    }

    /**
     * @return the fields
     */
    public Collection<Field> getFields()
    {
	return fields.values();
    }

    /**
     * @param shortName le nom du champ voulu
     * @return the named field
     */
    public Field getField(String shortName)
    {
	return fields.get(shortName);
    }

    /**
     * @param field the fields to set
     * @throws FieldNotFoundException si le champ donne n'existe pas
     */
    public void setField(Field field) throws FieldNotFoundException
    {
	if (fields.containsKey(field.getShortName()))
	{
	    fields.put(field.getShortName(), field);
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
     */
    public void setData(String shortName, Object data)
	    throws FieldNotFoundException
    {
	try
	{
	    if (data instanceof String)
		fields.get(shortName).setData((String) data);
	    else if (data instanceof Number)
		fields.get(shortName).setData(data + "");
	    else if (data instanceof Boolean)
		fields.get(shortName).setData(((Boolean) data).toString());
	} catch (NullPointerException e)
	{
	    throw new FieldNotFoundException(shortName);
	}
    }

    /**
     * @param parameters Hashtable de parametre
     */
    public void setFromHashTable(Hashtable<String, ?> parameters)
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
	for (Field field : fields.values())
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
	if (fields == null)
	{
	    if (other.fields != null)
		return false;
	}
	else if (!fields.equals(other.fields))
	    return false;
	return true;
    }

    public String toString()
    {
	return fields.toString();
    }
}
