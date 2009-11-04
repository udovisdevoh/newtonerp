package newtonERP.module.field;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;

/**
 * Double field in the entities
 * 
 * @author djo
 */
public class FieldDouble extends Field
{
    Double data;

    /**
     * constructeur minimum
     * 
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @param data donne du champ
     */
    public FieldDouble(String name, String shortName, Double data)
    {
	super(name, shortName);
	this.data = data;
	operator = "=";
    }

    /**
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     */
    public FieldDouble(String name, String shortName)
    {
	this(name, shortName, null);
    }

    /**
     * @return the data
     */
    public Double getData()
    {
	return data;
    }

    private void setDataD(Double data)
    {
	this.data = data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Double data)
    {
	setDataD(data);
    }

    /**
     * @return the data
     */
    public String getDataString(Boolean forOrm)
    {
	return data + "";
    }

    /**
     * @param data the data to set
     */
    public void setData(String data)
    {
	if (data == null || data.equals("null"))
	    data = "0.0";
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
	else if (data instanceof Integer)
	    setDataD((double) ((Integer) (data)));
	else
	    throw new FieldNotCompatibleException(getShortName(), data);
    }

    @Override
    public void setOperator(String operator) throws InvalidOperatorException
    {
	operator.trim();

	if (operator.equals("<") || operator.equals(">")
		|| operator.equals("="))
	{
	    super.operator = operator;
	}
	else
	    throw new InvalidOperatorException("Opérateur invalide pour "
		    + getClass().getSimpleName());
    }

    public void setDefaultValue() throws FieldNotCompatibleException
    {
	setData(0);
    }
}
