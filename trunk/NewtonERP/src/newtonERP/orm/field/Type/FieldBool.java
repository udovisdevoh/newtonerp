package newtonERP.orm.field.Type;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;
import newtonERP.orm.field.Field;

/**
 * Boolean field in the entities
 * 
 * @author CloutierJo, r3hallejo
 */
public class FieldBool extends Field<Boolean>
{
    /**
     * constructeur minimum
     * 
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @param data donne du champ
     * @throws InvalidOperatorException remonte
     */
    public FieldBool(String name, String shortName, Boolean data)
	    throws InvalidOperatorException
    {
	super(name, shortName, data);
    }

    /**
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @throws InvalidOperatorException remonte
     */
    public FieldBool(String name, String shortName)
	    throws InvalidOperatorException
    {
	this(name, shortName, null);
    }

    @Override
    public String getDataString(Boolean forOrm)
    {
	if (data == null)
	    return "";

	if (forOrm)
	    return addSlash(data.toString());
	else if (data)
	    return "Oui";
	else
	    return "Non";
    }

    /**
     * @param data the data to set
     */
    public void setData(String data)
    {
	if (data.toLowerCase().equals("on")
		|| data.toLowerCase().equals("true"))
	{
	    setDataType(true);
	}
	else
	    setDataType(Boolean.parseBoolean(data));
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
	    throw new InvalidOperatorException("Opérateur ( " + operator
		    + " ) invalide pour " + getSystemName());
    }

    @Override
    public void setDefaultValue() throws FieldNotCompatibleException
    {
	data = false;
    }

    public void setData(Object data) throws FieldNotCompatibleException
    {
	if (data instanceof Boolean)
	    setDataType((Boolean) data);
	else
	    throw new FieldNotCompatibleException(getShortName(), data);
    }
}
