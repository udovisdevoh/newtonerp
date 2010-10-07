package newtonERP.orm.field.type;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;

/**
 * String field in the entities (String is a short text where text is a long
 * text)
 * 
 * @author CloutierJo, r3hallejo
 */
public class FieldString extends newtonERP.orm.field.Field<String>
{
	/**
	 * constructeur minimum
	 * 
	 * @param name nom du champ qui sera visible par l'utilisateur
	 * @param shortName nom du champ qui sera utiliser a l'interne
	 * @param data donne du champ
	 */
	public FieldString(String name, String shortName, String data)
	{
		super(name, shortName, data);
	}

	/**
	 * @param name nom du champ qui sera visible par l'utilisateur
	 * @param shortName nom du champ qui sera utiliser a l'interne
	 */
	public FieldString(String name, String shortName)
	{
		this(name, shortName, null);
	}

	@Override
	public void setOperator(String operator)
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

	public void setData(Object data)
	{
		if (data instanceof String)
			setDataType((String) data);
		else if (data instanceof Number)
			setDataType(data + "");
		else
			throw new FieldNotCompatibleException(getShortName(), data);
	}

}
