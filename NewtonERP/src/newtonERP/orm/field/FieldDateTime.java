package newtonERP.orm.field;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;

/**
 * Date field for entities
 * 
 * @author r3hallejo
 */
public class FieldDateTime extends Field
{
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
	    "yyyy-MM-dd HH:mm:ss");
    protected GregorianCalendar data;

    /**
     * constructeur minimum
     * 
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @param data donne du champ
     */
    public FieldDateTime(String name, String shortName, GregorianCalendar data)
    {
	super(name, shortName);
	this.data = data;
	operator = "=";
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
    public Object getData()
    {
	return data;
    }

    @Override
    public String getDataString(Boolean forOrm)
    {
	if (data == null)
	    return null;
	String retVal = dateFormatter.format(data.getTime());
	if (forOrm)
	    return addSlash(retVal);
	return retVal;
    }

    @Override
    public void setData(String date) throws ParseException
    {
	GregorianCalendar tempDate = new GregorianCalendar();
	tempDate.setTime(dateFormatter.parse(date));
	data = tempDate;
    }

    @Override
    public void setData(Object date) throws FieldNotCompatibleException
    {
	if (date instanceof GregorianCalendar)
	    data = (GregorianCalendar) date;
	else
	    throw new FieldNotCompatibleException(
		    "Field has to be a gregorian calendar");
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

    public void setDefaultValue() throws FieldNotCompatibleException
    {
	setData(new GregorianCalendar());
    }

    /**
     * Convertie une date de string vers GregorianCalendar
     * @param dateInString date en string
     * @return date un gregorian calendar
     * @throws Exception si formattage fail
     */
    public static GregorianCalendar getFormatedDate(String dateInString)
	    throws Exception
    {
	return getFormatedDate(dateInString, dateFormatter);
    }

    /**
     * Convertie une date de string vers GregorianCalendar
     * @param dateInString date en string
     * @param sdf format de dateTime a utiliser
     * @return date un gregorian calendar
     * @throws Exception si formattage fail
     */
    public static GregorianCalendar getFormatedDate(String dateInString,
	    SimpleDateFormat sdf) throws Exception
    {
	GregorianCalendar gregorianCalendar = new GregorianCalendar();
	Date tempDate = sdf.parse(dateInString);
	gregorianCalendar.setTime(tempDate);
	return gregorianCalendar;
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
