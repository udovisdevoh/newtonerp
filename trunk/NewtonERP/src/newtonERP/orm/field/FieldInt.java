package newtonERP.orm.field;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;

/**
 * Integer field in the entities
 * 
 * @author djo, r3hallejo
 */
public class FieldInt extends Field
{
    Integer data;

    /**
     * constructeur minimum
     * 
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @param data donne du champ
     */
    public FieldInt(String name, String shortName, Integer data)
    {
	super(name, shortName);
	this.data = data;
	operator = "=";
    }

    /**
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     */
    public FieldInt(String name, String shortName)
    {
	this(name, shortName, null);
    }

    /**
     * @return the data
     */
    public Integer getData()
    {
	return data;
    }

    private void setDataI(Integer data)
    {
	this.data = data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Integer data)
    {
	setDataI(data);
    }

    /**
     * @return the data
     */
    public String getDataString(Boolean forOrm)
    {
	if (forOrm)
	    return addSlash(data + "");
	return data + "";
    }

    /**
     * @param data the data to set
     */
    public void setData(String data)
    {
	if (data == null || data.equals("null"))
	    data = "0";
	this.data = Integer.parseInt(data);
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
	if (!(obj instanceof FieldInt))
	    return false;
	FieldInt other = (FieldInt) obj;
	if (data != other.data)
	    return false;
	return true;
    }

    public void setData(Object data) throws FieldNotCompatibleException
    {
	if (data instanceof Integer)
	    setDataI((Integer) data);
	else
	    throw new FieldNotCompatibleException(getShortName(), data);
    }

    @Override
    public void setOperator(String operator) throws InvalidOperatorException
    {
	operator.trim();

	if (operator.equals(">") || operator.equals("<")
		|| operator.equals("="))
	{
	    super.operator = operator;
	}
	else
	    throw new InvalidOperatorException("Opérateur invalide pour "
		    + getClass().getSimpleName());
    }

    public void setDefaultValue()
    {
	setData(0);
    }
}
