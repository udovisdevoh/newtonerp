package newtonERP.orm.field.type;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import newtonERP.logging.Logger;
import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;
import newtonERP.orm.field.Field;

/**
 * Date field for entities
 * 
 * @author r3hallejo
 */
public class FieldDateTime extends Field<GregorianCalendar>
{
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * constructeur minimum
	 * 
	 * @param name nom du champ qui sera visible par l'utilisateur
	 * @param shortName nom du champ qui sera utiliser a l'interne
	 * @param data donne du champ
	 */
	public FieldDateTime(String name, String shortName, GregorianCalendar data)

	{
		super(name, shortName, data);
	}

	/**
	 * @param name nom du champ qui sera visible par l'utilisateur
	 * @param shortName nom du champ qui sera utiliser a l'interne
	 */
	public FieldDateTime(String name, String shortName)

	{
		this(name, shortName, null);
	}

	@Override
	public String getDataString(Boolean forOrm)
	{
		if (data == null)
			return null;
		String retVal = dateFormatter.format(super.getData().getTime());
		if (forOrm)
			return addSlash(retVal);
		return retVal;
	}

	@Override
	public void setData(String date)
	{
		try
		{
			GregorianCalendar tempDate = new GregorianCalendar();
			tempDate.setTime(dateFormatter.parse(date));
			data = tempDate;
		} catch (Exception e)
		{
			setErrorMessage("Le format de donnée entrée ne correspond pas avec le type de champ (dateTime): "
					+ data);
			Logger.error(e.getMessage());
		}
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
		setData(new GregorianCalendar());
	}

	@Override
	public void setData(Object date)
	{
		if (date instanceof GregorianCalendar)
			setDataType((GregorianCalendar) date);
		else
			throw new FieldNotCompatibleException(
					"Field has to be a gregorian calendar");
	}

	/**
	 * Convertie une date de string vers GregorianCalendar
	 * @param dateInString date en string
	 * @return date un gregorian calendar
	 */
	public static GregorianCalendar getFormatedDate(String dateInString)

	{
		return getFormatedDate(dateInString, dateFormatter);
	}

	/**
	 * Convertie une date de string vers GregorianCalendar
	 * @param dateInString date en string
	 * @param sdf format de dateTime a utiliser
	 * @return date un gregorian calendar
	 */
	public static GregorianCalendar getFormatedDate(String dateInString,
			SimpleDateFormat sdf)
	{
		try
		{
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			Date tempDate = sdf.parse(dateInString);
			gregorianCalendar.setTime(tempDate);
			return gregorianCalendar;
		} catch (Exception e)
		{
			Logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * @param first premier date
	 * @param second deuxieme date
	 * @param field etendu de comparaison
	 * @return true si les 2 date son dans le meme intervale
	 */
	public static boolean isInSameDay(GregorianCalendar first,
			GregorianCalendar second)
	{
		boolean ret;
		ret = first.get(Calendar.DAY_OF_YEAR) == second
				.get(Calendar.DAY_OF_YEAR);
		ret &= first.get(Calendar.YEAR) == second.get(Calendar.YEAR);
		return ret;
	}
}
