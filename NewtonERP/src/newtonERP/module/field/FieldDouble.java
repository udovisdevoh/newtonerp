package newtonERP.module.field;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;

/**
 * @author djo
 * 
 *         Double field in the entities
 */
public class FieldDouble extends Field
{
    double data;
    String operator;

    /**
     * constructeur minimum
     * 
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @param data donne du champ
     */
    public FieldDouble(String name, String shortName, double data)
    {
	super(name, shortName);
	this.data = data;
    }

    /**
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     */
    public FieldDouble(String name, String shortName)
    {
	this(name, shortName, 0.);
    }

    /**
     * @return the data
     */
    public Double getData()
    {
	return data;
    }

    private void setDataD(double data)
    {
	this.data = data;
    }

    /**
     * @param data the data to set
     */
    public void setData(double data)
    {
	setDataD(data);
    }

    /**
     * @return the data
     */
    public String getDataString()
    {
	return data + "";
    }

    /**
     * @param data the data to set
     */
    public void setData(String data)
    {
	this.data = Double.parseDouble(data);
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
	if (!(obj instanceof FieldDouble))
	    return false;
	FieldDouble other = (FieldDouble) obj;
	if (Double.doubleToLongBits(data) != Double
		.doubleToLongBits(other.data))
	    return false;
	return true;
    }

    public void setData(Object data) throws FieldNotCompatibleException
    {
	if (data instanceof Double)
	    setDataD((Double) data);
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
	if (operator.equals("<") || operator.equals(">")
		|| operator.equals("="))
	{
	    this.operator = operator;
	}
	else
	    throw new InvalidOperatorException("Opérateur invalide pour"
		    + getClass().getSimpleName());
    }
}
