package newtonERP.orm.field.type;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;
import newtonERP.orm.field.Field;

/**
 * Integer field in the entities
 * 
 * @author CloutierJo, r3hallejo
 */
public class FieldInt extends Field<Integer>
{
    /**
     * constructeur minimum
     * 
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @param data donne du champ
     * @throws InvalidOperatorException remonte
     */
    public FieldInt(String name, String shortName, Integer data)
	    throws InvalidOperatorException
    {
	super(name, shortName, data);
    }

    /**
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @throws InvalidOperatorException remonte
     */
    public FieldInt(String name, String shortName)
	    throws InvalidOperatorException
    {
	this(name, shortName, null);
    }

    /**
     * @param data the data to set
     */
    public void setData(String data)
    {
	try
	{
	    if (data == null || data.equals("null"))
		data = "0";
	    setDataType(Integer.parseInt(data));
	} catch (Exception e)
	{
	    setErrorMessage("Le format de donnée entrée ne correspond pas avec le type de champ (entier): "
		    + data);
	    System.err.println(e.getMessage());
	}
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

    public void setDefaultValue() throws FieldNotCompatibleException
    {
	setData(0);
    }

    public void setData(Object data) throws FieldNotCompatibleException
    {
	if (data instanceof Integer)
	    setDataType((Integer) data);
	else
	    throw new FieldNotCompatibleException(getShortName(), data);
    }
}
