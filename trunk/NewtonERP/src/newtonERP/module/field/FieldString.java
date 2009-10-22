package newtonERP.module.field;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;

/**
 * @author djo, r3hallejo
 * 
 *         String field in the entities
 */
public class FieldString extends Field
{
    String data;
    String operator;

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
    }

    /**
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     */
    public FieldString(String name, String shortName)
    {
	this(name, shortName, "");
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
    public String getDataString()
    {
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
	else
	    throw new FieldNotCompatibleException(getShortName(), data);
    }

    public String toString()
    {
	return getDataString();
    }

    @Override
    public String getOperator()
    {
	return operator;
    }

    @Override
    public void setOperator(String operator) throws InvalidOperatorException
    {
	if (operator.equals("="))
	{
	    this.operator = operator;
	}
	else
	    throw new InvalidOperatorException("Opérateur invalide pour "
		    + getClass().getSimpleName());
    }
}
