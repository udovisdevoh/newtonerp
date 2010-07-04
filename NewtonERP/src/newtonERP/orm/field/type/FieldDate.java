package newtonERP.orm.field.type;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import newtonERP.logging.Logger;

/**
 * Date field for entities
 * 
 * @author r3hallejo
 */
public class FieldDate extends FieldDateTime
{
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * constructeur minimum
	 * 
	 * @param name nom du champ qui sera visible par l'utilisateur
	 * @param shortName nom du champ qui sera utiliser a l'interne
	 * @param data donne du champ
	 */
	public FieldDate(String name, String shortName, GregorianCalendar data)
	{
		super(name, shortName, data);
	}

	/**
	 * @param name nom du champ qui sera visible par l'utilisateur
	 * @param shortName nom du champ qui sera utiliser a l'interne
	 */
	public FieldDate(String name, String shortName)
	{
		super(name, shortName);
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
			setErrorMessage("Le format de donnée entrée ne correspond pas avec le type de champ (date): "
					+ data);
			Logger.error(e.getMessage());
		}
	}

	@Override
	public String getDataString(Boolean forOrm)
	{
		if (forOrm)
			return super.getDataString(forOrm);
		if (data == null)
			return null;
		return dateFormatter.format(super.getData().getTime());
	}
}