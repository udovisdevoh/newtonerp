package newtonERP.orm.field;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;

/**
 * String field in the entities (String is a short text where text is a long
 * text)
 * 
 * @author CloutierJo, r3hallejo
 */
public class FieldString extends Field
{
    String data;

    /**
     * constructeur minimum
     * 
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @param data donne du champ
     */
    public FieldString(String name, String shortName, String data)
    {
	super(name, shortName);
	this.data = data;
	operator = "=";
    }

    /**
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     */
    public FieldString(String name, String shortName)
    {
	this(name, shortName, null);
    }

    /**
     * @return the data
     */
    public String getData()
    {
	return data;
    }

    private void setDataS(String data)
    {
	this.data = data;
    }

    /**
     * @return the data
     */
    public String getDataString(Boolean forOrm)
    {
	if (forOrm)
	    return addSlash(data);

	return data;
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
	if (!super.equals(obj))
	    return false;
	if (!(obj instanceof FieldString))
	    return false;
	FieldString other = (FieldString) obj;
	if (data == null)
	{
	    if (other.data != null)
		return false;
	}
	else if (!data.equals(other.data))
	    return false;
	return true;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data)
    {
	setDataS(data);
    }

    public void setData(Object data) throws FieldNotCompatibleException
    {
	if (data instanceof String)
	    setDataS((String) data);
	else if (data instanceof Number)
	    setDataS(data + "");
	else
	    throw new FieldNotCompatibleException(getShortName(), data);
    }

    @Override
    public void setOperator(String operator) throws InvalidOperatorException
    {
	operator.trim();

	if (operator.equals("="))
	{
	    super.operator = operator;
	}
	else
	    throw new InvalidOperatorException("Opérateur invalide pour "
		    + getClass().getSimpleName());
    }

    public void setDefaultValue()
    {
	setData("");
    }
}
