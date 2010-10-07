package newtonERP.orm.field.type;

import newtonERP.logging.Logger;
import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;

/**
 * Double field in the entities
 * 
 * @author CloutierJo
 */
public class FieldDouble extends newtonERP.orm.field.Field<Double>
{
	/**
	 * constructeur minimum
	 * 
	 * @param name nom du champ qui sera visible par l'utilisateur
	 * @param shortName nom du champ qui sera utiliser a l'interne
	 * @param data donne du champ
	 */
	public FieldDouble(String name, String shortName, Double data)
	{
		super(name, shortName, data);
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
	 * @param data the data to set
	 */
	public void setData(String data)
	{
		try
		{
			if (data == null || data.equals("null"))
				data = "0.0";
			this.data = Double.parseDouble(data);
		} catch (Exception e)
		{
			setErrorMessage("Le format de donnée entrée ne correspond pas avec le type de champ (Double): "
					+ data);
			Logger.error(e.getMessage());
		}
	}

	@Override
	public void setOperator(String operator)
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

	public void setDefaultValue()
	{
		setData(0.);
	}

	public void setData(Object data)
	{
		if (data instanceof Double)
			setDataType((Double) data);
		else if (data instanceof Integer)
			setDataType((double) ((Integer) (data)));
		else
			throw new FieldNotCompatibleException(getShortName(), data);
	}

}
