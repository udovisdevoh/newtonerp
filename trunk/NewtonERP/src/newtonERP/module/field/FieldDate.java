package newtonERP.module.field;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;

/**
 * Date field for entities
 * 
 * @author r3hallejo
 */
public class FieldDate extends Field
{
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
	    "yyyy-MM-dd");
    private GregorianCalendar data;
    private String operator;

    /**
     * @param name the viewable name
     * @param shortName the internal name
     * @param data the date
     */
    public FieldDate(String name, String shortName)
    {
	super(name, shortName);
	data = new GregorianCalendar();
	operator = "=";
    }

    @Override
    public Object getData()
    {
	return data;
    }

    @Override
    public String getDataString()
    {
	// TODO : Remove this code if no bug is reported with this class
	// if (data == null)
	// data = new GregorianCalendar();
	return dateFormatter.format(data.getTime());
    }

    /**
     * Use this method to store the dates in the database
     * 
     * @return the data that is stored like a date in the orm
     */
    public String getDataForOrm()
    {
	return dateFormatter.format(data) + " 12:00:00";
    }

    @Override
    public String getOperator()
    {
	return operator;
    }

    @Override
    public void setData(String date) throws ParseException
    {
	Date tempDate = dateFormatter.parse(date);
	data.setTime(tempDate);
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
	    this.operator = operator;
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
	GregorianCalendar gregorianCalendar = new GregorianCalendar();
	Date tempDate = dateFormatter.parse(dateInString);
	gregorianCalendar.setTime(tempDate);
	return gregorianCalendar;
    }
}
