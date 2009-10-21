package newtonERP.module;

import java.util.Hashtable;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.field.Fields;

/**
 * @author r3lemaypa, r3lacasgu, CloutierJo
 * 
 */
public class AbstractEntity
{
    private Fields fields;

    /**
     * construit une entity ne comportant aucun champ
     */
    public AbstractEntity()
    {
	fields = initFields();
    }

    /**
     * initialise les champ de l'entity, doit etre overider si l'entity contient
     * des champs, sinon initialise une liste de champ vide
     * 
     * @return le Fields initialiser
     */
    public Fields initFields()
    {
	return new Fields();
    }

    /**
     * @param parameters Hashtable de parametre
     * @deprecated use getFields().setFromHashTable(parameters);
     */
    @Deprecated
    public void setEntityFromHashTable(Hashtable<String, ?> parameters)
    {
	fields.setFromHashTable(parameters);
    }

    /**
     * @return Hashtable contenant chaqu'un des champ
     * @deprecated use getFields().getHashTableFrom();
     */
    @Deprecated
    public Hashtable<String, String> getHashTableFromEntity()
    {

	return fields.getHashTableFrom();
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

}