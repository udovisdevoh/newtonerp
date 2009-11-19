package newtonERP.orm.field;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;

/**
 * Super class for entity fields used in the modules
 * 
 * @author CloutierJo, r3hallejo
 * @param <T> type de field
 */
public abstract class Field<T>
{
    T data;
    private String name; // Name is the name that is visible by the end-user
    private String shortName; // Short name is the name that is used internally
    protected String operator;
    private Boolean hidden = false;
    private Boolean readOnly = false;
    private Boolean isNaturalKey = false;
    private FieldValidator<T> validator;

    /**
     * default constructor
     */
    @SuppressWarnings("unused")
    private Field()
    {
	// on ne doit pas pouvoir initialiser un Field sans son name et
	// shortName
    }

    /**
     * constructeur minimum
     * 
     * @param data donne du champ
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @throws InvalidOperatorException remonte
     */
    public Field(String name, String shortName) throws InvalidOperatorException
    {
	this(name, shortName, null);
    }

    /**
     * constructeur minimum
     * 
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @param data donne du champ
     * @throws InvalidOperatorException remonte
     */
    public Field(String name, String shortName, T data)
	    throws InvalidOperatorException
    {

	this.name = name;
	// TODO: validate that shortName are really shortName
	this.shortName = shortName;
	this.data = data;
	setOperator("=");
	setValidator(new FieldValidator<T>()
	{
	    public boolean validate(T value)
	    {
		return true;
	    }
	});
    }

    /**
     * @param data the data to set
     * @throws Exception remonte
     */
    public abstract void setData(String data) throws Exception;

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
     * definie la valeur par defaut (devrais etre une valeur null)
     * @throws FieldNotCompatibleException remonte
     */
    public abstract void setDefaultValue() throws FieldNotCompatibleException;

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
    public String getDataString(Boolean forOrm)
    {
	if (data == null)
	    return "";
	if (forOrm)
	    return addSlash(data.toString());
	return data.toString();
    }

    /**
     * @return the data
     */
    public T getData()
    {
	return data;
    }

    /**
     * @param data the data to set
     */
    protected void setDataType(T data)
    {
	if (validator.validate(data))
	    this.data = data;
    }

    /**
     * @param data the data to set
     * @throws FieldNotCompatibleException remonte
     */
    public abstract void setData(Object data)
	    throws FieldNotCompatibleException;

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

    public String toString()
    {
	return "{" + getClass().getSimpleName() + ":" + getDataString() + "}";
    }

    protected String addSlash(String str)
    {
	str = str.replace("'", "`");
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

    /**
     * @param fieldValidator un validateur
     * @param validator the validator to set
     */
    public void setValidator(FieldValidator<T> fieldValidator)
    {
	validator = fieldValidator;
    }

    /**
     * @return the validator
     */
    public FieldValidator<T> getValidator()
    {
	return validator;
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
	Field<?> other = (Field<?>) obj;
	if (data == null)
	{
	    if (other.data != null)
		return false;
	}
	else if (!data.equals(other.data))
	    return false;
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
}
