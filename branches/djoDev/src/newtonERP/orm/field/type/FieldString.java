package newtonERP.orm.field.type;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;
import newtonERP.orm.field.Field;

/**
 * String field in the entities (String is a short text where text is a long
 * text)
 * 
 * @author CloutierJo, r3hallejo
 */
public class FieldString extends Field<String>
{
    /**
     * constructeur minimum
     * 
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @param data donne du champ
     * @throws InvalidOperatorException remonte
     */
    public FieldString(String name, String shortName, String data)
	    throws InvalidOperatorException
    {
	super(name, shortName, data);
    }

    /**
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @throws InvalidOperatorException remonte
     */
    public FieldString(String name, String shortName)
	    throws InvalidOperatorException
    {
	this(name, shortName, null);
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
	setDataType("");
    }

    public void setData(String data)
    {
	setDataType(data);
    }

    public void setData(Object data) throws FieldNotCompatibleException
    {
	if (data instanceof String)
	    setDataType((String) data);
	else if (data instanceof Number)
	    setDataType(data + "");
	else
	    throw new FieldNotCompatibleException(getShortName(), data);
    }
}
