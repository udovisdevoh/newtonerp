package newtonERP.orm.field.type;

import newtonERP.logging.Logger;
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
	 */
	public FieldInt(String name, String shortName, Integer data)

	{
		super(name, shortName, data);
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
			Logger.error(e.getMessage());
		}
	}

	@Override
	public void setOperator(String operator)
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

	public void setData(Object data)
	{
		if (data instanceof Integer)
			setDataType((Integer) data);
		else
			throw new FieldNotCompatibleException(getShortName(), data);
	}
}
