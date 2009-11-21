package newtonERP.orm.field.Type;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;
import newtonERP.orm.field.Field;

/**
 * Double field in the entities
 * 
 * @author CloutierJo
 */
public class FieldDouble extends Field<Double>
{
    /**
     * constructeur minimum
     * 
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @param data donne du champ
     * @throws InvalidOperatorException remonte
     */
    public FieldDouble(String name, String shortName, Double data)
	    throws InvalidOperatorException
    {
	super(name, shortName, data);
    }

    /**
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @throws InvalidOperatorException remonte
     */
    public FieldDouble(String name, String shortName)
	    throws InvalidOperatorException
    {
	this(name, shortName, null);
    }

    /**
     * @param data the data to set
     * @throws Exception remonte
     */
    public void setData(String data) throws Exception
    {
	if (data == null || data.equals("null"))
	    data = "0.0";
	this.data = Double.parseDouble(data);
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
	setData(0.);
    }

    public void setData(Object data) throws FieldNotCompatibleException
    {
	if (data instanceof Double)
	    setDataType((Double) data);
	else if (data instanceof Integer)
	    setDataType((double) ((Integer) (data)));
	else
	    throw new FieldNotCompatibleException(getShortName(), data);
    }
}
