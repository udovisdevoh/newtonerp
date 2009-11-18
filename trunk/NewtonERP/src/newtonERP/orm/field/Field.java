package newtonERP.orm.field;

import java.text.ParseException;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;

/**
 * Super class for entity fields used in the modules
 * 
 * @author djo, r3hallejo
 */
public abstract class Field
{
    private String name; // Name is the name that is visible by the end-user
    private String shortName; // Short name is the name that is used internally
    protected String operator;
    private Boolean hidden = false;
    private Boolean readOnly = false;
    private Boolean isNaturalKey = false;

    /**
     * default constructor
     */
    @SuppressWarnings("unused")
    private Field()
    {
	// on ne doit pas pouvoir initialiser un Field sans son name etshortName
    }

    /**
     * constructeur minimum
     * 
     * @param data donne du champ
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     */
    public Field(String name, String shortName)
    {
	this.name = name;
	// TODO: validate that shortName are really shortName
	this.shortName = shortName;
    }

    /**
     * @return the name
     */
    public String getName()
    {
	return name;
    }

    /**
     * @return the shortName
     */
    public String getShortName()
    {
	return shortName;
    }

    /**
     * @return the data sous forme de string visible par l'utilisateur
     */
    public String getDataString()
    {
	return getDataString(false);
    }

    /**
     * @param forOrm si true, la méthode retourne une string formatté pour l'ORM
     *            si faux retourne un string visible pour l'utilisateur, valeur
     *            par défaut false
     * @return the data
     */
    public abstract String getDataString(Boolean forOrm);

    /**
     * @return the data
     */
    public abstract Object getData();

    /**
     * @param data the data to set
     * @throws ParseException an exception that can occur during parsing dates
     * @throws Exception remonte
     */
    public abstract void setData(String data) throws ParseException, Exception;

    /**
     * @param data the data to set
     * @throws FieldNotCompatibleException si le type ne correspond pas avec le
     *             champ demande
     */
    public abstract void setData(Object data)
	    throws FieldNotCompatibleException;

    /**
     * Validation on operators will be done in the fields types
     * 
     * @param operator the operator to set in the field
     * @throws InvalidOperatorException if an operator is wrong for the data
     *             type
     */
    public abstract void setOperator(String operator)
	    throws InvalidOperatorException;

    /**
     * @return the operator
     */
    public String getOperator()
    {
	return operator;
    }

    /**
     * @param hidden the hidden to set
     */
    public void setHidden(Boolean hidden)
    {
	this.hidden = hidden;
    }

    /**
     * @return the hidden
     */
    public Boolean isHidden()
    {
	return hidden;
    }

    /**
     * @param readOnly the readOnly to set
     */
    public void setReadOnly(Boolean readOnly)
    {
	this.readOnly = readOnly;
    }

    /**
     * @return the readOnly
     */
    public Boolean isReadOnly()
    {
	return readOnly;
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
	if (!(obj instanceof Field))
	    return false;
	Field other = (Field) obj;
	if (name == null)
	{
	    if (other.name != null)
		return false;
	}
	else if (!name.equals(other.name))
	    return false;
	if (shortName == null)
	{
	    if (other.shortName != null)
		return false;
	}
	else if (!shortName.equals(other.shortName))
	    return false;
	return true;
    }

    /**
     * definie la valeur par defaut (devrais etre une valeur null)
     * @throws FieldNotCompatibleException remonte
     */
    public abstract void setDefaultValue() throws FieldNotCompatibleException;

    public String toString()
    {
	return "{" + getClass().getSimpleName() + ":" + getDataString() + "}";
    }

    protected String addSlash(String str)
    {
	str = str.replace("'", "''");
	return str;
    }

    /**
     * @return nom système d'un field
     */
    public String getSystemName()
    {
	return getClass().getSimpleName();
    }

    /**
     * @return si le field est une clef naturelle
     */
    public final boolean isNaturalKey()
    {
	return isNaturalKey;
    }

    /**
     * @param isNaturalKey mettre le field comme clef naturelle ou pas
     */
    public final void setNaturalKey(boolean isNaturalKey)
    {
	this.isNaturalKey = isNaturalKey;
    }
}
