package newtonERP.module.field;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;

/**
 * @author djo, r3hallejo
 * 
 *         Boolean field in the entities
 */
public abstract class FieldBool extends Field
{
    Boolean data;
    String operator;

    /**
     * constructeur minimum
     * 
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @param data donne du champ
     */
    public FieldBool(String name, String shortName, Boolean data)
    {
	super(name, shortName);
	this.data = data;
    }

    /**
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     */
    public FieldBool(String name, String shortName)
    {
	this(name, shortName, null);
    }

    private void setDataB(Boolean data)
    {
	this.data = data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Boolean data)
    {
	setDataB(data);
    }

    /**
     * @return the data
     */
    public String getDataString()
    {
	return (data).toString();
    }

    /**
     * @param data the data to set
     */
    public void setData(String data)
    {
	this.data = Boolean.parseBoolean(data);
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
	if (!(obj instanceof FieldBool))
	    return false;
	FieldBool other = (FieldBool) obj;
	if (data != other.data)
	    return false;
	return true;
    }

    public Boolean getData()
    {
	// TODO Auto-generated method stub
	return data;
    }

    public void setData(Object data) throws FieldNotCompatibleException
    {
	if (data instanceof Boolean)
	    setDataB((Boolean) data);
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
